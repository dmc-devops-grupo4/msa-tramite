package edu.proyecto.mapper;

import org.mapstruct.Mapper;

import edu.proyecto.dto.TramiteArchivoDTO;
import edu.proyecto.entity.TramiteArchivoEntity;

@Mapper
public interface TramiteArchivoMapper {
    TramiteArchivoDTO toTramiteArchivoDto(TramiteArchivoEntity data);

}
