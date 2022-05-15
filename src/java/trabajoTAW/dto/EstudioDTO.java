/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dto;



/**
 *
 * @author Alfonso 100%
 */
public class EstudioDTO {
    private Integer idEstudio;
    private String nombre;
    private UsuarioDTO analista;
    private String descripcion;
    private Boolean comprador;
    private Boolean vendedor;
    private Boolean producto;
    private DatosEstudioUsuarioDTO datosEstudioUsuario;
    private DatosEstudioProductoDTO datosEstudioProducto;

    public EstudioDTO() {
    }


    public Integer getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UsuarioDTO getAnalista() {
        return analista;
    }

    public void setAnalista(UsuarioDTO analista) {
        this.analista = analista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getComprador() {
        return comprador;
    }

    public void setComprador(Boolean comprador) {
        this.comprador = comprador;
    }
    
    public Boolean getVendedor() {
        return vendedor;
    }

    public void setVendedor(Boolean vendedor) {
        this.vendedor = vendedor;
    }
    
    public Boolean getProducto() {
        return producto;
    }

    public void setProducto(Boolean producto) {
        this.producto = producto;
    }
    
    public DatosEstudioUsuarioDTO getDatosEstudioUsuario() {
        return datosEstudioUsuario;
    }

    public void setDatosEstudioUsuario(DatosEstudioUsuarioDTO datosEstudioUsuario) {
        this.datosEstudioUsuario = datosEstudioUsuario;
    }

    public DatosEstudioProductoDTO getDatosEstudioProducto() {
        return datosEstudioProducto;
    }

    public void setDatosEstudioProducto(DatosEstudioProductoDTO datosEstudioProducto) {
        this.datosEstudioProducto = datosEstudioProducto;
    }
}
