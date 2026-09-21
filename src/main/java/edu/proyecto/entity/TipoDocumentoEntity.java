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
@Table(name = "tipo_documento")
@Schema(name = "tipo_documento", description = "Entity de Tipo de Documento")
public class TipoDocumentoEntity {
    @Id
    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "abreviatura")
    private String abreviatura;
    @Column(name = "activo")
    private boolean activo;
}
