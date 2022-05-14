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
import trabajoTAW.dao.DatosEstudioProductoFacade;
import trabajoTAW.dao.EstudioFacade;
import trabajoTAW.dao.ProductoFacade;
import trabajoTAW.dto.ProductoDTO;
import trabajoTAW.entity.DatosEstudioProducto;
import trabajoTAW.entity.Estudio;
import trabajoTAW.entity.Producto;

/**
 *
 * @author Victor
 */

@Stateless
public class ProductoService {
    
    @EJB ProductoFacade pf;
    @EJB EstudioFacade  ef;
    @EJB DatosEstudioProductoFacade  depf;
    
    public List<ProductoDTO> listaEntityADTO(List<Producto> lista) {
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
    
    public List<ProductoDTO> visualizarEstudio(Integer idEstudioProducto){
        DatosEstudioProducto estudioProducto = this.depf.find(idEstudioProducto);
        List<Producto> productos = pf.visualizarEstudio(estudioProducto);
        List<ProductoDTO> productosDTO = this.listaEntityADTO(productos);
        return productosDTO;
    }
    
}
