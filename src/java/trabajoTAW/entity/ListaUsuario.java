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

/**
 *
 * @author nicor
 */
@Entity
@Table(name = "LISTA_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaUsuario.findAll", query = "SELECT l FROM ListaUsuario l")
    , @NamedQuery(name = "ListaUsuario.findByIdlistaUsuario", query = "SELECT l FROM ListaUsuario l WHERE l.idlistaUsuario = :idlistaUsuario")
    , @NamedQuery(name = "ListaUsuario.findByNombre", query = "SELECT l FROM ListaUsuario l WHERE l.nombre = :nombre")})
public class ListaUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDLISTA_USUARIO")
    private Integer idlistaUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listaUsuario")
    private List<UsuarioListaUsuario> usuarioListaUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listaUsuario")
    private List<Notificacion> notificacionList;

    public ListaUsuario() {
    }

    public ListaUsuario(Integer idlistaUsuario) {
        this.idlistaUsuario = idlistaUsuario;
    }

    public ListaUsuario(Integer idlistaUsuario, String nombre) {
        this.idlistaUsuario = idlistaUsuario;
        this.nombre = nombre;
    }

    public Integer getIdlistaUsuario() {
        return idlistaUsuario;
    }

    public void setIdlistaUsuario(Integer idlistaUsuario) {
        this.idlistaUsuario = idlistaUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<UsuarioListaUsuario> getUsuarioListaUsuarioList() {
        return usuarioListaUsuarioList;
    }

    public void setUsuarioListaUsuarioList(List<UsuarioListaUsuario> usuarioListaUsuarioList) {
        this.usuarioListaUsuarioList = usuarioListaUsuarioList;
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
        hash += (idlistaUsuario != null ? idlistaUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaUsuario)) {
            return false;
        }
        ListaUsuario other = (ListaUsuario) object;
        if ((this.idlistaUsuario == null && other.idlistaUsuario != null) || (this.idlistaUsuario != null && !this.idlistaUsuario.equals(other.idlistaUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trabajoTAW.entity.ListaUsuario[ idlistaUsuario=" + idlistaUsuario + " ]";
    }
    
}
