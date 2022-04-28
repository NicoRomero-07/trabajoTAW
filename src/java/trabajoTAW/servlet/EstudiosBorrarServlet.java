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
import trabajoTAW.dao.DatosEstudioProductoFacade;
import trabajoTAW.dao.DatosEstudioUsuarioFacade;
import trabajoTAW.dao.EstudioFacade;
import trabajoTAW.entity.DatosEstudioProducto;
import trabajoTAW.entity.DatosEstudioUsuario;
import trabajoTAW.entity.Estudio;

/**
 *
 * @author Alfonso
 */
@WebServlet(name = "EstudiosBorrarServlet", urlPatterns = {"/EstudiosBorrarServlet"})
public class EstudiosBorrarServlet extends HttpServlet {

    @EJB EstudioFacade estudioFacade;
    @EJB DatosEstudioProductoFacade estudioProductoFacade;
    @EJB DatosEstudioUsuarioFacade estudioUsuarioFacade;
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
        
         String str = request.getParameter("id");
         Estudio estudio = this.estudioFacade.find(Integer.parseInt(str));
         if(estudio.getDatosEstudioProducto() != null){
             DatosEstudioProducto estudioProducto = this.estudioProductoFacade.find(Integer.parseInt(str));
             this.estudioProductoFacade.remove(estudioProducto);
         }else if (estudio.getDatosEstudioUsuario() != null){
             DatosEstudioUsuario estudioUsuario = this.estudioUsuarioFacade.find(Integer.parseInt(str));
             this.estudioUsuarioFacade.remove(estudioUsuario);
         }
         this.estudioFacade.remove(estudio);
         response.sendRedirect(request.getContextPath() + "/EstudiosServlet");
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
