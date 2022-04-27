/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trabajoTAW.dao.ListaUsuarioFacade;
import trabajoTAW.dao.NotificacionFacade;
import trabajoTAW.dao.ProductoFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.entity.ListaUsuario;
import trabajoTAW.entity.Notificacion;
import trabajoTAW.entity.Producto;

/**
 *
 * @author nicol
 */
@WebServlet(name = "ListaCompradorEnviarNotificacionServlet", urlPatterns = {"/ListaCompradorEnviarNotificacionServlet"})
public class ListaCompradorEnviarNotificacionServlet extends HttpServlet {
    
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
        String strId;
        strId = request.getParameter("id");
        ListaUsuario listaComprador = listaCompradorFacade.find(Integer.parseInt(strId));
        List<Producto> promociones = productoFacade.getProductosPromocion();
        Notificacion notificacion = new Notificacion();
        StringBuilder contenido = new StringBuilder();
        for (Producto promocion: promociones){
            contenido.append(promocion.getNombre()).append("\t");
            contenido.append(promocion.getPublicador()).append("\t");
            contenido.append(promocion.getDescripcion()).append("\t");
            contenido.append(promocion.getPrecioSalida()).append("\n");
        }
        notificacion.setContenido(contenido.toString());
        notificacion.setListaUsuario(listaComprador);
        //notificacion.getNotificante(super);
        notificacionFacade.create(notificacion);
        response.sendRedirect(request.getContextPath() + "/ListaCompradorServlet");
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
