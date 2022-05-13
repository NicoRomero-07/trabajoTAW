/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import trabajoTAW.dto.DatosEstudioProductoDTO;

/**
 *
 * @author nicol
 */
@Entity
@Table(name = "DATOS_ESTUDIO_PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosEstudioProducto.findAll", query = "SELECT d FROM DatosEstudioProducto d")
    , @NamedQuery(name = "DatosEstudioProducto.findById", query = "SELECT d FROM DatosEstudioProducto d WHERE d.id = :id")
    , @NamedQuery(name = "DatosEstudioProducto.findByCategorias", query = "SELECT d FROM DatosEstudioProducto d WHERE d.categorias = :categorias")
    , @NamedQuery(name = "DatosEstudioProducto.findByVendidos", query = "SELECT d FROM DatosEstudioProducto d WHERE d.vendidos = :vendidos")
    , @NamedQuery(name = "DatosEstudioProducto.findByPromocion", query = "SELECT d FROM DatosEstudioProducto d WHERE d.promocion = :promocion")
    , @NamedQuery(name = "DatosEstudioProducto.findByPrecioSalida", query = "SELECT d FROM DatosEstudioProducto d WHERE d.precioSalida = :precioSalida")
    , @NamedQuery(name = "DatosEstudioProducto.findByPrecioActual", query = "SELECT d FROM DatosEstudioProducto d WHERE d.precioActual = :precioActual")})
public class DatosEstudioProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CATEGORIAS")
    private Boolean categorias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VENDIDOS")
    private Boolean vendidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROMOCION")
    private Boolean promocion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO_SALIDA")
    private Double precioSalida;
    @Column(name = "PRECIO_ACTUAL")
    private Double precioActual;
    @JoinColumn(name = "ID", referencedColumnName = "ID_ESTUDIO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Estudio estudio;

    public DatosEstudioProducto() {
    }

    public DatosEstudioProducto(Integer id) {
        this.id = id;
    }

    public DatosEstudioProducto(Integer id, Boolean categorias, Boolean vendidos, Boolean promocion) {
        this.id = id;
        this.categorias = categorias;
        this.vendidos = vendidos;
        this.promocion = promocion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosEstudioProducto)) {
            return false;
        }
        DatosEstudioProducto other = (DatosEstudioProducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trabajoTAW.entity.DatosEstudioProducto[ id=" + id + " ]";
    }
    
    public DatosEstudioProductoDTO toDTO () {
        DatosEstudioProductoDTO dto = new DatosEstudioProductoDTO();
        
        dto.setId(id);
        dto.setCategorias(categorias);
        dto.setVendidos(vendidos);
        dto.setPromocion(promocion);
        dto.setPrecioSalida(precioSalida);
        dto.setPrecioActual(precioActual);
        dto.setEstudio(estudio);
       
        return dto;
    }
    
}
