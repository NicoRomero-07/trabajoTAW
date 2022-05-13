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
import trabajoTAW.dao.DatosEstudioProductoFacade;
import trabajoTAW.dao.DatosEstudioUsuarioFacade;
import trabajoTAW.dao.EstudioFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.dto.EstudioDTO;
import trabajoTAW.entity.DatosEstudioProducto;
import trabajoTAW.entity.DatosEstudioUsuario;
import trabajoTAW.entity.Estudio;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author Alfonso
 */
@Stateless
public class EstudioService {
    
    @EJB EstudioFacade estudioFacade;
    @EJB UsuarioFacade usuarioFacade;
    @EJB DatosEstudioProductoFacade estudioProductoFacade;
    @EJB DatosEstudioUsuarioFacade estudioUsuarioFacade;
    
    public List<EstudioDTO> listarClientes (String filtroNombre) {
        List<Estudio> estudios = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            estudios = this.estudioFacade.findAll();        
        } else {
            estudios = this.estudioFacade.findByNombre(filtroNombre);
        }
        
        return this.listaEntityADTO(estudios);                
    } 

    private List<EstudioDTO> listaEntityADTO (List<Estudio> lista) {
        List<EstudioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Estudio estudio:lista) {
                listaDTO.add(estudio.toDTO());
            }
        }
        return listaDTO;
    }
    
    public EstudioDTO find(Integer idEstudio){
        Estudio estudio = estudioFacade.find(idEstudio);
        return estudio.toDTO();
    }
    
    public void remove(Integer idEstudio){
        Estudio estudio = this.estudioFacade.find(idEstudio);
        estudioFacade.remove(estudio);
    }
    
    public EstudioDTO create(String nombre,String analista,String descripcion,String element,String idEstudioProducto,String idEstudioUsuario){
        Estudio estudio = new Estudio();
        estudio = rellenarEstudio(estudio,nombre,analista,descripcion,element,idEstudioProducto,idEstudioUsuario);
        estudioFacade.create(estudio);
        return estudio.toDTO();
    }
    
    public EstudioDTO edit(String idEstudio,String nombre,String analista,String descripcion,String element,String idEstudioProducto,String idEstudioUsuario){
        Estudio estudio = this.estudioFacade.find(Integer.parseInt(idEstudio));
        estudio = rellenarEstudio(estudio,nombre,analista,descripcion,element,idEstudioProducto,idEstudioUsuario);
        estudioFacade.edit(estudio);
        return estudio.toDTO();
    }
    
    private Estudio rellenarEstudio(Estudio estudio,String nombre,String analista,String descripcion,String element,String idEstudioProducto,String idEstudioUsuario){
        if(nombre != null && !nombre.isEmpty()){
            estudio.setNombre(nombre);
        }
        if(analista != null && !analista.isEmpty()){
            Usuario user = this.usuarioFacade.find(Integer.parseInt(analista));
            if(user != null){
                estudio.setAnalista(user);
            }
        }
        if(analista != null && !analista.isEmpty()){
            estudio.setDescripcion(descripcion);
        }
        if(element != null && !element.isEmpty()){
            
            switch (element) {
                case "comprador":
                    estudio.setComprador(Boolean.TRUE);
                    estudio.setVendedor(Boolean.FALSE);
                    estudio.setProducto(Boolean.FALSE);
                    break;
                case "vendedor":
                    estudio.setComprador(Boolean.FALSE);
                    estudio.setVendedor(Boolean.TRUE);
                    estudio.setProducto(Boolean.FALSE);
                    break;
                default:
                    estudio.setComprador(Boolean.FALSE);
                    estudio.setVendedor(Boolean.FALSE);
                    estudio.setProducto(Boolean.TRUE);
                    break;
            }
            
        }
        
        return estudio;
    }
    
        public void copy(String str){
            
            Estudio estudio = estudioFacade.find(Integer.parseInt(str));
                
            estudio.setDatosEstudioProducto(null);
            estudio.setDatosEstudioUsuario(null);
                
            estudioFacade.create(estudio);
                   
            DatosEstudioProducto estudioProducto = this.estudioProductoFacade.find(Integer.parseInt(str));
            DatosEstudioUsuario estudioUsuario = this.estudioUsuarioFacade.find(Integer.parseInt(str));
            
            if(estudioProducto != null){
                
                estudioProducto.setEstudio(estudio);
                estudioProducto.setId(estudio.getIdEstudio());
                estudioProductoFacade.create(estudioProducto);
                estudio.setDatosEstudioProducto(estudioProducto);
                
            }else if(estudioUsuario != null){
                estudioUsuario.setEstudio(estudio);
                estudioUsuario.setId(estudio.getIdEstudio());
                estudioUsuarioFacade.create(estudioUsuario);
                estudio.setDatosEstudioUsuario(estudioUsuario);
            }
            estudioFacade.edit(estudio);
            
        }
    
    
}
