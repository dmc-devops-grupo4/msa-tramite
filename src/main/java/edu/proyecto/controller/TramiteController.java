package edu.proyecto.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.dto.RechazarTramiteRequestDTO;
import edu.proyecto.dto.TramiteDTO;
import edu.proyecto.dto.CrearTramiteRequestDTO;
import edu.proyecto.entity.ErrorEntity;
import edu.proyecto.service.TramiteService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/tramites")
@Tag(name = "Tramites", description = "Api Tramite")
public class TramiteController {

    @Autowired
    private TramiteService tramiteService;
    
    @ExceptionHandler(Exception.class)   ///obteniendo el error de excepcion en forma generica
    private ErrorEntity capturadorErrores(Exception ex){
        ErrorEntity error = new ErrorEntity(HttpStatus.CONFLICT.toString(), "Problema interno :)", "A ocurrido un error: "+ex.getMessage());
        return  error;
    }
    
    @GetMapping
    public ResponseEntity<List<TramiteDTO>> listarTramites(){
        List<TramiteDTO> listTramites = tramiteService.listarTramites();
        
        return ResponseEntity.status(HttpStatus.OK)  
                        .header("Tramite", "grupoproyecto")                     
                        .body(listTramites);
    }

    @GetMapping("/por-usuario/{idUsuario}/por-filtros")
    public ResponseEntity<List<TramiteDTO>> listarTramitesPorFiltros(
        @PathVariable("idUsuario") Integer idUsuario,
        @RequestParam(required = true) String fechaIni, 
        @RequestParam(required = true) String fechaFin, 
        @RequestParam(required = false) Integer idTipoDocumento, 
        @RequestParam(required = false) String nroDocumento, 
        @RequestParam(required = false) String asunto, 
        @RequestParam(required = false) Integer estado
    ) {
        List<TramiteDTO> listTramites = tramiteService.listarTramitesPorFiltros(fechaIni, fechaFin, idUsuario, idTipoDocumento, nroDocumento, asunto, estado);
        return ResponseEntity.status(HttpStatus.OK).body(listTramites);
    }    

    @GetMapping("{idTramite}") 
    public ResponseEntity<TramiteDTO> obtenerTramite(@PathVariable("idTramite") Integer idTramite){
        TramiteDTO tramite = tramiteService.obtenerTramite(idTramite);
        
        return ResponseEntity.status(HttpStatus.OK)
                            .header("User", "grupoproyecto")
                            .body(tramite);
    }

    @PostMapping
    public ResponseEntity<TramiteDTO> registrarTramite(@RequestBody CrearTramiteRequestDTO request) throws URISyntaxException{   
        TramiteDTO tramiteEntity = tramiteService.grabarTramite(request); 
        return ResponseEntity.created(new URI("/"+tramiteEntity.getIdTramite()))
                            .body(tramiteEntity);
    }

    @DeleteMapping("/{idTramite}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> eliminarTramite(@PathVariable("idTramite") Integer idTramite){
        tramiteService.eliminarTramite(idTramite);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{idTramite}/aceptar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> aceptarTramite(@PathVariable("idTramite") Integer idTramite){
        tramiteService.aceptarTramite(idTramite);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{idTramite}/rechazar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> rechazarTramite(@PathVariable("idTramite") Integer idTramite, @RequestBody RechazarTramiteRequestDTO requestDto){
        tramiteService.rechazarTramite(idTramite, requestDto.getMotivo());
        return ResponseEntity.noContent().build();
    }

}
