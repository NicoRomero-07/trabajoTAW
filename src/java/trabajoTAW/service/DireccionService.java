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
import trabajoTAW.dao.DireccionFacade;
import trabajoTAW.dto.DireccionDTO;
import trabajoTAW.entity.Direccion;
//import static trabajoTAW.entity.ListaProductoPK_.usuario;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author nicor
 */
@Stateless
public class DireccionService {
    
    @EJB DireccionFacade df;
    
    private List<DireccionDTO> listaEntityADTO (List<Direccion> lista) {
        List<DireccionDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Direccion direccion:lista) {
                listaDTO.add(direccion.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<DireccionDTO> listarDirecciones (String filtroNombre) {
        List<Direccion> direcciones = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            direcciones = this.df.findAll();        
        } else {
            direcciones = this.df.findByNombreDireccion(filtroNombre);
        }
        
        return this.listaEntityADTO(direcciones);                
    } 
    
    public DireccionDTO buscarDireccion (Integer id) {
        Direccion direccion = this.df.find(id);
        return direccion.toDTO();
    }
    
    public void borrarDireccion (Integer id) {
        Direccion direccion = this.df.find(id);

        this.df.remove(direccion);        
    }
    
    private void rellenarDireccion (Direccion direccion, String tipo, String calle, int numero, int codigoPostal, int planta, String puerta ) {
        
        direccion.setTipo(tipo);
        direccion.setCalle(calle);
        direccion.setNumero(numero);
        direccion.setCodigoPostal(codigoPostal);
        direccion.setPlanta(planta);
        direccion.setPuerta(puerta);
        direccion.setUsuarioList(new ArrayList<>());
                       
    }
    
    public void crearDireccion (String tipo, String calle, int numero, int codigoPostal, int planta, String puerta) {
        Direccion direccion = new Direccion();

        this.rellenarDireccion(direccion, tipo, calle, numero, codigoPostal, planta, puerta);

        this.df.create(direccion);
    }

    public void modificarDireccion (Integer id, String tipo, String calle, int numero, int codigoPostal, int planta, String puerta) {
        
        Direccion direccion = this.df.find(id);

        this.rellenarDireccion(direccion, tipo, calle, numero, codigoPostal, planta, puerta);

        this.df.edit(direccion);
    }

    public Direccion findDireccion(String calle, String numero) {
        Direccion direccion = (Direccion) df.findByCalleNumero(calle,Integer.parseInt(numero));
        return direccion;
    }
    
    
}
