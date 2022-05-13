/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author nicol
 */
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
}
