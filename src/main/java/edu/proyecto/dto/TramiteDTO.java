package edu.proyecto.dto;

import java.util.Date;
import java.util.List;

import edu.proyecto.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TramiteDTO {
    private Integer idTramite;
    private Date fecha;
    private OficinaDTO oficina;
    private TipoDocumentoDTO tipoDocumento;
    private String nroDocumento;
    private String asunto;
    private String observacion;
    private UsuarioEntity usuario; // view model
    private Integer estado;
    private String estadoString;
    private String motivoRechazo;
    private Boolean activo;
    private List<TramiteArchivoDTO> tramiteArchivos;
}

