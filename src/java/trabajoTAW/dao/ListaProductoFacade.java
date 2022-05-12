/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import trabajoTAW.dao.AbstractFacade;
import trabajoTAW.entity.ListaProducto;
import trabajoTAW.entity.Producto;

/**
 *
 * @author Victor
 */
@Stateless
public class ListaProductoFacade extends AbstractFacade<ListaProducto> {

    @PersistenceContext(unitName = "trabajoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListaProductoFacade() {
        super(ListaProducto.class);
    }
    
    public List<Producto> ListaFavoritoUsuario(Integer usuarioId){
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p join lp from ListaProducto lp on"
                + " p.idProducto = lp.producto join u from Usuario u on lp.usuario = u.usuario");
        return q.getResultList();
        
    }
    
}
