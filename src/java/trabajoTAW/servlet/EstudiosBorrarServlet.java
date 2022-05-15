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
import trabajoTAW.dto.DatosEstudioProductoDTO;
import trabajoTAW.dto.DatosEstudioUsuarioDTO;
import trabajoTAW.dto.EstudioDTO;
import trabajoTAW.service.DatosEstudioProductoService;
import trabajoTAW.service.DatosEstudioUsuarioService;
import trabajoTAW.service.EstudioService;

/**
 *
 * @author Alfonso 100%
 */
@WebServlet(name = "EstudiosBorrarServlet", urlPatterns = {"/EstudiosBorrarServlet"})
public class EstudiosBorrarServlet extends trabajoTAWServlet {

    @EJB
    EstudioService estudioService;
    @EJB
    DatosEstudioProductoService estudioProductoService;
    @EJB
    DatosEstudioUsuarioService estudioUsuarioService;

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
            EstudioDTO estudio = this.estudioService.find(Integer.parseInt(str));
            if (estudio.getDatosEstudioProducto() != null) {
                DatosEstudioProductoDTO estudioProducto = this.estudioProductoService.find(Integer.parseInt(str));
                this.estudioProductoService.remove(estudioProducto.getId());
            } else if (estudio.getDatosEstudioUsuario() != null) {
                DatosEstudioUsuarioDTO estudioUsuario = this.estudioUsuarioService.find(Integer.parseInt(str));
                this.estudioUsuarioService.remove(estudioUsuario.getId());
            }
            this.estudioService.remove(estudio.getIdEstudio());
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
