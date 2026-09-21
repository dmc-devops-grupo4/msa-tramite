package edu.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TramiteArchivoDTO {
    private Integer idTramiteArchivo;
    private String idArchivo;
    private String nombreArchivo;
    private boolean activo;
}

