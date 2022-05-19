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
import trabajoTAW.dao.PujaFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.dto.ProductoDTO;
import trabajoTAW.dto.PujaDTO;
import trabajoTAW.entity.Producto;
import trabajoTAW.entity.Puja;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author Victor
 */
@Stateless
public class PujaService {
    
    @EJB PujaFacade pf;
    @EJB UsuarioFacade uf;
    @EJB ProductoFacade prf;
    
    private List<PujaDTO> listaEntityADTO (List<Puja> lista) {
        List<PujaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Puja p:lista) {
                listaDTO.add(p.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<PujaDTO> buscarPujas(Integer idProducto){
        List<Puja> lista = pf.buscarPujas(idProducto);
        return this.listaEntityADTO(lista);
    }
    
    private void rellenarPuja (Puja puja, Usuario comprador, Producto producto, double cantidad) {
        
        puja.setComprador(comprador);
        puja.setProducto(producto);
        puja.setCantidad(cantidad);
    }
    
    public void crearPuja(Integer usuarioId, Integer productoId, double cantidad){
        Puja puja = new Puja();
        
        Usuario usuario = uf.find(usuarioId);
        Producto producto = prf.find(productoId);
        
        this.rellenarPuja(puja, usuario, producto, cantidad);
        
        this.pf.create(puja);
        
    }
    
    public double calcularPrecioActual(List<PujaDTO> pujas, ProductoDTO producto){
        double precioActual = producto.getPrecioSalida();
        double cantidad = precioActual;
        for(PujaDTO p: pujas){
            cantidad = p.getCantidad();
           if(precioActual < cantidad){
               precioActual = cantidad;
           } 
        }
        return cantidad;
    }
}
