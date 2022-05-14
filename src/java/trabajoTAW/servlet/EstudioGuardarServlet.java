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
import trabajoTAW.dao.EstudioFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.dto.EstudioDTO;
import trabajoTAW.entity.Estudio;
import trabajoTAW.entity.Usuario;
import trabajoTAW.service.EstudioService;
import trabajoTAW.service.UsuarioService;

/**
 *
 * @author Alfonso
 */
@WebServlet(name = "EstudioGuardarServlet", urlPatterns = {"/EstudioGuardarServlet"})
public class EstudioGuardarServlet extends trabajoTAWServlet {

    @EJB
    EstudioService estudioService;
    @EJB
    UsuarioService usuarioService;

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
            String nombre = request.getParameter("nombre");
            String analista = request.getParameter("analista");
            String descripcion = request.getParameter("descripcion");
            String element = request.getParameter("element");
            
            if (strId == null || strId.isEmpty()) {    // Crear nuevo estudio
                EstudioDTO estudioDTO = estudioService.create(nombre,analista,descripcion,element,null,null);
                strId = estudioDTO.getIdEstudio().toString();
            } else {                                   // Editar estudio
                estudioService.edit(strId,nombre,analista,descripcion,element,null,null);
            }
            
            
            response.sendRedirect(request.getContextPath() + "/DatosEstudioNuevoEditarServlet?id=" + strId);
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