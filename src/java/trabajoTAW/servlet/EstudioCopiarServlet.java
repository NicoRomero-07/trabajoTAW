/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trabajoTAW.dto.DatosEstudioProductoDTO;
import trabajoTAW.dto.DatosEstudioUsuarioDTO;
import trabajoTAW.dto.EstudioDTO;
import trabajoTAW.dto.UsuarioDTO;
import trabajoTAW.service.DatosEstudioProductoService;
import trabajoTAW.service.DatosEstudioUsuarioService;
import trabajoTAW.service.EstudioService;
import trabajoTAW.service.UsuarioService;

/**
 *
 * @author Alfonso
 */
@WebServlet(name = "EstudioCopiarServlet", urlPatterns = {"/EstudioCopiarServlet"})
public class EstudioCopiarServlet extends trabajoTAWServlet {

    @EJB UsuarioService usuarioService;
    @EJB EstudioService estudioService;
    @EJB DatosEstudioProductoService estudioProductoService;
    @EJB DatosEstudioUsuarioService estudioUsuarioService;
    
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
        if(super.comprobarSession(request, response)){
            List<UsuarioDTO> listaUsuarios = this.usuarioService.listarUsuarios(null);
            request.setAttribute("usuarios", listaUsuarios);

            String id = request.getParameter("id");
            if (id != null) {
                this.estudioService.copy(id);
            }
            response.sendRedirect(request.getContextPath() + "/EstudiosServlet");   
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
