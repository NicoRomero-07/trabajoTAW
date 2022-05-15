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
public class PujaDTO {
    
    private Integer idPuja;
    private UsuarioDTO comprador;
    private ProductoDTO producto;
    private double cantidad;
    
    public PujaDTO() {
    }

    public PujaDTO(Integer idPuja) {
        this.idPuja = idPuja;
    }

    public PujaDTO(Integer idPuja, double cantidad) {
        this.idPuja = idPuja;
        this.cantidad = cantidad;
    }

    public Integer getIdPuja() {
        return idPuja;
    }

    public void setIdPuja(Integer idPuja) {
        this.idPuja = idPuja;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public UsuarioDTO getComprador() {
        return comprador;
    }

    public void setComprador(UsuarioDTO comprador) {
        this.comprador = comprador;
    }
    
}
