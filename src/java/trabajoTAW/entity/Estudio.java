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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nicor
 */
@Entity
@Table(name = "ESTUDIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudio.findAll", query = "SELECT e FROM Estudio e")
    , @NamedQuery(name = "Estudio.findByIdestudio", query = "SELECT e FROM Estudio e WHERE e.idestudio = :idestudio")
    , @NamedQuery(name = "Estudio.findByIngreso", query = "SELECT e FROM Estudio e WHERE e.ingreso = :ingreso")})
public class Estudio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDESTUDIO")
    private Integer idestudio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INGRESO")
    private double ingreso;
    @JoinColumn(name = "VENDEDOR", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario vendedor;
    @JoinColumn(name = "ANALISTA", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario analista;

    public Estudio() {
    }

    public Estudio(Integer idestudio) {
        this.idestudio = idestudio;
    }

    public Estudio(Integer idestudio, double ingreso) {
        this.idestudio = idestudio;
        this.ingreso = ingreso;
    }

    public Integer getIdestudio() {
        return idestudio;
    }

    public void setIdestudio(Integer idestudio) {
        this.idestudio = idestudio;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Usuario getAnalista() {
        return analista;
    }

    public void setAnalista(Usuario analista) {
        this.analista = analista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestudio != null ? idestudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudio)) {
            return false;
        }
        Estudio other = (Estudio) object;
        if ((this.idestudio == null && other.idestudio != null) || (this.idestudio != null && !this.idestudio.equals(other.idestudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trabajoTAW.entity.Estudio[ idestudio=" + idestudio + " ]";
    }
    
}
