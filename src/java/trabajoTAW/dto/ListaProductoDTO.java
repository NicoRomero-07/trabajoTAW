/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dto;

import trabajoTAW.entity.Producto;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author Victor
 */
public class ListaProductoDTO {
    
    private UsuarioDTO usuario1;
    private ProductoDTO producto1;
    
    public ListaProductoDTO(){
        
    }
    
    public ProductoDTO getProducto1() {
        return producto1;
    }

    public void setProducto1(ProductoDTO producto1) {
        this.producto1 = producto1;
    }

    public UsuarioDTO getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(UsuarioDTO usuario1) {
        this.usuario1 = usuario1;
    }
}
