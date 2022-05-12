/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dto;

import trabajoTAW.entity.Estudio;


/**
 *
 * @author Alfonso
 */
public class DatosEstudioProductoDTO {
    
    private Integer id;
    private Boolean categorias;
    private Boolean vendidos;
    private Boolean promocion;
    private Double precioSalida;
    private Double precioActual;
    private Estudio estudio;
    
    public DatosEstudioProductoDTO() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getCategorias() {
        return categorias;
    }

    public void setCategorias(Boolean categorias) {
        this.categorias = categorias;
    }

    public Boolean getVendidos() {
        return vendidos;
    }

    public void setVendidos(Boolean vendidos) {
        this.vendidos = vendidos;
    }

    public Boolean getPromocion() {
        return promocion;
    }

    public void setPromocion(Boolean promocion) {
        this.promocion = promocion;
    }

    public Double getPrecioSalida() {
        return precioSalida;
    }

    public void setPrecioSalida(Double precioSalida) {
        this.precioSalida = precioSalida;
    }

    public Double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(Double precioActual) {
        this.precioActual = precioActual;
    }
    
    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }
    
}
