/**
 * @author Nicolás Zhao (100%)
 */
package trabajoTAW.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import trabajoTAW.entity.ListaUsuario;


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
        q = this.getEntityManager().createQuery("SELECT l FROM ListaUsuario l WHERE l.nombre LIKE :nombre");
        q.setParameter("nombre","%"+nombre+"%");
        return q.getResultList();
    }   
    
    public ListaUsuario findRecent(){
        Query q;
        q = this.getEntityManager().createQuery("SELECT l FROM ListaUsuario l ORDER BY l.idListaUsuario DESC");
        return (ListaUsuario)q.getResultList().get(0);
    }
}
