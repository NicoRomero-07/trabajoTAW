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
import trabajoTAW.entity.Producto;
import trabajoTAW.entity.Usuario;

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
    
    public List<Producto> getProductosPromocion(){
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where p.enPromocion = :enPromocion");
        
        q.setParameter("enPromocion", 1);
        
        List<Producto> lista = q.getResultList();
        
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista;
        } 
    }
}
