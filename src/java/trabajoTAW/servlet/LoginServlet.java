/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trabajoTAW.dto.UsuarioDTO;
import trabajoTAW.service.UsuarioService;

/**
 *
 * @author nicor (85%) Pablo (5%) Alfonso (5%)
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @EJB UsuarioService us;
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
        
        String usuario = request.getParameter("nombreusuario");
        String clave = request.getParameter("contrasenya");        
        
        UsuarioDTO user = this.us.comprobarUsuario(usuario, clave);
        
        
        if (user == null) {
            String strError = "El usuario o la clave son incorrectos";
            request.setAttribute("error", strError);
            request.getRequestDispatcher("login.jsp").forward(request, response);                
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", user);
            
            if(user.getTipoUsuario().getTipo().equalsIgnoreCase("Administrador")){
                response.sendRedirect(request.getContextPath() + "/AdministradorServlet");
            }else if (user.getTipoUsuario().getTipo().equalsIgnoreCase("Analista")){
                response.sendRedirect(request.getContextPath() + "/EstudiosServlet");
            }else if (user.getTipoUsuario().getTipo().equalsIgnoreCase("Marketing")){
                response.sendRedirect(request.getContextPath() + "/ListaCompradorServlet");
            }else if(user.getTipoUsuario().getTipo().equalsIgnoreCase("Comprador")){
                response.sendRedirect(request.getContextPath() + "/CompradorPrincipalServlet");
            }else if(user.getTipoUsuario().getTipo().equalsIgnoreCase("Vendedor")){
                response.sendRedirect(request.getContextPath() + "/ListaVendedorServlet");
            }else{
                response.sendRedirect(request.getContextPath() + "/index.html");
            }
                            
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
