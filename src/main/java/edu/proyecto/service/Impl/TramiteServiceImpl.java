package edu.proyecto.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import edu.proyecto.dto.EmailTramiteEnviadoDTO;
import edu.proyecto.dto.TramiteDTO;
import edu.proyecto.dto.CrearTramiteRequestDTO;
import edu.proyecto.entity.ArchivoEntity;
import edu.proyecto.entity.OficinaEntity;
import edu.proyecto.entity.TipoDocumentoEntity;
import edu.proyecto.entity.TramiteArchivoEntity;
import edu.proyecto.entity.TramiteEntity;
import edu.proyecto.entity.UsuarioEntity;
import edu.proyecto.mapper.TramiteMapper;
import edu.proyecto.repository.ArchivoRepository;
import edu.proyecto.repository.OficinaRepository;
import edu.proyecto.repository.TipoDocumentoRepository;
import edu.proyecto.repository.TramiteArchivoRepository;
import edu.proyecto.repository.TramiteRepository;
import edu.proyecto.repository.UsuarioRepository;
import edu.proyecto.service.TramiteService;
import edu.proyecto.utils.Helper;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TramiteServiceImpl implements TramiteService {
    @Autowired
    private TramiteRepository tramiteRepository;
    @Autowired
    private OficinaRepository oficinaRepository;
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;
    @Autowired
    private TramiteArchivoRepository tramiteArchivoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ArchivoRepository archivoRepository;
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${topico.tramite-enviado}")
    private String topicoTramiteEnviado;

    TramiteMapper mapper = Mappers.getMapper(TramiteMapper.class);

    @Override
    public List<TramiteDTO> listarTramites() {
        return tramiteRepository.listarTramiteActivos().stream()
                    .map(entity -> mapper.toTramiteDto(entity))
                    .collect(Collectors.toList());
    }

    @Override
    public TramiteDTO obtenerTramite(Integer idTramite) {
        return mapper.toTramiteDto(tramiteRepository.findById(idTramite).get());
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED) 
    public TramiteDTO grabarTramite(CrearTramiteRequestDTO tramiteDto) {

        OficinaEntity oficinaEntity = oficinaRepository.findById(tramiteDto.getIdOficina())
                                .orElseThrow(() -> new RuntimeException("Oficina no encontrado"));

        TipoDocumentoEntity tipoDocumentoEntity = tipoDocumentoRepository.findById(tramiteDto.getIdTipoDocumento())
                                .orElseThrow(() -> new RuntimeException("Tipo Documento no encontrado"));
        
        UsuarioEntity usuarioEntity = usuarioRepository.findById(tramiteDto.getIdUsuario())
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        //Guardar tramite
        TramiteEntity tramiteEntity = new TramiteEntity(); 

        tramiteEntity.setFecha(Helper.getCurrentDate());
        tramiteEntity.setUsuario(usuarioEntity);
        tramiteEntity.setOficina(oficinaEntity);
        tramiteEntity.setTipoDocumento(tipoDocumentoEntity);
        tramiteEntity.setNroDocumento(tramiteDto.getNroDocumento());
        tramiteEntity.setAsunto(tramiteDto.getAsunto());
        tramiteEntity.setObservacion(tramiteDto.getObservacion());
        tramiteEntity.setEstado(1);
        tramiteEntity.setActivo(true);
        tramiteEntity.setRegUsuarioCreacion("DEFAULT"); 
    
        tramiteEntity = tramiteRepository.save(tramiteEntity);
        
        for (String idArchivo : tramiteDto.getArchivosId()) {

            ArchivoEntity archivoEntity = archivoRepository.obtenerArchivo(idArchivo);

            TramiteArchivoEntity tramiteArchivo = new TramiteArchivoEntity();

            tramiteArchivo.setTramite(tramiteEntity);
            tramiteArchivo.setIdArchivo(idArchivo);
            tramiteArchivo.setNombreArchivo(archivoEntity.getNombre());
            tramiteArchivo.setActivo(true);

            tramiteArchivoRepository.save(tramiteArchivo);
        }
        
        try {
            String jsonMessage = objectMapper.writeValueAsString(
                new EmailTramiteEnviadoDTO(
                    usuarioEntity.getCorreo(), 
                    usuarioEntity.getNombres(),
                    tramiteEntity.getNroDocumento(),
                    tramiteEntity.getAsunto()
                ));
    
            System.out.println("Mensaje: " + jsonMessage);
            kafkaTemplate.send(topicoTramiteEnviado, jsonMessage);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mapper.toTramiteDto(tramiteEntity);
    }
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED) 
    public void eliminarTramite(Integer idTramite) {
        
        TramiteEntity tramiteEntity = tramiteRepository.findById(idTramite).get();

        if(tramiteEntity == null){
            throw new RuntimeException("Tramite no existe");
        }

        tramiteEntity.setActivo(false);
        tramiteRepository.save(tramiteEntity);

        List<TramiteArchivoEntity> tramitesArchivo = tramiteArchivoRepository.buscarTramitesArchivoPorIdTramite(tramiteEntity.getIdTramite());
        ////eliminar Tramite_Archivo
        for (TramiteArchivoEntity tramiteArchivo : tramitesArchivo) {
            if(tramiteArchivo == null){
                throw new RuntimeException("Archivo de Trámite no existe");
            }

            tramiteArchivo.setActivo(false);
            tramiteArchivoRepository.save(tramiteArchivo);
        }
    }

    @Override
    public void aceptarTramite(Integer idTramite) {
        
        TramiteEntity tramiteEntity = tramiteRepository.findById(idTramite).get();
        if(tramiteEntity == null){
            throw new RuntimeException("Trámite no existe");
        }
        if(tramiteEntity.getActivo() == false){
            throw new RuntimeException("No puede aceptar un trámite inactivo");
        }

        tramiteEntity.setEstado(2);
        tramiteRepository.save(tramiteEntity);
    }

    @Override
    public void rechazarTramite(Integer idTramite, String motivo) {
        
        TramiteEntity tramiteEntity = tramiteRepository.findById(idTramite).get();
        if(tramiteEntity == null){
            throw new RuntimeException("Trámite no existe");
        }
        if(tramiteEntity.getActivo() == false){
            throw new RuntimeException("No puede rechazar un trámite inactivo");
        }

        tramiteEntity.setEstado(3);
        tramiteEntity.setMotivoRechazo(motivo);
        tramiteRepository.save(tramiteEntity);
    }

    @Override
    public List<TramiteDTO> listarTramitesPorFiltros(String fechaIni, String fechaFin, 
        Integer idUsuario, Integer idTipoDocumento, String nroDocumento, 
        String asunto, Integer estado) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha1Date = formatter.parse(fechaIni);
            Date fecha2Date = formatter.parse(fechaFin);
            List<TramiteEntity> listaTramites = tramiteRepository.listarPorFechasUsuario(fecha1Date, fecha2Date, idUsuario);

            if(idTipoDocumento != null && idTipoDocumento > 0){
                listaTramites = listaTramites.stream()
                                .filter(e -> e.getTipoDocumento().getIdTipoDocumento() == idTipoDocumento)
                                .collect(Collectors.toList());
            }

            if(nroDocumento != null && !nroDocumento.equals("")){
                listaTramites = listaTramites.stream()
                                .filter(e -> e.getNroDocumento().equals(nroDocumento))
                                .collect(Collectors.toList());
            }

            if(asunto != null && !asunto.equals("")){
                listaTramites = listaTramites.stream()
                            .filter(e -> e.getAsunto() != null && e.getAsunto().contains(asunto))
                            .collect(Collectors.toList());
            }

            if(estado != null && estado > 0){
                listaTramites = listaTramites.stream()
                                .filter(e -> e.getEstado() == estado)
                                .collect(Collectors.toList());
            }

            return listaTramites.stream()
                        .map(entity -> mapper.toTramiteDto(entity))
                        .collect(Collectors.toList());
        
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // o lanzar una excepción personalizada
        }
    }
    
}
