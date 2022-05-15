/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import trabajoTAW.dao.CategoriaFacade;
import trabajoTAW.dao.DireccionFacade;
import trabajoTAW.dao.ListaUsuarioFacade;
import trabajoTAW.dao.TipoUsuarioFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.dto.CategoriaDTO;
import trabajoTAW.dto.ListaUsuarioDTO;
import trabajoTAW.dto.NotificacionDTO;
import trabajoTAW.dto.UsuarioDTO;
import trabajoTAW.entity.Categoria;
import trabajoTAW.entity.TipoUsuario;
import trabajoTAW.entity.Usuario;
import trabajoTAW.entity.Direccion;
import trabajoTAW.entity.ListaUsuario;
import trabajoTAW.entity.Notificacion;
/**
 *
 * @author nicor
 */
@Stateless
public class UsuarioService {
    @EJB TipoUsuarioFacade tuf;
    @EJB CategoriaFacade cf;
    @EJB UsuarioFacade uf;
    @EJB DireccionFacade df;
    @EJB CategoriaService cs;
    @EJB ListaUsuarioFacade lf;
    
    private List<UsuarioDTO> listaEntityADTO (List<Usuario> lista) {
        List<UsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Usuario usuario:lista) {
                listaDTO.add(usuario.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<UsuarioDTO> listarUsuarios (String filtroNombre) {
        List<Usuario> usuarios = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            usuarios = this.uf.findAll();        
        } else {
            usuarios = this.uf.findByNombreUsuario(filtroNombre);
        }
        
        return this.listaEntityADTO(usuarios);                
    } 
    public List<UsuarioDTO> getCompradores(){
        List<Usuario> compradores = this.uf.getCompradores();
        return listaEntityADTO(compradores);
    }
    
    public UsuarioDTO buscarUsuario (Integer id) {
        Usuario usuario = this.uf.find(id);
        return usuario.toDTO();
    }
    
    public void borrarUsuario (Integer id) {
        Usuario usuario = this.uf.find(id);

        this.uf.remove(usuario);        
    }
    
    private void rellenarUsuario (Usuario usuario,
                              String nombreUsuario, String contrasenya, String nombre, String primerApellido, 
                              String segundoApellido, String email, Integer direccion, Character sexo, 
                              Integer tipoUsuario, Date fechaNacimiento, String[] categoriasStr) {
        
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasenya(contrasenya);
        usuario.setNombre(nombre);
        usuario.setPrimerApellido(primerApellido);
        usuario.setSegundoApellido(segundoApellido);
        usuario.setEmail(email);
        usuario.setSexo(sexo);
        List<Categoria> categorias = new ArrayList<>();
        if(categoriasStr!=null){
            for(String c : categoriasStr){
                Categoria ca = this.cf.find(Integer.parseInt(c));
                categorias.add(ca);
            }
        }
            
        usuario.setCategoriaList(categorias);
        
        /*
        List<ListaUsuario> listas = new ArrayList<>();
        if(listasStr!=null){
            for(String c : listasStr){
                ListaUsuario l = lf.find(Integer.parseInt(c));
                listas.add(l);
            }
        }

        
        usuario.setListaUsuarioList(listas);
        */
        TipoUsuario tu = this.tuf.find(tipoUsuario);
        usuario.setTipoUsuario(tu);
        
        usuario.setFechaNacimiento(fechaNacimiento);
        Direccion dd = this.df.find(direccion);
        
        usuario.setDireccion(dd);
                       
    }
    
    public void crearUsuario (String nombreUsuario, String contrasenya, String nombre, String primerApellido, 
                              String segundoApellido, String email, Integer direccion, Character sexo, 
                              Integer tipoUsuario, Date fechaNacimiento,String[] categoriasStr) {
        Usuario usuario = new Usuario();

        this.rellenarUsuario(usuario, nombreUsuario, contrasenya, nombre, primerApellido, segundoApellido, email, direccion, sexo, tipoUsuario, fechaNacimiento,categoriasStr);

        this.uf.create(usuario);
    }

    public void modificarUsuario (Integer id,
                              String nombreUsuario, String contrasenya, String nombre, String primerApellido, 
                              String segundoApellido, String email, Integer direccion, Character sexo, 
                              Integer tipoUsuario, Date fechaNacimiento,String[] categoriasStr) {
        
        Usuario usuario = this.uf.find(id);

        this.rellenarUsuario(usuario, nombreUsuario, contrasenya, nombre, primerApellido,
                segundoApellido, email, direccion, sexo, tipoUsuario, fechaNacimiento, categoriasStr);

        this.uf.edit(usuario);
    }
    
    public void modificarUsuario(Integer id, List<Integer> lista){
        
        Usuario usuario = this.uf.find(id);
        
        List<ListaUsuario> listas = new ArrayList<>();
        for(Integer c : lista){
            ListaUsuario l = this.lf.find(c);
            listas.add(l);
        }
        usuario.setListaUsuarioList(listas);
        
        this.uf.edit(usuario);
    }
    
    public List<CategoriaDTO> categoriasUsuario(Integer id){
        Usuario usuario = this.uf.find(id);
        List<CategoriaDTO> categoriasDTO = new ArrayList();
        List<Categoria> categorias = usuario.getCategoriaList();
        
        for(Categoria c : categorias){
            categoriasDTO.add(c.toDTO());
        }
        return categoriasDTO;
    }
    
    public List<ListaUsuarioDTO> listasUsuario(Integer id){
        Usuario usuario = this.uf.find(id);
        List<ListaUsuarioDTO> listaDTO = new ArrayList();
        List<ListaUsuario> listas = usuario.getListaUsuarioList();
        
        for(ListaUsuario c : listas){
            listaDTO.add(c.toDTO());
        }
        return listaDTO;
    }
    
        public List<NotificacionDTO> notificacionesUsuario (Integer id){
        Usuario usuario = this.uf.find(id);
        List<NotificacionDTO> notificacionDTO = new ArrayList();
        List<Notificacion> notificaciones = usuario.getNotificacionList();
        
        for(Notificacion c : notificaciones){
            notificacionDTO.add(c.toDTO());
        }
        return notificacionDTO;
    }
}
