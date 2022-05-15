/**
 *
 * @author Nicolás Zhao(100%)
 */
package trabajoTAW.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trabajoTAW.dto.NotificacionDTO;
import trabajoTAW.dto.UsuarioDTO;
/*
import trabajoTAW.dao.ListaUsuarioFacade;
import trabajoTAW.dao.NotificacionFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.entity.Notificacion;
import trabajoTAW.entity.Usuario;
*/
import trabajoTAW.service.ListaUsuarioService;
import trabajoTAW.service.NotificacionService;
import trabajoTAW.service.UsuarioService;

@WebServlet(name = "CompradorVerMensajeServlet", urlPatterns = {"/CompradorVerMensajeServlet"})
public class CompradorVerMensajeServlet extends trabajoTAWServlet {
    /*
    @EJB NotificacionFacade notificacionFacade;
    @EJB UsuarioFacade usuarioFacade;
    @EJB ListaUsuarioFacade listaUsuarioFacade;
    */
    @EJB NotificacionService notificacionService;
    @EJB UsuarioService usuarioService;
    @EJB ListaUsuarioService listaUsuarioService;
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
            String strId = request.getParameter("id");
            UsuarioDTO comprador = usuarioService.buscarUsuario(Integer.parseInt(strId));
            String strIdLista = request.getParameter("idlista");
            request.setAttribute("comprador", comprador);
            List<NotificacionDTO> notificaciones = usuarioService.notificacionesUsuario(Integer.parseInt(strId));
            request.setAttribute("notificaciones", notificaciones);
            request.setAttribute("lista",listaUsuarioService.buscarLista(Integer.parseInt(strIdLista)));
            request.getRequestDispatcher("/WEB-INF/jsp/mensajes.jsp").forward(request, response);
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
