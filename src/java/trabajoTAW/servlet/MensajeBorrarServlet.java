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
import trabajoTAW.dao.NotificacionFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.entity.Notificacion;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author nicol
 */
@WebServlet(name = "MensajeBorrarServlet", urlPatterns = {"/MensajeBorrarServlet"})
public class MensajeBorrarServlet extends trabajoTAWServlet {
    @EJB NotificacionFacade notificacionFacade;
    @EJB UsuarioFacade compradorFacade;
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
        
            String str = request.getParameter("id");
            String strComprador = request.getParameter("idComprador");
            Usuario comprador = this.compradorFacade.find(Integer.parseInt(strComprador));
            Notificacion notificacion = this.notificacionFacade.find(Integer.parseInt(str));
            List<Notificacion> notificaciones = comprador.getNotificacionList();
            notificaciones.remove(notificacion);
            comprador.setNotificacionList(notificaciones);
            this.compradorFacade.edit(comprador);
            List<Usuario> usuarios =  notificacion.getUsuarioList();
            usuarios.remove(comprador);
            if (notificacion.getUsuarioList().isEmpty()){
                this.notificacionFacade.remove(notificacion); 
            }else{
                this.notificacionFacade.edit(notificacion);
            }
            
            request.setAttribute("comprador", comprador);
            request.setAttribute("notificaciones", comprador.getNotificacionList());

            //request.getRequestDispatcher("WEB-INF/jsp/mensajes.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath()+"/CompradorVerMensajeServlet?id="+comprador.getIdUsuario());
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
