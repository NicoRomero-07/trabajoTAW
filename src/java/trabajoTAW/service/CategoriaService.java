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
import trabajoTAW.dao.CategoriaFacade;
import trabajoTAW.dto.CategoriaDTO;
import trabajoTAW.entity.Categoria;

/**
 *
 * @author nicor
 */
@Stateless
public class CategoriaService {
    @EJB CategoriaFacade cf;
    private List<CategoriaDTO> listaEntityADTO (List<Categoria> lista) {
        List<CategoriaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Categoria categoria:lista) {
                listaDTO.add(categoria.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<CategoriaDTO> listarCategorias (String filtroNombre) {
        List<Categoria> categorias = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            categorias = this.cf.findAll();        
        } else {
            categorias = this.cf.findByNombre(filtroNombre);
        }
        
        return this.listaEntityADTO(categorias);                
    } 
    
    public CategoriaDTO buscarCategoria (Integer id) {
        Categoria categoria = this.cf.find(id);
        return categoria.toDTO();
    }
    
    public void borrarCategoria (Integer id) {
        Categoria categoria = this.cf.find(id);

        this.cf.remove(categoria);        
    }
    
    private void rellenarCategoria (Categoria categoria,
                              String nombreCategoria) {
        
        categoria.setNombre(nombreCategoria);
        
                       
    }
    
    public void crearCategoria (String nombreCategoria) {
        Categoria categoria = new Categoria();

        this.rellenarCategoria(categoria, nombreCategoria);

        this.cf.create(categoria);
    }

    public void modificarCategoria (Integer id,
                              String nombreCategoria) {
        
        Categoria categoria = this.cf.find(id);

        this.rellenarCategoria(categoria, nombreCategoria);

        this.cf.edit(categoria);
    }

}
