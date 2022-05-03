/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dao;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
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
    
    public List<Producto> getProductoPublicadorId(HttpSession session) {
        Query query = getEntityManager().createQuery("select p FROM Producto p where p.publicador = :publicadorid");
        Usuario user = (Usuario) session.getAttribute("usuario");
        query.setParameter("publicadorid", user.getIdUsuario());
        return query.getResultList();
    }
    
    public List<Producto> findByNombreProducto (String nombre) {
        Query q;
        q = this.getEntityManager().createQuery("select p from Producto p where upper(p.nombre) like upper(:nombre)");
        q.setParameter("nombre", '%' + nombre +'%');
        return q.getResultList();
    }
    
}
