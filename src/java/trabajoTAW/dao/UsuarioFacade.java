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
import trabajoTAW.entity.Usuario;

/**
 *
 * @author nicor
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "trabajoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario comprobarUsuario(String strusuario, String strclave) {
        Query q;
        
        q = this.getEntityManager().createQuery("select u from Usuario u where u.nombreUsuario = :usuario and"
                + " u.contrasenya = :clave");
        
        q.setParameter("usuario", strusuario);
        q.setParameter("clave", strclave);
        List<Usuario> lista = q.getResultList();
        
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        } 
    }
    public List<Usuario> findByNombreUsuario (String nombre) {
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.nombreUsuario like :nombre");
        q.setParameter("nombre", '%' + nombre +'%');
        return q.getResultList();
    }
    
    public List<Usuario> getCompradores() {
        Query q;
        Integer idTipoComprador = 3;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.tipoUsuario.idTipoUsuario = :tipo");
        
        q.setParameter("tipo", idTipoComprador);
        
        List<Usuario> lista = q.getResultList();
        
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista;
        } 
    }
    
    
}
