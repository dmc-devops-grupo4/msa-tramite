package edu.proyecto.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyecto.dto.OficinaDTO;
import edu.proyecto.mapper.OficinaMapper;
import edu.proyecto.repository.OficinaRepository;
import edu.proyecto.service.OficinaService;

@Service
public class OficinaServiceImpl implements OficinaService{
    @Autowired
    private OficinaRepository oficinaRepository;

    OficinaMapper mapper = Mappers.getMapper(OficinaMapper.class);

    @Override
    public List<OficinaDTO> listarOficina() {
        return oficinaRepository.consultarOficinaActivos().stream()
                .map(entity -> mapper.toOficinaDto(entity))
                .collect(Collectors.toList());
    }

}
