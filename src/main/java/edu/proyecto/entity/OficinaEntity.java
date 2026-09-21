package edu.proyecto.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "oficina")
@Schema(name = "oficina", description = "Entity de Oficina")
public class OficinaEntity {
    @Id
    @Column(name = "id_oficina")
    private Integer idOficina;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private Boolean  activo;

}
