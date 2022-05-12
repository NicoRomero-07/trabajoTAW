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
import trabajoTAW.entity.Direccion;

/**
 *
 * @author nicor
 */
@Stateless
public class DireccionFacade extends AbstractFacade<Direccion> {

    @PersistenceContext(unitName = "trabajoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DireccionFacade() {
        super(Direccion.class);
    }

    public List<Direccion> findByNombreDireccion(String filtroNombre) {
        Query q;
        q = this.getEntityManager().createQuery("select d from Direccion d where upper(d.calle) like upper(:calle)");
        q.setParameter("calle", '%' + filtroNombre +'%');
        return q.getResultList();
    }
    
    public Direccion findByCalleNumero(String calle, int numero) {
        Query q;
        q = this.getEntityManager().createQuery("select d from Direccion d where upper(d.calle) like upper(:calle) and d.numero = :numero");
        q.setParameter("calle", '%' + calle +'%');
        q.setParameter("numero",  numero);
        Direccion direccion = null;
        
        if(!q.getResultList().isEmpty()){
            direccion = (Direccion) q.getResultList().get(0);
        }
        return direccion;
    }
    
}
