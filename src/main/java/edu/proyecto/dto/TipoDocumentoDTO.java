package edu.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumentoDTO {
    private Integer idTipoDocumento;
    private String nombre;
    private String abreviatura;
}

