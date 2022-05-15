/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import trabajoTAW.entity.DatosEstudioUsuario;
import trabajoTAW.entity.Estudio;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author Alfonso(77,77%), Nicolás Zhao(11,11%), Nicolás Álvarez (nicor) (11,11%)
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "trabajoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario comprobarUsuario(String strusuario, String strclave) {
        Query q;

        q = this.getEntityManager().createQuery("select u from Usuario u where u.nombreUsuario = :usuario and"
                + " u.contrasenya = :clave");

        q.setParameter("usuario", strusuario);
        q.setParameter("clave", strclave);
        List<Usuario> lista = q.getResultList();

        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public List<Usuario> findByNombreUsuario(String nombre) {
        Query q;
        q = this.getEntityManager().createQuery("select u from Usuario u where upper(u.nombreUsuario) like upper(:nombre)");
        q.setParameter("nombre", '%' + nombre +'%');
        return q.getResultList();
    }

    public List<Usuario> getCompradores() {
        Query q;
        Integer idTipoComprador = 3;
        q = this.getEntityManager().createQuery("select u from Usuario u where u.tipoUsuario.idTipoUsuario = :tipo");

        q.setParameter("tipo", idTipoComprador);

        List<Usuario> lista = q.getResultList();

        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista;
        }
    }

    public List<Usuario> getAnalistas() {
        Query q;
        String analista = "analista";
        q = this.getEntityManager().createQuery("select u from Usuario u where upper(u.tipoUsuario.tipo) like upper(:analista)");
        q.setParameter("analista", '%' + analista + '%');
        return q.getResultList();
    }

    public List<Usuario> getAdministradores() {
        Query q;
        String administrador = "administrador";
        q = this.getEntityManager().createQuery("select u from Usuario u where upper(u.tipoUsuario.tipo) like upper(:administrador)");
        q.setParameter("administrador", '%' + administrador + '%');
        return q.getResultList();
    }

    public List<Usuario> visualizarEstudio(Estudio estudio,DatosEstudioUsuario estudioUsuario) {
        Query q;
        String comprador = "comprador";
        String vendedor = "vendedor";
        String consulta = generarConsulta(estudio,estudioUsuario);
        q = this.getEntityManager().createQuery(consulta);
        if(consulta.contains(":comprador")){
            q.setParameter("comprador", '%' + comprador + '%');
        }
        if(consulta.contains(":vendedor")){
            q.setParameter("vendedor", '%' + vendedor + '%');
        }
        return q.getResultList();
    }

    private String generarConsulta(Estudio estudio,DatosEstudioUsuario estudioUsuario) {
        StringBuilder consulta = new StringBuilder();
        Boolean bnombre = estudioUsuario.getNombre();
        Boolean bapellidos = estudioUsuario.getApellidos();
        Boolean bingresos = estudioUsuario.getIngresos();
        Boolean bascendente = estudioUsuario.getAscendente();
        
        consulta.append("SELECT u FROM Usuario u");
        
            if(bingresos){
                if(estudio.getComprador()){
                    consulta.append(" JOIN Producto p ON u.idUsuario = p.comprador.idUsuario ");
                }else{
                    consulta.append(" JOIN Producto p ON u.idUsuario = p.publicador.idUsuario ");
                }
                consulta.append(" JOIN Puja pu ON p.idProducto = pu.producto.idProducto");
                
            }
            
            if(estudio.getVendedor()){
                consulta.append(" WHERE upper(u.tipoUsuario.tipo) like upper(:vendedor)");
            }else{
                consulta.append(" WHERE upper(u.tipoUsuario.tipo) like upper(:comprador)");
            }
            if(bingresos && estudio.getComprador()){
                consulta.append(" AND pu.comprador.idUsuario = u.idUsuario GROUP BY u");
            }else if(bingresos && estudio.getVendedor()){
                consulta.append(" AND pu.cantidad IN " 
                        + "(SELECT MAX(pu.cantidad) FROM Usuario u "
                        + "JOIN Producto p ON u.idUsuario = p.publicador.idUsuario " 
                        + "JOIN Puja pu ON p.idProducto = pu.producto.idProducto " 
                        + "WHERE upper(u.tipoUsuario.tipo) like upper(:vendedor) " 
                        + "GROUP BY p.idProducto) " 
                        + "GROUP BY u");
            }
         
            
        if (bnombre || bapellidos || bingresos) {
            consulta.append(" ORDER BY ");
        }

        String ascendente = Objects.equals(bascendente, Boolean.TRUE) ? " ASC, " : " DESC, ";

        consulta.append(Objects.equals(bnombre, Boolean.TRUE) ? "u.nombre" + ascendente : "");
        consulta.append(Objects.equals(bapellidos, Boolean.TRUE) ? "u.primerApellido" + ascendente + "u.segundoApellido" + ascendente : "");
        consulta.append(Objects.equals(bingresos, Boolean.TRUE) ? "sum(pu.cantidad)" + ascendente : "");
        
        if (bnombre || bapellidos || bingresos) {
            consulta.deleteCharAt(consulta.length() - 1);
            consulta.deleteCharAt(consulta.length() - 1);
        }
        return consulta.toString();
    }
    
    public List<Double> getIngresosUsuarios(Estudio estudio,DatosEstudioUsuario estudioUsuario){
        Query q;
        String comprador = "comprador";
        String vendedor = "vendedor";
        String consulta = generarConsultaIngresos(estudio,estudioUsuario);
        q = this.getEntityManager().createQuery(consulta);
        if(consulta.contains(":comprador")){
            q.setParameter("comprador", '%' + comprador + '%');
        }
        if(consulta.contains(":vendedor")){
            q.setParameter("vendedor", '%' + vendedor + '%');
        }
        return q.getResultList();
    }
    
    private String generarConsultaIngresos(Estudio estudio,DatosEstudioUsuario estudioUsuario){
        StringBuilder consulta = new StringBuilder();
        String ascendente = Objects.equals(estudioUsuario.getAscendente(), Boolean.TRUE) ? " ASC, " : " DESC, ";
        if(estudio.getComprador()){
            consulta.append("SELECT SUM(pu.cantidad) FROM Usuario u JOIN Producto p ON u.idUsuario = p.comprador.idUsuario " +
                 "JOIN Puja pu ON p.idProducto = pu.producto.idProducto " +
                 "WHERE upper(u.tipoUsuario.tipo) like upper(:comprador) " +
                 "AND pu.comprador.idUsuario = u.idUsuario " +
                 "GROUP BY u ORDER BY SUM(pu.cantidad)" + ascendente);
        }else{
            consulta.append("SELECT SUM(pu.cantidad) FROM Usuario u JOIN Producto p ON u.idUsuario = p.publicador.idUsuario " +
                 "JOIN Puja pu ON p.idProducto = pu.producto.idProducto " +
                 "WHERE upper(u.tipoUsuario.tipo) like upper(:vendedor) " +
                 "AND pu.cantidad IN "
                    + "(SELECT MAX(pu.cantidad) FROM Usuario u JOIN Producto p ON u.idUsuario = p.publicador.idUsuario " +
                            "JOIN Puja pu ON p.idProducto = pu.producto.idProducto " +
                            "WHERE upper(u.tipoUsuario.tipo) like upper(:vendedor) " +
                            "GROUP BY p.idProducto)" +
                 "GROUP BY u ORDER BY SUM(pu.cantidad)" + ascendente);
        }
        consulta.append(Objects.equals(estudioUsuario.getNombre(), Boolean.TRUE) ? "u.nombre" + ascendente : "");
        consulta.append(Objects.equals(estudioUsuario.getApellidos(), Boolean.TRUE) ? "u.primerApellido" + ascendente + "u.segundoApellido" + ascendente : "");
        
        consulta.deleteCharAt(consulta.length() - 1);
        consulta.deleteCharAt(consulta.length() - 1);
        
        return consulta.toString();
    }
    
}
