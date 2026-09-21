package edu.proyecto.service;

import java.util.List;

import edu.proyecto.dto.CrearTramiteRequestDTO;
import edu.proyecto.dto.TramiteDTO;

public interface TramiteService {
    public List<TramiteDTO> listarTramites();
    public TramiteDTO obtenerTramite(Integer idTramite);
    public TramiteDTO grabarTramite(CrearTramiteRequestDTO tramiteDto);
    public void eliminarTramite(Integer idTramite);
    public void aceptarTramite(Integer idTramite);
    public void rechazarTramite(Integer idTramite, String motivo);

    public List<TramiteDTO> listarTramitesPorFiltros(String fechaIni, String fechaFin, 
        Integer idUsuario, Integer idTipoDocumento, String nroDocumento, 
        String asunto, Integer estado);
}
