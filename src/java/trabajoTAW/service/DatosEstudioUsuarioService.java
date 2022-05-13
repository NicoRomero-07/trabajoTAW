/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import trabajoTAW.dao.DatosEstudioUsuarioFacade;
import trabajoTAW.dao.EstudioFacade;
import trabajoTAW.dto.DatosEstudioUsuarioDTO;
import trabajoTAW.entity.DatosEstudioUsuario;
import trabajoTAW.entity.Estudio;

/**
 *
 * @author Alfonso
 */
@Stateless
public class DatosEstudioUsuarioService {
    
    @EJB DatosEstudioUsuarioFacade estudioUsuarioFacade;
    @EJB EstudioFacade estudioFacade;
    
    public DatosEstudioUsuarioDTO find(Integer idEstudio){
        DatosEstudioUsuario estudioUsuario = estudioUsuarioFacade.find(idEstudio);
        return estudioUsuario == null ? null : estudioUsuario.toDTO();
    }
    
    public void remove(Integer idEstudio){
        DatosEstudioUsuario estudioUsuario = estudioUsuarioFacade.find(idEstudio);
        estudioUsuarioFacade.remove(estudioUsuario);
    }
    
    public void create(Boolean nombre,Boolean apellidos,Boolean ingresos,
            Boolean ascendente,String idEstudio){
        
        
        DatosEstudioUsuario estudioUsuario = new DatosEstudioUsuario();
        
        estudioUsuario = rellenarEstudioUsuario(estudioUsuario,
                nombre,apellidos,ingresos,ascendente,idEstudio);
        
        estudioUsuarioFacade.create(estudioUsuario);
        
    }
    
    public void edit(String strIdEstudioUsuario,Boolean nombre,Boolean apellidos,
            Boolean ingresos,Boolean ascendente,String idEstudio){
        
        
        DatosEstudioUsuario estudioUsuario = estudioUsuarioFacade.find(Integer.parseInt(strIdEstudioUsuario));
        
        estudioUsuario = rellenarEstudioUsuario(estudioUsuario,
                nombre,apellidos,ingresos,ascendente,idEstudio);
        
        estudioUsuarioFacade.edit(estudioUsuario);
        
    }
    
    private DatosEstudioUsuario rellenarEstudioUsuario(
            DatosEstudioUsuario estudioUsuario,Boolean nombre,
            Boolean apellidos,Boolean ingresos,Boolean ascendente,String idEstudio){
         
        estudioUsuario.setNombre(nombre);
        estudioUsuario.setApellidos(apellidos);
        estudioUsuario.setIngresos(ingresos);
        estudioUsuario.setAscendente(ingresos);
        Estudio estudio = this.estudioFacade.find(Integer.parseInt(idEstudio));
        estudio.setDatosEstudioUsuario(estudioUsuario);
        estudioFacade.edit(estudio);
        estudioUsuario.setEstudio(estudio);
        return estudioUsuario;
    }
    
}
