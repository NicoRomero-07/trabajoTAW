/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nicor
 */
@Embeddable
public class UsuarioListaUsuarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "USUARIO")
    private int usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LISTA")
    private int lista;

    public UsuarioListaUsuarioPK() {
    }

    public UsuarioListaUsuarioPK(int usuario, int lista) {
        this.usuario = usuario;
        this.lista = lista;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getLista() {
        return lista;
    }

    public void setLista(int lista) {
        this.lista = lista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usuario;
        hash += (int) lista;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioListaUsuarioPK)) {
            return false;
        }
        UsuarioListaUsuarioPK other = (UsuarioListaUsuarioPK) object;
        if (this.usuario != other.usuario) {
            return false;
        }
        if (this.lista != other.lista) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trabajoTAW.entity.UsuarioListaUsuarioPK[ usuario=" + usuario + ", lista=" + lista + " ]";
    }
    
}
