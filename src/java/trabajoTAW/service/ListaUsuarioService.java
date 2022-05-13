/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import trabajoTAW.dao.ListaUsuarioFacade;
import trabajoTAW.dto.ListaUsuarioDTO;
import trabajoTAW.entity.ListaUsuario;

/**
 *
 * @author nicol
 */
@Stateless
public class ListaUsuarioService {
    @EJB ListaUsuarioFacade listaUsuarioFacade;

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
                              String nombre) {
        
        lista.setNombre(nombre);               
    }
    
    public void crearLista (String nombre) {
        ListaUsuario lista = new ListaUsuario();

        this.rellenarLista(lista, nombre);

        this.listaUsuarioFacade.create(lista);
    }

    public void modificarLista(Integer id,
                              String nombre) {
        
        ListaUsuario lista = this.listaUsuarioFacade.find(id);

        this.rellenarLista(lista, nombre);

        this.listaUsuarioFacade.edit(lista);
    }
    
    
}
