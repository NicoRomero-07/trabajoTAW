/**
 *
 * @author Nicolás Zhao(100%)
 */
package trabajoTAW.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import trabajoTAW.dao.ListaUsuarioFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.dto.ListaUsuarioDTO;
import trabajoTAW.dto.UsuarioDTO;
import trabajoTAW.entity.ListaUsuario;
import trabajoTAW.entity.Usuario;


@Stateless
public class ListaUsuarioService {
    @EJB ListaUsuarioFacade listaUsuarioFacade;
    @EJB UsuarioFacade usuarioFacade;

    public List<ListaUsuarioDTO> listaEntityADTO(List<ListaUsuario> lista) {
        List<ListaUsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (ListaUsuario lu:lista) {
                listaDTO.add(lu.toDTO());
            }
        }
        return listaDTO;
    }
    public List<ListaUsuarioDTO> listarListas (String filtroNombre) {
        List<ListaUsuario> listas = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            listas = this.listaUsuarioFacade.findAll();        
        } else {
            listas = this.listaUsuarioFacade.findByNombre(filtroNombre);
        }
        
        return this.listaEntityADTO(listas);                
    } 
    
    public ListaUsuarioDTO buscarLista (Integer id) {
        ListaUsuario lista = this.listaUsuarioFacade.find(id);
        return lista.toDTO();
    }
    
    public void borrarLista (Integer id) {
        ListaUsuario lista = this.listaUsuarioFacade.find(id);

        this.listaUsuarioFacade.remove(lista);        
    }
    
    
    private void rellenarLista (ListaUsuario lista,
                              String nombre, String[] listas) {
        
        lista.setNombre(nombre);
        List<Usuario> listaUsuarios = new ArrayList<>();
        for (String i : listas){
            Usuario l = this.usuarioFacade.find(Integer.parseInt(i));
            listaUsuarios.add(l);
        }
        lista.setUsuarioList(listaUsuarios);               
    }
    
    public ListaUsuarioDTO crearLista (String nombre,String[] listas) {
        ListaUsuario lista = new ListaUsuario();

        this.rellenarLista(lista, nombre,listas);

        this.listaUsuarioFacade.create(lista);
        return lista.toDTO();
    }

    
    public void modificarLista(Integer id, String nombre, String[] listas){
        ListaUsuario lista = this.listaUsuarioFacade.find(id);
        
        this.rellenarLista(lista, nombre,listas);
        
        this.listaUsuarioFacade.edit(lista);
    }
    
    
    public List<UsuarioDTO> usuariosRelacionados(Integer id){
        ListaUsuario listaUsuario = this.listaUsuarioFacade.find(id);
        List<UsuarioDTO> usuariosDTO = new ArrayList();
        List<Usuario> usuarios = listaUsuario.getUsuarioList();
        
        for(Usuario c : usuarios){
            usuariosDTO.add(c.toDTO());
        }
        return usuariosDTO;
    }
    
}
