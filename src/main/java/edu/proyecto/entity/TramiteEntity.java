package edu.proyecto.entity;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tramite")
@Schema(name = "tramite", description = "Entity de Tramite")
public class TramiteEntity extends RepresentationModel<TramiteEntity>{    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tramite")
    private Integer idTramite;

    @Column(name = "fecha")
    private Date fecha;
 
    @OneToOne
    @JoinColumn(name = "id_usuario", updatable = false, nullable = false)
    private UsuarioEntity usuario;
    
    @OneToMany(mappedBy = "tramite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TramiteArchivoEntity> tramiteArchivos;

    @OneToOne
    @JoinColumn(name = "id_oficina", updatable = false, nullable = false)
    private OficinaEntity oficina;

    @OneToOne
    @JoinColumn(name = "id_tipo_documento", updatable = false, nullable = false)
    private TipoDocumentoEntity tipoDocumento;

    @Column(name = "nro_documento")
    private String nroDocumento;
    @Column(name = "asunto")
    private String asunto;
    @Column(name = "observacion")
    private String observacion;
    
    @Column(name = "activo")
    private Boolean  activo;

    @Column(name = "motivo_rechazo")
    private String motivoRechazo;
    
    @Column(name="reg_fecha_creacion")
    private Date regFechaCreacion;
    @Column(name="reg_usuario_creacion")
    private String regUsuarioCreacion;
    @Column(name="reg_ip_creacion")
    private String regIpCreacion;
    @Column(name="reg_fecha_modificacion")
    private Date regFechaModificacion;
    @Column(name="reg_usuario_modificacion")
    private String regUsuarioModificacion;
    @Column(name="reg_ip_modificacion")
    private String regIpModificacion;

    @Column(name = "estado")
    private Integer estado;
    @Transient
    private String estadoString;
    
    public String getEstadoString(){
        String resultado ="";
        switch (this.estado) {
            case 1:
                resultado="SOLICITADO";
                break;
            case 2:
                resultado="ACEPTADO";
                break;
            case 3:
                resultado="RECHAZADO";
                break;
            default:
                resultado="";
                break;
        }
        return resultado;
    }

}
