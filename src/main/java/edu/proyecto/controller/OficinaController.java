package edu.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.dto.OficinaDTO;
import edu.proyecto.service.OficinaService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/oficinas")
@Tag(name = "Oficina", description = "Api Oficina")
public class OficinaController {
    @Autowired
    private OficinaService oficinaService;

    @GetMapping
    public ResponseEntity<List<OficinaDTO>> listarOficinas() {
        return ResponseEntity.ok(oficinaService.listarOficina());
    }
    
}
