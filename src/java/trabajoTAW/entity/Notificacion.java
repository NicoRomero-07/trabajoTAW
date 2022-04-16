/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nicor
 */
@Entity
@Table(name = "NOTIFICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n")
    , @NamedQuery(name = "Notificacion.findByIdNotificacion", query = "SELECT n FROM Notificacion n WHERE n.idNotificacion = :idNotificacion")
    , @NamedQuery(name = "Notificacion.findByContenido", query = "SELECT n FROM Notificacion n WHERE n.contenido = :contenido")
    , @NamedQuery(name = "Notificacion.findByFechaEnvio", query = "SELECT n FROM Notificacion n WHERE n.fechaEnvio = :fechaEnvio")})
public class Notificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_NOTIFICACION")
    private Integer idNotificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CONTENIDO")
    private String contenido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ENVIO")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @JoinColumn(name = "LISTA_USUARIO", referencedColumnName = "ID_LISTA_USUARIO")
    @ManyToOne(optional = false)
    private ListaUsuario listaUsuario;
    @JoinColumn(name = "NOTIFICANTE", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario notificante;

    public Notificacion() {
    }

    public Notificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Notificacion(Integer idNotificacion, String contenido, Date fechaEnvio) {
        this.idNotificacion = idNotificacion;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
    }

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public ListaUsuario getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(ListaUsuario listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Usuario getNotificante() {
        return notificante;
    }

    public void setNotificante(Usuario notificante) {
        this.notificante = notificante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotificacion != null ? idNotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.idNotificacion == null && other.idNotificacion != null) || (this.idNotificacion != null && !this.idNotificacion.equals(other.idNotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trabajoTAW.entity.Notificacion[ idNotificacion=" + idNotificacion + " ]";
    }
    
}
