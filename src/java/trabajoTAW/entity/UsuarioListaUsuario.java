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

/**
 *
 * @author nicor
 */
@Entity
@Table(name = "USUARIO_LISTA_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioListaUsuario.findAll", query = "SELECT u FROM UsuarioListaUsuario u")
    , @NamedQuery(name = "UsuarioListaUsuario.findByUsuario", query = "SELECT u FROM UsuarioListaUsuario u WHERE u.usuarioListaUsuarioPK.usuario = :usuario")
    , @NamedQuery(name = "UsuarioListaUsuario.findByLista", query = "SELECT u FROM UsuarioListaUsuario u WHERE u.usuarioListaUsuarioPK.lista = :lista")
    , @NamedQuery(name = "UsuarioListaUsuario.findByNombre", query = "SELECT u FROM UsuarioListaUsuario u WHERE u.nombre = :nombre")})
public class UsuarioListaUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioListaUsuarioPK usuarioListaUsuarioPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "LISTA", referencedColumnName = "ID_LISTA_USUARIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ListaUsuario listaUsuario;
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID_USUARIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public UsuarioListaUsuario() {
    }

    public UsuarioListaUsuario(UsuarioListaUsuarioPK usuarioListaUsuarioPK) {
        this.usuarioListaUsuarioPK = usuarioListaUsuarioPK;
    }

    public UsuarioListaUsuario(UsuarioListaUsuarioPK usuarioListaUsuarioPK, String nombre) {
        this.usuarioListaUsuarioPK = usuarioListaUsuarioPK;
        this.nombre = nombre;
    }

    public UsuarioListaUsuario(int usuario, int lista) {
        this.usuarioListaUsuarioPK = new UsuarioListaUsuarioPK(usuario, lista);
    }

    public UsuarioListaUsuarioPK getUsuarioListaUsuarioPK() {
        return usuarioListaUsuarioPK;
    }

    public void setUsuarioListaUsuarioPK(UsuarioListaUsuarioPK usuarioListaUsuarioPK) {
        this.usuarioListaUsuarioPK = usuarioListaUsuarioPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaUsuario getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(ListaUsuario listaUsuario) {
        this.listaUsuario = listaUsuario;
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
        hash += (usuarioListaUsuarioPK != null ? usuarioListaUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioListaUsuario)) {
            return false;
        }
        UsuarioListaUsuario other = (UsuarioListaUsuario) object;
        if ((this.usuarioListaUsuarioPK == null && other.usuarioListaUsuarioPK != null) || (this.usuarioListaUsuarioPK != null && !this.usuarioListaUsuarioPK.equals(other.usuarioListaUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trabajoTAW.entity.UsuarioListaUsuario[ usuarioListaUsuarioPK=" + usuarioListaUsuarioPK + " ]";
    }
    
}
