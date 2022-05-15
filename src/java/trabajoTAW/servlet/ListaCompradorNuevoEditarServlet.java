/**
 *
 * @author Nicolás Zhao(100%)
 */
package trabajoTAW.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trabajoTAW.dto.ListaUsuarioDTO;
import trabajoTAW.dto.UsuarioDTO;
import trabajoTAW.service.ListaUsuarioService;
import trabajoTAW.service.UsuarioService;
//import trabajoTAW.dao.ListaUsuarioFacade;
//import trabajoTAW.dao.UsuarioFacade;
//import trabajoTAW.entity.ListaUsuario;
//import trabajoTAW.entity.Usuario;

@WebServlet(name = "ListaCompradorNuevoEditarServlet", urlPatterns = {"/ListaCompradorNuevoEditarServlet"})
public class ListaCompradorNuevoEditarServlet extends trabajoTAWServlet {
    
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
            List<UsuarioDTO> compradores = this.usuarioService.getCompradores();

            String str = request.getParameter("id");
            ListaUsuarioDTO listaComprador = null;
            if (str != null) {
                listaComprador = this.listaUsuarioService.buscarLista(Integer.parseInt(str));
            }
            Map<UsuarioDTO,List<ListaUsuarioDTO>> relaciones = new HashMap<>();

            for (UsuarioDTO comprador :compradores){       
                relaciones.put(comprador, this.usuarioService.listasUsuario(comprador.getIdUsuario()));
            }
            request.setAttribute("relaciones", relaciones);
            request.setAttribute("listaComprador", listaComprador);
            request.getRequestDispatcher("/WEB-INF/jsp/listaComprador.jsp").forward(request, response);
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
