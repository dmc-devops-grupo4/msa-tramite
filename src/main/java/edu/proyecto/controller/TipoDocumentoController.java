package edu.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.dto.TipoDocumentoDTO;
import edu.proyecto.service.TipoDocumentoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/tipos-documento")
@Tag(name = "TipoDocumento", description = "Api TipoDocumento")
public class TipoDocumentoController {
    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @GetMapping
    public ResponseEntity<List<TipoDocumentoDTO>> tipoDocumentoOficinas() {
        return ResponseEntity.ok(tipoDocumentoService.listarTipoDocumento());
    }
     
}
