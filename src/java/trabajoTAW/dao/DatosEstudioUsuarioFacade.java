/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import trabajoTAW.entity.DatosEstudioUsuario;

/**
 *
 * @author nicol
 */
@Stateless
public class DatosEstudioUsuarioFacade extends AbstractFacade<DatosEstudioUsuario> {

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DatosEstudioUsuarioFacade() {
        super(DatosEstudioUsuario.class);
    }
    
}
