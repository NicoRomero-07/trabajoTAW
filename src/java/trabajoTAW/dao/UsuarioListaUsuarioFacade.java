/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import trabajoTAW.entity.UsuarioListaUsuario;

/**
 *
 * @author nicor
 */
@Stateless
public class UsuarioListaUsuarioFacade extends AbstractFacade<UsuarioListaUsuario> {

    @PersistenceContext(unitName = "trabajoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioListaUsuarioFacade() {
        super(UsuarioListaUsuario.class);
    }
    
}
