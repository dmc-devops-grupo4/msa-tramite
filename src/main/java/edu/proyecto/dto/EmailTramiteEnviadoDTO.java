package edu.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTramiteEnviadoDTO {
    private String correo;
    private String nombre;
    private String nroDocumento;
    private String asunto;
}
