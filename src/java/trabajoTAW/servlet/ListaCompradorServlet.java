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
import trabajoTAW.dao.ListaUsuarioFacade;
import trabajoTAW.entity.ListaUsuario;

/**
 *
 * @author nicol
 */
@WebServlet(name = "ListaCompradorServlet", urlPatterns = {"/ListaCompradorServlet"})
public class ListaCompradorServlet extends HttpServlet {
    
    @EJB ListaUsuarioFacade listaUsuarioFacade;
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
        String filtroNombre = request.getParameter("filtroNombre");
        String filtroId = request.getParameter("filtroId");
        List<ListaUsuario> listasCompradores = null;

            if ((filtroNombre == null || filtroNombre.isEmpty()) && (filtroId == null || filtroId.isEmpty())) {
                listasCompradores = this.listaUsuarioFacade.findAll();        
            } else if ((filtroNombre != null) && (filtroId == null || filtroId.isEmpty())){
                listasCompradores = this.listaUsuarioFacade.findByNombre(filtroNombre);
            } else if ((filtroNombre == null || filtroNombre.isEmpty()) && (filtroId != null)){
                listasCompradores = this.listaUsuarioFacade.findById(Integer.parseInt(filtroId));
            } else if ((filtroNombre != null) && (filtroId != null)){
                listasCompradores = this.listaUsuarioFacade.findByIdNombre(Integer.parseInt(filtroId),filtroNombre);
            }
        
        request.setAttribute("listasCompradores", listasCompradores);
        request.getRequestDispatcher("listasCompradores.jsp").forward(request, response);
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