/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import trabajoTAW.dto.ListaProductoDTO;

/**
 *
 * @author nicol
 */
@Entity
@Table(name = "LISTA_PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaProducto.findAll", query = "SELECT l FROM ListaProducto l")
    , @NamedQuery(name = "ListaProducto.findByProducto", query = "SELECT l FROM ListaProducto l WHERE l.listaProductoPK.producto = :producto")
    , @NamedQuery(name = "ListaProducto.findByUsuario", query = "SELECT l FROM ListaProducto l WHERE l.listaProductoPK.usuario = :usuario")
    , @NamedQuery(name = "ListaProducto.findByNombre", query = "SELECT l FROM ListaProducto l WHERE l.nombre = :nombre")})
public class ListaProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ListaProductoPK listaProductoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "PRODUCTO", referencedColumnName = "ID_PRODUCTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto1;
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID_USUARIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public ListaProducto() {
    }

    public ListaProducto(ListaProductoPK listaProductoPK) {
        this.listaProductoPK = listaProductoPK;
    }

    public ListaProducto(ListaProductoPK listaProductoPK, String nombre) {
        this.listaProductoPK = listaProductoPK;
        this.nombre = nombre;
    }

    public ListaProducto(int producto, int usuario) {
        this.listaProductoPK = new ListaProductoPK(producto, usuario);
    }

    public ListaProductoPK getListaProductoPK() {
        return listaProductoPK;
    }

    public void setListaProductoPK(ListaProductoPK listaProductoPK) {
        this.listaProductoPK = listaProductoPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Producto getProducto1() {
        return producto1;
    }

    public void setProducto1(Producto producto1) {
        this.producto1 = producto1;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listaProductoPK != null ? listaProductoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaProducto)) {
            return false;
        }
        ListaProducto other = (ListaProducto) object;
        if ((this.listaProductoPK == null && other.listaProductoPK != null) || (this.listaProductoPK != null && !this.listaProductoPK.equals(other.listaProductoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trabajoTAW.entity.ListaProducto[ listaProductoPK=" + listaProductoPK + " ]";
    }
    
    public ListaProductoDTO toDTO() {
        ListaProductoDTO dto = new ListaProductoDTO();
        
       dto.setNombre(nombre);
       dto.setUsuario1(usuario1.toDTO());
       dto.setProducto1(producto1.toDTO());
        
        return dto;
    }
    
}
