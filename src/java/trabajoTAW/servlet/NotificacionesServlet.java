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
import javax.servlet.http.HttpSession;
import trabajoTAW.dto.ProductoDTO;
import trabajoTAW.dto.UsuarioDTO;
import trabajoTAW.service.ListaProductoService;
import trabajoTAW.service.ProductoService;
/**
 *
 * @author Victor
 */
@WebServlet(name = "NotificacionesServlet", urlPatterns = {"/NotificacionesServlet"})
public class NotificacionesServlet extends trabajoTAWServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB ProductoService ps;
    @EJB ListaProductoService lps;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(super.comprobarSession(request, response)){
            
            HttpSession session = request.getSession();
            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
            request.setAttribute("usuario", usuario);
            
            List<ProductoDTO> pujas = ps.buscarProductosPujados(usuario.getIdUsuario());
            request.setAttribute("pujas", pujas);
            
            List<ProductoDTO> favoritos = lps.buscarListaFavoritos(usuario.getIdUsuario());
            request.setAttribute("favoritos", favoritos);

            
            request.getRequestDispatcher("/WEB-INF/jsp/notificaciones.jsp").forward(request, response);
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
