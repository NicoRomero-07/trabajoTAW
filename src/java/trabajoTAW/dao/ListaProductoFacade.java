/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import trabajoTAW.dao.AbstractFacade;
import trabajoTAW.entity.ListaProducto;

/**
 *
 * @author Victor
 */
@Stateless
public class ListaProductoFacade extends AbstractFacade<ListaProducto> {

    @PersistenceContext(unitName = "trabajoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListaProductoFacade() {
        super(ListaProducto.class);
    }
    
}