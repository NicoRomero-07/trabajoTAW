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
import trabajoTAW.entity.ListaUsuario;

/**
 *
 * @author nicor
 */
@Stateless
public class ListaUsuarioFacade extends AbstractFacade<ListaUsuario> {

    @PersistenceContext(unitName = "trabajoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListaUsuarioFacade() {
        super(ListaUsuario.class);
    }
    public List<ListaUsuario> findByNombre (String nombre) {
        Query q;
        q = this.getEntityManager().createQuery("SELECT l FROM ListaUsuario l WHERE l.nombre = :nombre");
        q.setParameter("nombre",nombre);
        return q.getResultList();
    }
    
    public List<ListaUsuario> findById (int id) {
        Query q;
        q = this.getEntityManager().createQuery("SELECT l FROM ListaUsuario l WHERE l.idListaUsuario = :id");
        q.setParameter("id",id);
        return q.getResultList();
    }
    
    public List<ListaUsuario> findByIdNombre(int id, String nombre){
        Query q;
        q = this.getEntityManager().createQuery("SELECT l FROM ListaUsuario l WHERE l.idListaUsuario = :id and l.nombre = :nombre");
        q.setParameter("id",id);
        q.setParameter("nombre", nombre);
        return q.getResultList();
    }
    
}
