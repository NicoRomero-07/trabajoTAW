/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dto;

import java.util.List;
//import trabajoTAW.entity.Usuario;

/**
 *
 * @author nicol
 */
public class ListaUsuarioDTO {
    
    private Integer idListaUsuario;
    private String nombre;
    private List<UsuarioDTO> usuarioList;
    private List<NotificacionDTO> notificacionList;

    public ListaUsuarioDTO() {
    }


    public Integer getIdListaUsuario() {
        return idListaUsuario;
    }

    public void setIdListaUsuario(Integer idListaUsuario) {
        this.idListaUsuario = idListaUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public List<NotificacionDTO> getNofificacionList(){
        return notificacionList;
    }
    public void setNotificacionList(List<NotificacionDTO> notificacionList) {
        this.notificacionList = notificacionList;
    }
      public List<UsuarioDTO> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<UsuarioDTO> usuarioList) {
        this.usuarioList = usuarioList;
    }

}

