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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import trabajoTAW.dto.PujaDTO;

/**
 *
 * @author nicol
 */
@Entity
@Table(name = "PUJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puja.findAll", query = "SELECT p FROM Puja p")
    , @NamedQuery(name = "Puja.findByIdPuja", query = "SELECT p FROM Puja p WHERE p.idPuja = :idPuja")
    , @NamedQuery(name = "Puja.findByCantidad", query = "SELECT p FROM Puja p WHERE p.cantidad = :cantidad")})
public class Puja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PUJA")
    private Integer idPuja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private double cantidad;
    @JoinColumn(name = "PRODUCTO", referencedColumnName = "ID_PRODUCTO")
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "COMPRADOR", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario comprador;

    public Puja() {
    }

    public Puja(Integer idPuja) {
        this.idPuja = idPuja;
    }

    public Puja(Integer idPuja, double cantidad) {
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPuja != null ? idPuja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puja)) {
            return false;
        }
        Puja other = (Puja) object;
        if ((this.idPuja == null && other.idPuja != null) || (this.idPuja != null && !this.idPuja.equals(other.idPuja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trabajoTAW.entity.Puja[ idPuja=" + idPuja + " ]";
    }
    
    public PujaDTO toDTO() {
        PujaDTO dto = new PujaDTO();
        
        dto.setCantidad(cantidad);
        dto.setComprador(comprador.toDTO());
        dto.setIdPuja(idPuja);
        dto.setProducto(producto.toDTO());
        
        return dto;
    }
    
}
