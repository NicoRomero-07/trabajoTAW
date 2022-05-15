/**
 *
 * @author Nicolás Zhao(100%)
 */
package trabajoTAW.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;


public class NotificacionDTO {

    private Integer idNotificacion;
    private String contenido;
    private Date fechaEnvio;
    private UsuarioDTO notificante;

    public NotificacionDTO() {
    }

  
    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public UsuarioDTO getNotificante() {
        return notificante;
    }
    
    public void setNotificante(UsuarioDTO notificante) {
        this.notificante = notificante;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.idNotificacion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NotificacionDTO other = (NotificacionDTO) obj;
        if (!Objects.equals(this.idNotificacion, other.idNotificacion)) {
            return false;
        }
        return true;
    }
    
    
}
