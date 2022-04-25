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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nicol
 */
@Entity
@Table(name = "LISTA_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaUsuario.findAll", query = "SELECT l FROM ListaUsuario l")
    , @NamedQuery(name = "ListaUsuario.findByIdListaUsuario", query = "SELECT l FROM ListaUsuario l WHERE l.idListaUsuario = :idListaUsuario")
    , @NamedQuery(name = "ListaUsuario.findByNombre", query = "SELECT l FROM ListaUsuario l WHERE l.nombre = :nombre")})
public class ListaUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LISTA_USUARIO")
    private Integer idListaUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinTable(name = "USUARIO_LISTA_USUARIO", joinColumns = {
        @JoinColumn(name = "LISTA", referencedColumnName = "ID_LISTA_USUARIO")}, inverseJoinColumns = {
        @JoinColumn(name = "USUARIO", referencedColumnName = "ID_USUARIO")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listaUsuario")
    private List<Notificacion> notificacionList;

    public ListaUsuario() {
    }

    public ListaUsuario(Integer idListaUsuario) {
        this.idListaUsuario = idListaUsuario;
    }

    public ListaUsuario(Integer idListaUsuario, String nombre) {
        this.idListaUsuario = idListaUsuario;
        this.nombre = nombre;
    }

    public Integer getIdListaUsuario() {
        return idListaUsuario;
    }

    public void setIdListaUsuario(Integer idListaUsuario) {
        this.idListaUsuario = idListaUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Notificacion> getNotificacionList() {
        return notificacionList;
    }

    public void setNotificacionList(List<Notificacion> notificacionList) {
        this.notificacionList = notificacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idListaUsuario != null ? idListaUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaUsuario)) {
            return false;
        }
        ListaUsuario other = (ListaUsuario) object;
        if ((this.idListaUsuario == null && other.idListaUsuario != null) || (this.idListaUsuario != null && !this.idListaUsuario.equals(other.idListaUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trabajoTAW.entity.ListaUsuario[ idListaUsuario=" + idListaUsuario + " ]";
    }
    
}
