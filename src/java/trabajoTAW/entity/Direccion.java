/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import trabajoTAW.dto.DireccionDTO;

/**
 *
 * @author nicol
 */
@Entity
@Table(name = "DIRECCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Direccion.findAll", query = "SELECT d FROM Direccion d")
    , @NamedQuery(name = "Direccion.findByIdDireccion", query = "SELECT d FROM Direccion d WHERE d.idDireccion = :idDireccion")
    , @NamedQuery(name = "Direccion.findByTipo", query = "SELECT d FROM Direccion d WHERE d.tipo = :tipo")
    , @NamedQuery(name = "Direccion.findByCalle", query = "SELECT d FROM Direccion d WHERE d.calle = :calle")
    , @NamedQuery(name = "Direccion.findByNumero", query = "SELECT d FROM Direccion d WHERE d.numero = :numero")
    , @NamedQuery(name = "Direccion.findByCodigoPostal", query = "SELECT d FROM Direccion d WHERE d.codigoPostal = :codigoPostal")
    , @NamedQuery(name = "Direccion.findByPlanta", query = "SELECT d FROM Direccion d WHERE d.planta = :planta")
    , @NamedQuery(name = "Direccion.findByPuerta", query = "SELECT d FROM Direccion d WHERE d.puerta = :puerta")})
public class Direccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DIRECCION")
    private Integer idDireccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CALLE")
    private String calle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO")
    private int numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_POSTAL")
    private int codigoPostal;
    @Column(name = "PLANTA")
    private Integer planta;
    @Size(max = 45)
    @Column(name = "PUERTA")
    private String puerta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "direccion")
    private List<Usuario> usuarioList;

    public Direccion() {
    }

    public Direccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Direccion(Integer idDireccion, String tipo, String calle, int numero, int codigoPostal) {
        this.idDireccion = idDireccion;
        this.tipo = tipo;
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Integer getPlanta() {
        return planta;
    }

    public void setPlanta(Integer planta) {
        this.planta = planta;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDireccion != null ? idDireccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Direccion)) {
            return false;
        }
        Direccion other = (Direccion) object;
        if ((this.idDireccion == null && other.idDireccion != null) || (this.idDireccion != null && !this.idDireccion.equals(other.idDireccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trabajoTAW.entity.Direccion[ idDireccion=" + idDireccion + " ]";
    }

    public DireccionDTO toDTO() {
        DireccionDTO dto = new DireccionDTO();
        
        dto.setCalle(calle);
        dto.setCodigoPostal(codigoPostal);
        dto.setIdDireccion(idDireccion);
        dto.setNumero(numero);
        dto.setPlanta(planta);
        dto.setPuerta(puerta);
        dto.setTipo(tipo);
        
        return dto;
    }
    
        public DireccionDTO toDTO() {
        DireccionDTO dto = new DireccionDTO();
        
        dto.setCalle(calle);
        dto.setCodigoPostal(codigoPostal);
        dto.setIdDireccion(idDireccion);
        dto.setNumero(numero);
        dto.setPlanta(planta);
        dto.setPuerta(puerta);
        dto.setTipo(tipo);
        
        return dto;
    }
}
