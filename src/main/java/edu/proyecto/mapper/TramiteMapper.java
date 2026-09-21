package edu.proyecto.mapper;

import org.mapstruct.Mapper;

import edu.proyecto.dto.CrearTramiteRequestDTO;
import edu.proyecto.dto.TramiteDTO;
import edu.proyecto.entity.TramiteEntity;

@Mapper
public interface TramiteMapper {
    TramiteDTO toTramiteDto(TramiteEntity data);
    TramiteEntity toTramiteEntity(CrearTramiteRequestDTO data);
}
