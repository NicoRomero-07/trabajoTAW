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
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import trabajoTAW.dao.CategoriaFacade;
import trabajoTAW.dao.DatosEstudioProductoFacade;
import trabajoTAW.dao.EstudioFacade;
import trabajoTAW.dao.ProductoFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.dto.ProductoDTO;
import trabajoTAW.dto.PujaDTO;
import trabajoTAW.dto.UsuarioDTO;
import trabajoTAW.entity.Categoria;
import trabajoTAW.entity.DatosEstudioProducto;
import trabajoTAW.entity.Estudio;
import trabajoTAW.entity.Producto;
import trabajoTAW.entity.Producto;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author Victor (58%) Alfonso (9%) Pablo (9%)
 */

@Stateless
public class ProductoService {
    
    @EJB ProductoFacade pf;
    @EJB EstudioFacade  ef;
    @EJB UsuarioFacade uf;
    @EJB CategoriaFacade cf;
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

    
    private void rellenarProducto (Producto producto,
                              String nombreProducto, String descripcion, Double precioSalida, String imagen, Date fechaInicio, Date fechaFin, String comprador, String publicador, Boolean promocion,  Integer categoria) {
        
        producto.setNombre(nombreProducto);
        producto.setDescripcion(descripcion);
        producto.setPrecioSalida(precioSalida);
        producto.setUrlFoto(imagen);
        producto.setFechaInicioSubasta(fechaInicio);
        producto.setFechaFinSubasta(fechaFin);
        
        if(!"".equals(comprador)){
            List<Usuario> usuarios = uf.findByNombreUsuario(comprador);
            if(!usuarios.isEmpty()){
               Usuario compradorUser = usuarios.get(0);
               producto.setComprador(compradorUser); 
            }
        }else{
            producto.setComprador(null);
        }

        if(!"".equals(publicador)){
            List<Usuario> usuarios = uf.findByNombreUsuario(publicador);
            if(!usuarios.isEmpty()){
                Usuario publicadorUser = usuarios.get(0);
                producto.setPublicador(publicadorUser);
            }
        }
        
        producto.setEnPromocion(promocion);
          
        producto.setCategoria(categoria);
                      
    }
    
    public void crearProducto (String nombreProducto, String descripcion, Double precioSalida, String imagen, Date fechaInicio, Date fechaFin, String comprador, String publicador, Boolean promocion,  Integer categoria) {
        Producto producto = new Producto();

        this.rellenarProducto(producto, nombreProducto, descripcion, precioSalida, imagen, fechaInicio, fechaFin, comprador, publicador, promocion, categoria);

        this.pf.create(producto);
    }

    public void modificarProducto (Integer id,
                              String nombreProducto, String descripcion, Double precioSalida, String imagen, Date fechaInicio, Date fechaFin, String comprador, String publicador, Boolean promocion, Integer categoria) {
        
        Producto producto = this.pf.find(id);

        this.rellenarProducto(producto, nombreProducto, descripcion, precioSalida, imagen, fechaInicio, fechaFin, comprador, publicador, promocion, categoria);

        this.pf.edit(producto);
    }
    
    public ProductoDTO buscarProducto(Integer id){
        Producto p = pf.find(id);
        return p.toDTO();
    }

    public List<ProductoDTO> listaProductosLogin(Integer idUsuario) {
        return this.listaEntityADTO(this.pf.getProductoPublicadorId(idUsuario));
    }
    
    public void borrarProducto (Integer id) {
        Producto producto = this.pf.find(id);

        this.pf.remove(producto);        
    }
    
    public List<ProductoDTO> buscarProductosComprados(Integer idProducto){
        List<Producto> productos = pf.productosComprados(idProducto);
        return this.listaEntityADTO(productos);
    }
    

    public List<ProductoDTO> buscarProductosPujados(Integer idUsuario){
        List<Producto> productos = pf.productosPujados(idUsuario);
        return this.listaEntityADTO(productos);
    }
    

    public List<ProductoDTO> filtrarProductosComprados(Integer idProducto, String filtro){
        List<Producto> productos = null;

        if (filtro == null || filtro.isEmpty()) {
            productos = this.pf.filtrarProductosComprados(idProducto, null);
        } else {
            productos = this.pf.filtrarProductosComprados(idProducto, filtro);
        }
        
        return this.listaEntityADTO(productos); 
    }
    
    public List<ProductoDTO> visualizarEstudio(Integer idEstudioProducto){
        DatosEstudioProducto estudioProducto = this.depf.find(idEstudioProducto);
        List<Producto> productos = pf.visualizarEstudio(estudioProducto);
        List<ProductoDTO> productosDTO = this.listaEntityADTO(productos);
        return productosDTO;
    }
    public List<ProductoDTO> getProductosEnPromocion(){
        return this.listaEntityADTO(this.pf.getProductosPromocion());
    }
}
