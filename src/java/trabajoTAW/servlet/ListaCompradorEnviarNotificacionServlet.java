/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trabajoTAW.dao.ListaUsuarioFacade;
import trabajoTAW.dao.NotificacionFacade;
import trabajoTAW.dao.ProductoFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.entity.ListaUsuario;
import trabajoTAW.entity.Notificacion;
import trabajoTAW.entity.Producto;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author nicol
 */
@WebServlet(name = "ListaCompradorEnviarNotificacionServlet", urlPatterns = {"/ListaCompradorEnviarNotificacionServlet"})
public class ListaCompradorEnviarNotificacionServlet extends trabajoTAWServlet {
    
    @EJB ListaUsuarioFacade listaCompradorFacade;
    @EJB UsuarioFacade usuarioFacade;
    @EJB ProductoFacade productoFacade;
    @EJB NotificacionFacade notificacionFacade;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (super.comprobarSession(request, response)) {  
            String strId;
            strId = request.getParameter("id");
            ListaUsuario listaComprador = listaCompradorFacade.find(Integer.parseInt(strId));
            List<Producto> promociones = productoFacade.getProductosPromocion();
            Notificacion notificacion = new Notificacion();
            StringBuilder contenido = new StringBuilder();
            for (Producto promocion: promociones){
                contenido.append("Nombre: ").append(promocion.getNombre()).append("<br/>");
                contenido.append("Publicador: ").append(usuarioFacade.find(promocion.getPublicador()).getNombreUsuario()).append("<br/>");
                contenido.append("Descripcion: ").append(promocion.getDescripcion()).append("<br/>");
                contenido.append("Precio de salida: ").append(promocion.getPrecioSalida()).append("<br/><br/>");
            }
            Date now = new Date();
            notificacion.setFechaEnvio(now);
            notificacion.setContenido(contenido.toString());
            notificacion.setListaUsuario(listaComprador);
            HttpSession session = request.getSession();
            Usuario notificante = (Usuario)session.getAttribute("usuario");
            notificacion.setNotificante(notificante);
            notificacionFacade.create(notificacion);
            
            List<Usuario> compradores = new ArrayList();
            for(Usuario comprador: listaComprador.getUsuarioList()){
                List<Notificacion> notificaciones = comprador.getNotificacionList()== null?new ArrayList(): comprador.getNotificacionList();
                notificaciones.add(notificacion);
                comprador.setNotificacionList(notificaciones);
                usuarioFacade.edit(comprador);
                compradores.add(comprador);
            }
            notificacion.setUsuarioList(compradores);
            notificacionFacade.edit(notificacion);
            response.sendRedirect(request.getContextPath()+"/ListaCompradorServlet");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
