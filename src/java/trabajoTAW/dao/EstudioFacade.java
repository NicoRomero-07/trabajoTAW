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
import trabajoTAW.entity.Estudio;

/**
 *
 * @author nicor
 */
@Stateless
public class EstudioFacade extends AbstractFacade<Estudio> {

    @PersistenceContext(unitName = "trabajoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudioFacade() {
        super(Estudio.class);
    }
    
    public List<Estudio> findByNombre(String nombre) {
        Query q;
        q = this.getEntityManager().createQuery("select e from Estudio e where upper(e.nombre) like upper(:nombre)");
        q.setParameter("nombre", '%' + nombre +'%');
        return q.getResultList();
    }
}
