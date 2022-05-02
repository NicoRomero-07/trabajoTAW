/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dao;

import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import trabajoTAW.entity.DatosEstudioProducto;
import trabajoTAW.entity.Producto;

/**
 *
 * @author nicor
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "trabajoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
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
        
        consulta.append("SELECT p FROM Producto p WHERE ");
        consulta.append(dprecioSalida != null ? "p.precioSalida >= " + dprecioSalida  + " AND " : "");
        consulta.append("p.enPromocion = " + bpromocion);
        consulta.append(Objects.equals(bcategorias, Boolean.TRUE) ? " ORDER BY p.categoria ASC" : "");
        return consulta.toString();
    }
    
}
