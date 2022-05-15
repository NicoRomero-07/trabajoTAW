/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import trabajoTAW.dao.ListaUsuarioFacade;
import trabajoTAW.dao.NotificacionFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.dto.NotificacionDTO;
import trabajoTAW.dto.UsuarioDTO;
import trabajoTAW.entity.Notificacion;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author nicol
 */
@Stateless
public class NotificacionService {
    @EJB NotificacionFacade notificacionFacade;
    @EJB UsuarioFacade usuarioFacade;
    @EJB ListaUsuarioFacade listaUsuarioFacade;

    public List<NotificacionDTO> listaEntityADTO(List<Notificacion> lista) {
        List<NotificacionDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Notificacion notificacion:lista) {
                listaDTO.add(notificacion.toDTO());
            }
        }
        return listaDTO;
    }
    public List<NotificacionDTO> listarNotificaciones () {
        return this.listaEntityADTO(this.notificacionFacade.findAll());                
    } 
    
    public NotificacionDTO buscarNotificacion (Integer id) {
        Notificacion notificacion = this.notificacionFacade.find(id);
        return notificacion.toDTO();
    }
    
    public void borrarNotificacion (Integer id) {
        Notificacion notificacion = this.notificacionFacade.find(id);

        this.notificacionFacade.remove(notificacion);        
    }
    
    
    private void rellenarNotificacion (Notificacion notificacion,
                              String contenido, Date fechaEnvio, Integer notificante) {
        
        notificacion.setContenido(contenido);   
        notificacion.setFechaEnvio(fechaEnvio);
        Usuario n = this.usuarioFacade.find(notificante);
        notificacion.setNotificante(n);
        
    }
    
    public void crearNotificacion (String contenido, Date fechaEnvio, Integer notificante) {
        Notificacion notificacion = new Notificacion();

        this.rellenarNotificacion(notificacion, contenido, fechaEnvio, notificante);

        this.notificacionFacade.create(notificacion);
    }

    public void modificarNotificacion(Integer id,
                              String contenido, Date fechaEnvio, Integer notificante) {
        
        Notificacion notificacion = this.notificacionFacade.find(id);

        this.rellenarNotificacion(notificacion, contenido, fechaEnvio, notificante);

        this.notificacionFacade.edit(notificacion);
    }
    
    public void modificarNotificacion(Integer id,
                              List<Integer> usuariosId) {
        
        Notificacion notificacion = this.notificacionFacade.find(id);
        List<Usuario> usuarios = null;
        if (usuariosId != null && !usuariosId.isEmpty()){
            usuarios = new ArrayList();
            for (Integer usuario: usuariosId){
                usuarios.add(this.usuarioFacade.find(usuario));
            }
            
        }
        notificacion.setUsuarioList(usuarios);
        this.notificacionFacade.edit(notificacion);
    }
    
    public List<UsuarioDTO> receptores(Integer id){
        Notificacion notificacion = this.notificacionFacade.find(id);

        List<UsuarioDTO> usuariosDTO = new ArrayList();
        for (Usuario usuario : notificacion.getUsuarioList()){
            usuariosDTO.add(usuario.toDTO());
        }
        
        return usuariosDTO;
    }
    
    public NotificacionDTO notificacionReciente(){
         return this.notificacionFacade.findRecent().toDTO();
    }
    
    
}