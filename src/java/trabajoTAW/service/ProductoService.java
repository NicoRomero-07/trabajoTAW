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
import trabajoTAW.dao.ProductoFacade;
import trabajoTAW.dto.ProductoDTO;
import trabajoTAW.entity.Producto;

/**
 *
 * @author Victor
 */

@Stateless
public class ProductoService {
    
    @EJB ProductoFacade pf;
    
    private List<ProductoDTO> listaEntityADTO(List<Producto> lista) {
        List<ProductoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Producto p:lista) {
                listaDTO.add(p.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<ProductoDTO> listarProductos(String busqueda) {
        List<Producto> productos = null;

        if (busqueda == null || busqueda.isEmpty()) {
            productos = this.pf.findAll();        
        } else {
            productos = this.pf.findByNombreProducto(busqueda);
        }
        
        return this.listaEntityADTO(productos);                
    } 
    
    public ProductoDTO buscarProducto(Integer id){
        Producto p = pf.find(id);
        return p.toDTO();
    }
    
}
