/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import trabajoTAW.dto.ProductoDTO;

/**
 *
 * @author nicol
 */
@Entity
@Table(name = "PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto")
    , @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Producto.findByPrecioSalida", query = "SELECT p FROM Producto p WHERE p.precioSalida = :precioSalida")
    , @NamedQuery(name = "Producto.findByUrlFoto", query = "SELECT p FROM Producto p WHERE p.urlFoto = :urlFoto")
    , @NamedQuery(name = "Producto.findByCategoria", query = "SELECT p FROM Producto p WHERE p.categoria = :categoria")
    , @NamedQuery(name = "Producto.findByPublicador", query = "SELECT p FROM Producto p WHERE p.publicador = :publicador")
    , @NamedQuery(name = "Producto.findByEnPromocion", query = "SELECT p FROM Producto p WHERE p.enPromocion = :enPromocion")
    , @NamedQuery(name = "Producto.findByFechaInicioSubasta", query = "SELECT p FROM Producto p WHERE p.fechaInicioSubasta = :fechaInicioSubasta")
    , @NamedQuery(name = "Producto.findByFechaFinSubasta", query = "SELECT p FROM Producto p WHERE p.fechaFinSubasta = :fechaFinSubasta")
    , @NamedQuery(name = "Producto.findByComprador", query = "SELECT p FROM Producto p WHERE p.comprador = :comprador")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PRODUCTO")
    private Integer idProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 45)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_SALIDA")
    private double precioSalida;
    @Size(max = 45)
    @Column(name = "URL_FOTO")
    private String urlFoto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CATEGORIA")
    private int categoria;
    @JoinColumn(name = "PUBLICADOR", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    @NotNull
    private Usuario publicador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EN_PROMOCION")
    private Boolean enPromocion;
    @NotNull
    @Column(name = "FECHA_INICIO_SUBASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioSubasta;
    @NotNull
    @Column(name = "FECHA_FIN_SUBASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaFinSubasta;
    @JoinColumn(name = "COMPRADOR", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    @NotNull
    private Usuario comprador;
    @ManyToMany(mappedBy = "productoList")
    private List<Categoria> categoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto1")
    private List<ListaProducto> listaProductoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<Puja> pujaList;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Integer idProducto, String nombre, double precioSalida, int categoria, Usuario publicador, Boolean enPromocion,
            Date fechaInicioSubasta, Date fechaFinSubasta) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precioSalida = precioSalida;
        this.categoria = categoria;
        this.publicador = publicador;
        this.enPromocion = enPromocion;
        this.fechaInicioSubasta = fechaInicioSubasta;
        this.fechaFinSubasta = fechaFinSubasta;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioSalida() {
        return precioSalida;
    }

    public void setPrecioSalida(double precioSalida) {
        this.precioSalida = precioSalida;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public Usuario getPublicador() {
        return publicador;
    }

    public void setPublicador(Usuario publicador) {
        this.publicador = publicador;
    }

    public Boolean getEnPromocion() {
        return enPromocion;
    }

    public void setEnPromocion(Boolean enPromocion) {
        this.enPromocion = enPromocion;
    }
    
    public Date getFechaInicioSubasta(){
        return this.fechaInicioSubasta;
    }
    
    public void setFechaInicioSubasta(Date fechaInicioSubasta){
        this.fechaInicioSubasta = fechaInicioSubasta;
    }
    
    public Date getFechaFinSubasta(){
        return this.fechaInicioSubasta;
    }
    
    public void setFechaFinSubasta(Date fechaFinSubasta){
        this.fechaFinSubasta = fechaFinSubasta;
    }
    
    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }
    
    

    @XmlTransient
    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    @XmlTransient
    public List<ListaProducto> getListaProductoList() {
        return listaProductoList;
    }

    public void setListaProductoList(List<ListaProducto> listaProductoList) {
        this.listaProductoList = listaProductoList;
    }

    @XmlTransient
    public List<Puja> getPujaList() {
        return pujaList;
    }

    public void setPujaList(List<Puja> pujaList) {
        this.pujaList = pujaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trabajoTAW.entity.Producto[ idProducto=" + idProducto + " ]";
    }
    
    
    public ProductoDTO toDTO() {
        ProductoDTO dto = new ProductoDTO();
        
        dto.setIdProducto(idProducto);
        dto.setNombre(nombre);
        dto.setDescripcion(descripcion);
        dto.setCategoria(categoria);
        dto.setUrlFoto(urlFoto);
        dto.setPublicador(publicador.toDTO());
        dto.setPrecioSalida(precioSalida);
        dto.setFechaInicioSubasta(fechaInicioSubasta);
        dto.setFechaFinSubasta(fechaFinSubasta);
        dto.setEnPromocion(enPromocion);
        
        return dto;
    }
    
}
