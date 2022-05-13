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
        List<Estudio> estudios;

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
        estudioFacade.count(); //Metodo para actualizar la bd
        return estudio.toDTO();
    }
    
    public EstudioDTO edit(String idEstudio,String nombre,String analista,String descripcion,String element,String idEstudioProducto,String idEstudioUsuario){
        Estudio estudio = this.estudioFacade.find(Integer.parseInt(idEstudio));
        estudio = rellenarEstudio(estudio,nombre,analista,descripcion,element,idEstudioProducto,idEstudioUsuario);
        estudioFacade.edit(estudio);
        estudioFacade.count(); //Metodo para actualizar la bd
        return estudio.toDTO();
    }
    
    private Estudio rellenarEstudio(Estudio estudio,String nombre,String analista,String descripcion,String element,String idEstudioProducto,String idEstudioUsuario){
        if(nombre != null && !nombre.isEmpty()){
            estudio.setNombre(nombre);
        }
        if(analista != null && !analista.isEmpty()){
            Usuario user = this.usuarioFacade.find(Integer.parseInt(analista));
            estudio.setAnalista(user);
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
        if(idEstudioProducto != null && !idEstudioProducto.isEmpty() ){
            DatosEstudioProducto estudioProducto = this.estudioProductoFacade.find(Integer.parseInt(idEstudioProducto));
            estudio.setDatosEstudioProducto(estudioProducto);
        }
        if(idEstudioUsuario != null && !idEstudioUsuario.isEmpty()){
            DatosEstudioUsuario estudioUsuario = this.estudioUsuarioFacade.find(Integer.parseInt(idEstudioUsuario));
            estudio.setDatosEstudioUsuario(estudioUsuario);
        }
        return estudio;
    }
    
        public void copy(String str){
            
            Estudio estudio = estudioFacade.find(Integer.parseInt(str));
            Estudio estudionew = new Estudio(); 
            
            estudionew.setAnalista(estudio.getAnalista());
            estudionew.setComprador(estudio.getComprador());
            estudionew.setDescripcion(estudio.getDescripcion());
            estudionew.setNombre(estudio.getNombre());
            estudionew.setProducto(estudio.getProducto());
            estudionew.setVendedor(estudio.getVendedor());
            
            estudioFacade.create(estudionew);
            estudioFacade.count(); //Actualizar la bd
            
            DatosEstudioProducto estudioProducto = this.estudioProductoFacade.find(Integer.parseInt(str));
            DatosEstudioUsuario estudioUsuario = this.estudioUsuarioFacade.find(Integer.parseInt(str));
            
            if(estudioProducto != null){
                DatosEstudioProducto estudioProductonew = new DatosEstudioProducto();
                estudioProductonew.setCategorias(estudioProducto.getCategorias());
                estudioProductonew.setPrecioActual(estudioProducto.getPrecioActual());
                estudioProductonew.setPrecioSalida(estudioProducto.getPrecioSalida());
                estudioProductonew.setPromocion(estudioProducto.getPromocion());
                estudioProductonew.setVendidos(estudioProducto.getVendidos());
                
                estudioProductonew.setEstudio(estudionew);
                estudioProductonew.setId(estudionew.getIdEstudio());
                estudioProductoFacade.create(estudioProductonew);
                estudionew.setDatosEstudioProducto(estudioProductonew);
                
            }else if(estudioUsuario != null){
                DatosEstudioUsuario estudioUsuarionew = new DatosEstudioUsuario();
                
                estudioUsuarionew.setApellidos(estudioUsuario.getApellidos());
                estudioUsuarionew.setAscendente(estudioUsuario.getAscendente());
                estudioUsuarionew.setIngresos(estudioUsuario.getIngresos());
                estudioUsuarionew.setNombre(estudioUsuario.getNombre());
                
                estudioUsuarionew.setEstudio(estudionew);
                estudioUsuarionew.setId(estudionew.getIdEstudio());
                estudioUsuarioFacade.create(estudioUsuarionew);
                estudionew.setDatosEstudioUsuario(estudioUsuarionew);
            }
            estudioFacade.edit(estudionew);
            
        }
    
    
}
