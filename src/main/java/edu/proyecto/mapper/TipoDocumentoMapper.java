package edu.proyecto.mapper;

import org.mapstruct.Mapper;

import edu.proyecto.dto.TipoDocumentoDTO;
import edu.proyecto.entity.TipoDocumentoEntity;

@Mapper
public interface TipoDocumentoMapper {
    TipoDocumentoDTO toTipoDocumentoDto(TipoDocumentoEntity data);

}
