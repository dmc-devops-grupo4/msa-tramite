package edu.proyecto.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyecto.dto.TipoDocumentoDTO;
import edu.proyecto.mapper.TipoDocumentoMapper;
import edu.proyecto.repository.TipoDocumentoRepository;
import edu.proyecto.service.TipoDocumentoService;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService{
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    TipoDocumentoMapper mapper = Mappers.getMapper(TipoDocumentoMapper.class);

    @Override
    public List<TipoDocumentoDTO> listarTipoDocumento() {
       return tipoDocumentoRepository.consultarTipoDocumentoActivos().stream()
                .map(entity -> mapper.toTipoDocumentoDto(entity))
                .collect(Collectors.toList());   
    }
    
}
