/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dto;

import java.util.Objects;

/**
 *
 * @author nicor
 */
public class CategoriaDTO {
    private Integer idCategoria;
    private String nombre;
    
    public CategoriaDTO(){
        
    }
     public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean equals(Object dto){
        CategoriaDTO o;
        if(dto instanceof CategoriaDTO){
            o = (CategoriaDTO) dto;
            return Objects.equals(o.getIdCategoria(), idCategoria);
        }else{
            return false;
        }
        
    }
}
