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
import trabajoTAW.dto.PujaDTO;
import trabajoTAW.entity.Puja;

/**
 *
 * @author nicor
 */
@Stateless
public class PujaFacade extends AbstractFacade<Puja> {

    @PersistenceContext(unitName = "trabajoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PujaFacade() {
        super(Puja.class);
    }
    
    public List<Puja> buscarPujas(Integer productoId){
        Query q;
        q = this.getEntityManager().createQuery("select pu from Puja pu where"
                + " pu.producto.idProducto = :productoId");
        q.setParameter("productoId", productoId);
        return q.getResultList();
    } 
    
}
