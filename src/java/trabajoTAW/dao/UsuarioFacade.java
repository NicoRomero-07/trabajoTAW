/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.dao;

import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import trabajoTAW.entity.DatosEstudioUsuario;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author nicor
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
    public List<Usuario> findByNombreUsuario (String nombre) {
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
        q.setParameter("analista", '%' + analista +'%');
        return q.getResultList();
    }
    
    public List<Usuario> getAdministradores() {
         Query q;
        String administrador = "administrador";
        q = this.getEntityManager().createQuery("select u from Usuario u where upper(u.tipoUsuario.tipo) like upper(:administrador)");
        q.setParameter("administrador", '%' + administrador +'%');
        return q.getResultList();
    }
    
    public List<Usuario> visualizarEstudio(DatosEstudioUsuario estudioUsuario){
        Query q;
        StringBuilder consulta = generarConsulta(estudioUsuario);
        
        q = this.getEntityManager().createQuery("SELECT u FROM Usuario u" + consulta);
        return q.getResultList();
    }
    
    private StringBuilder generarConsulta(DatosEstudioUsuario estudioUsuario){
        StringBuilder consulta = new StringBuilder();
        if(estudioUsuario != null){
            Boolean bnombre = estudioUsuario.getNombre();
            Boolean bapellidos = estudioUsuario.getApellidos();
            Boolean bingresos = estudioUsuario.getIngresos();
            Boolean bascendente = estudioUsuario.getAscendente();
            
            if(bnombre || bapellidos || bingresos){
                consulta.append(" ORDER BY ");
            }
            String ascendente = Objects.equals(bascendente, Boolean.TRUE) ? " ASC, " : " DESC, ";
            
            consulta.append(Objects.equals(bnombre, Boolean.TRUE) ? "u.nombreUsuario" + ascendente : "");
            consulta.append(Objects.equals(bapellidos, Boolean.TRUE) ? "u.primerApellido" + ascendente + "u.segundoApellido" + ascendente : "");
            //consulta.append(bingresos == Boolean.TRUE ? "ingresos" + ascendente : "");
            
            if(bnombre || bapellidos || bingresos){
                consulta.deleteCharAt(consulta.length()-1);
                consulta.deleteCharAt(consulta.length()-1);
            }
        }
        return consulta;
    }
}
