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
import trabajoTAW.dao.ListaProductoFacade;
import trabajoTAW.dao.ProductoFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.dto.ListaProductoDTO;
import trabajoTAW.entity.ListaProducto;
import trabajoTAW.entity.Producto;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author Victor
 */

@Stateless
public class ListaProductoService {
    @EJB ListaProductoFacade lpf;
    @EJB UsuarioFacade uf;
    @EJB ProductoFacade pf;
    
    private List<ListaProductoDTO> listaEntityADTO (List<ListaProducto> lista) {
        List<ListaProductoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (ListaProducto l:lista) {
                listaDTO.add(l.toDTO());
            }
        }
        return listaDTO;
    }
    
    private void rellenarListaProducto (ListaProducto listaProducto, String nombre, Usuario usuario, Producto producto) {
        
        listaProducto.setNombre(nombre);       
        listaProducto.setProducto1(producto);
        listaProducto.setUsuario1(usuario);
    }
    
    public void crearListaProducto(String nombre, Integer usuarioId, Integer productoId){
        ListaProducto lp = new ListaProducto();
        
        Usuario usuario = uf.find(usuarioId);
        Producto producto = pf.find(productoId);
        
        this.rellenarListaProducto(lp, nombre, usuario, producto);
        
        this.lpf.create(lp);
        
    }
}
