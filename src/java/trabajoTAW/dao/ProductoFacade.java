/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dao;

import java.util.Objects;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import trabajoTAW.entity.DatosEstudioProducto;
import javax.servlet.http.HttpSession;
import trabajoTAW.dto.UsuarioDTO;
import trabajoTAW.entity.Producto;
import trabajoTAW.entity.Usuario;
import trabajoTAW.dao.UsuarioFacade;

/**
 *
 * @author Alfonso (87,5%) , Nicolás Zhao (12,5%)
 * 
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {
    
    @EJB UsuarioFacade uf;

    @PersistenceContext(unitName = "trabajoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    public List<Producto> getProductosPromocion(){
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.enPromocion = :enPromocion");
        
        q.setParameter("enPromocion", true);
        
        List<Producto> lista = q.getResultList();
        
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista;
        } 
    }
    public List<Producto> visualizarEstudio(DatosEstudioProducto estudioProducto){
        Query q;
        String consulta = generarConsulta(estudioProducto);
        q = this.getEntityManager().createQuery(consulta);
        return q.getResultList();
    }
    
    private String generarConsulta(DatosEstudioProducto estudioProducto){
        StringBuilder consulta = new StringBuilder();
        
        Double dprecioSalida = estudioProducto.getPrecioSalida();
        Double dprecioActual = estudioProducto.getPrecioActual();
        Boolean bpromocion = estudioProducto.getPromocion();
        Boolean bvendidos = estudioProducto.getVendidos();
        Boolean bcategorias = estudioProducto.getCategorias();
        
        consulta.append("SELECT p FROM Producto p ");
        if(bcategorias){
            consulta.append(" JOIN Categoria c ON c.idCategoria = p.categoria ");
        }
        if(dprecioActual != null){
            consulta.append(" JOIN Puja pu ON p.productoId = pu.producto.productoId ");
        }
        if((dprecioSalida != null || dprecioActual != null || bpromocion || !bvendidos) ){
            consulta.append(" WHERE ");
        }
        consulta.append(dprecioSalida != null ? " p.precioSalida >= " + dprecioSalida  + " AND " : "");
        consulta.append(dprecioActual != null ? "pu.cantidad IN "
                + "(SELECT MAX(pu.cantidad) FROM Puja pu GROUP BY pu.producto) "
                + "AND pu.cantidad >= " + dprecioActual + " AND " : "");
        consulta.append(Objects.equals(bpromocion, Boolean.TRUE) ? " p.enPromocion = " + bpromocion + " AND " : "");
        consulta.append(Objects.equals(bvendidos, Boolean.FALSE) ? " p.comprador IS NULL AND " : "");
        
        if((dprecioSalida != null || dprecioActual != null || bpromocion || !bvendidos) ){
            quitarAND(consulta);
        }
        
        if(bcategorias){
            consulta.append(Objects.equals(bcategorias, Boolean.TRUE) ? " GROUP BY p" : "");
        }
        return consulta.toString();
    }
    
    public List<Producto> getProductoPublicadorId(Integer idUsuario) {
        Query query = getEntityManager().createQuery("select p FROM Producto p where p.publicador.idUsuario = :publicadorid");
        query.setParameter("publicadorid", idUsuario);
        return query.getResultList();
    }
    
    private void quitarAND(StringBuilder consulta){
        consulta.deleteCharAt(consulta.length() -1);
        consulta.deleteCharAt(consulta.length() -1);
        consulta.deleteCharAt(consulta.length() -1);
        consulta.deleteCharAt(consulta.length() -1);
    }
       
    public List<Producto> findByNombreProducto (String nombre) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where upper(p.nombre) like upper(:nombre)");
        q.setParameter("nombre", '%' + nombre +'%');
        return q.getResultList();
    }

    
    public List<Producto> productosComprados(Integer idUsuario){
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.comprador.idUsuario = :idUsuario");
        q.setParameter("idUsuario", idUsuario);
        return q.getResultList();
    }
    
    public List<Producto> filtrarProductosComprados(Integer idUsuario, String filtro){
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.comprador.idUsuario = :idUsuario and"
                + " upper(p.nombre) like upper(:filtro)");
        q.setParameter("idUsuario", idUsuario);
        q.setParameter("filtro", '%' + filtro +'%');
        return q.getResultList();
    }
    
    public List<Producto> productosPujados(Integer idUsuario){
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p join Puja pu on"
                + " p.idProducto = pu.producto.idProducto where pu.comprador.idUsuario = :idUsuario");
        q.setParameter("idUsuario", idUsuario);
        return q.getResultList();
    }
    
}
