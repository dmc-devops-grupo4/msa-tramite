package edu.proyecto.repository;

import edu.proyecto.dto.EmailTramiteEnviadoDTO;

public interface NotificacionRepository {
    public void notificarTramiteEnviado(EmailTramiteEnviadoDTO data);
}
