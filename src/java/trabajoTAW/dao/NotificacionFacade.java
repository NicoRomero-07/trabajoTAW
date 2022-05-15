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
import trabajoTAW.entity.Notificacion;
import trabajoTAW.entity.Usuario;


@Stateless
public class NotificacionFacade extends AbstractFacade<Notificacion> {

    @PersistenceContext(unitName = "trabajoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotificacionFacade() {
        super(Notificacion.class);
    }
    
    public Notificacion findRecent(){
        Query q;
        q = this.getEntityManager().createQuery("SELECT l FROM Notificacion l ORDER BY l.idNotificacion DESC");
        return (Notificacion)q.getResultList().get(0);
    }
    
}
