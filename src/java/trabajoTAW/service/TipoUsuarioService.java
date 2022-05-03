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
import trabajoTAW.dao.TipoUsuarioFacade;
import trabajoTAW.dto.TipoUsuarioDTO;
import trabajoTAW.entity.TipoUsuario;

/**
 *
 * @author nicor
 */
@Stateless
public class TipoUsuarioService {
    @EJB TipoUsuarioFacade tuf;
    private List<TipoUsuarioDTO> listaEntityADTO (List<TipoUsuario> lista) {
        List<TipoUsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (TipoUsuario tipoUsuario:lista) {
                listaDTO.add(tipoUsuario.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<TipoUsuarioDTO> listarTipoUsuarios (String filtroNombre) {
        List<TipoUsuario> tipoUsuarios = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            tipoUsuarios = this.tuf.findAll();        
        } else {
            tipoUsuarios = this.tuf.findByTipo(filtroNombre);
        }
        
        return this.listaEntityADTO(tipoUsuarios);                
    } 
    
    public TipoUsuarioDTO buscarTipoUsuario (Integer id) {
        TipoUsuario tipoUsuario = this.tuf.find(id);
        return tipoUsuario.toDTO();
    }
    
    public void borrarTipoUsuario (Integer id) {
        TipoUsuario tipoUsuario = this.tuf.find(id);

        this.tuf.remove(tipoUsuario);        
    }
    
    private void rellenarTipoUsuario (TipoUsuario tipoUsuario,
                              String nombreTipoUsuario) {
        
        tipoUsuario.setTipo(nombreTipoUsuario);
        
                       
    }
    
    public void crearTipoUsuario (String nombreTipoUsuario) {
        TipoUsuario tipoUsuario = new TipoUsuario();

        this.rellenarTipoUsuario(tipoUsuario, nombreTipoUsuario);

        this.tuf.create(tipoUsuario);
    }

    public void modificarTipoUsuario (Integer id,
                              String nombreTipoUsuario) {
        
        TipoUsuario tipoUsuario = this.tuf.find(id);

        this.rellenarTipoUsuario(tipoUsuario, nombreTipoUsuario);

        this.tuf.edit(tipoUsuario);
    }
}
