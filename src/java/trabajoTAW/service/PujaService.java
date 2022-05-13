/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import trabajoTAW.dao.PujaFacade;
import trabajoTAW.dto.PujaDTO;
import trabajoTAW.entity.Puja;

/**
 *
 * @author Victor
 */
@Stateless
public class PujaService {
    
    @EJB PujaFacade pf;
    
    private List<PujaDTO> listaEntityADTO (List<Puja> lista) {
        List<PujaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Puja p:lista) {
                listaDTO.add(p.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<PujaDTO> buscarPujas(Integer idProducto){
        List<Puja> lista = pf.buscarPujas(idProducto);
        return this.listaEntityADTO(lista);
    }
    
    public double calcularPrecioActual(List<PujaDTO> pujas){
        double precioActual = 0;
        double cantidad = 0;
        for(PujaDTO p: pujas){
            cantidad = p.getCantidad();
           if(precioActual < cantidad){
               precioActual = cantidad;
           } 
        }
        return cantidad;
    }
    
}
