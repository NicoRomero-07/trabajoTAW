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
import trabajoTAW.entity.TipoUsuario;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author nicor
 */
@Stateless
public class TipoUsuarioFacade extends AbstractFacade<TipoUsuario> {

    @PersistenceContext(unitName = "trabajoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<TipoUsuario> findByTipo (String tipo) {
        Query q;
        q = this.getEntityManager().createQuery("SELECT t FROM TipoUsuario t WHERE t.tipo = :tipo");
        q.setParameter("tipo", '%' + tipo +'%');
        return q.getResultList();
    }

    public TipoUsuarioFacade() {
        super(TipoUsuario.class);
    }
    
}
