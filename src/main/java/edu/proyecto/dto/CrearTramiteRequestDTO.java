package edu.proyecto.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearTramiteRequestDTO {
    private Integer idUsuario; // viene de vista
    private Integer idOficina;
    private Integer idTipoDocumento;
    private String nroDocumento;
    private String asunto;
    private String observacion;
    private List<String> archivosId;
}
