package edu.proyecto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchivoEntity {
    private String idArchivo;
    private String nombre;
    private Long tamanio;
    private String tipo;
    private String extension;
    private String ruta;
    private boolean activo;
}
