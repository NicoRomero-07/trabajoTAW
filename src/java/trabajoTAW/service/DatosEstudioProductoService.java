/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import trabajoTAW.dao.DatosEstudioProductoFacade;
import trabajoTAW.dao.EstudioFacade;
import trabajoTAW.dto.DatosEstudioProductoDTO;
import trabajoTAW.entity.DatosEstudioProducto;
import trabajoTAW.entity.Estudio;

/**
 *
 * @author Alfonso 100%
 */
@Stateless
public class DatosEstudioProductoService {
    
    @EJB DatosEstudioProductoFacade estudioProductoFacade;
    @EJB EstudioFacade estudioFacade;
    
    public DatosEstudioProductoDTO find(Integer idEstudio){
        DatosEstudioProducto estudioProducto = estudioProductoFacade.find(idEstudio);
        return estudioProducto == null ? null : estudioProducto.toDTO();
    }
    
    public void remove(Integer idEstudio){
        DatosEstudioProducto estudioProducto = estudioProductoFacade.find(idEstudio);
        estudioProductoFacade.remove(estudioProducto);
    }
    
    public void create(Boolean categorias,Boolean vendidos,Boolean promocion,
            Double precioSalida,Double precioActual,String idEstudio){
        
        
        DatosEstudioProducto estudioProducto = new DatosEstudioProducto();
        
        estudioProducto = rellenarEstudioProducto(estudioProducto,categorias,
                vendidos,promocion,precioSalida,precioActual,idEstudio);
        
        estudioProductoFacade.create(estudioProducto);
        
    }
    
    public void edit(String strIdEstudioProducto,Boolean categorias,
            Boolean vendidos,Boolean promocion,Double precioSalida,
            Double precioActual,String idEstudio){
        
        
        DatosEstudioProducto estudioProducto = estudioProductoFacade.find(Integer.parseInt(strIdEstudioProducto));
        
        estudioProducto = rellenarEstudioProducto(estudioProducto,categorias,
                vendidos,promocion,precioSalida,precioActual,idEstudio);
        
        estudioProductoFacade.edit(estudioProducto);
        
    }
    
    private DatosEstudioProducto rellenarEstudioProducto(
            DatosEstudioProducto estudioProducto,Boolean categorias,
            Boolean vendidos,Boolean promocion,Double precioSalida,
            Double precioActual,String idEstudio){
        Estudio estudio = this.estudioFacade.find(Integer.parseInt(idEstudio));
        estudioProducto.setId(estudio.getIdEstudio());
        estudioProducto.setCategorias(categorias);
        estudioProducto.setVendidos(vendidos);
        estudioProducto.setPromocion(promocion);
        estudioProducto.setPrecioActual(precioActual);
        estudioProducto.setPrecioSalida(precioSalida);
        return estudioProducto;
    }
    
}
