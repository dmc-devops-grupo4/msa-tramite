package edu.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tramite_archivo")
@Schema(name = "tramite-archivo", description = "Entity de Tramite Archivo")
public class TramiteArchivoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tramite_archivo")
    private Integer idTramiteArchivo;

    @ManyToOne
    @JoinColumn(name = "id_tramite", updatable = false, nullable = false)
    @JsonBackReference
    private TramiteEntity tramite;

    @Column(name = "id_archivo")
    private String idArchivo;
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    
    @Column(name = "activo")
    private Boolean activo;
}
