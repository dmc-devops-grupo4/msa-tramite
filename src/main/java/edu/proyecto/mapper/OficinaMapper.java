package edu.proyecto.mapper;

import org.mapstruct.Mapper;

import edu.proyecto.dto.OficinaDTO;
import edu.proyecto.entity.OficinaEntity;

@Mapper
public interface OficinaMapper {
    OficinaDTO toOficinaDto(OficinaEntity data);

}
