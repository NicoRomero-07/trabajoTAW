/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import trabajoTAW.dao.ListaUsuarioFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.entity.ListaUsuario;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author nicol
 */
@WebServlet(name = "ListaCompradorGuardarServlet", urlPatterns = {"/ListaCompradorGuardarServlet"})
public class ListaCompradorGuardarServlet extends trabajoTAWServlet {
    
        @EJB ListaUsuarioFacade listaUsuarioFacade;
        @EJB UsuarioFacade usuarioFacade;

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
            String strId, strNombre;
            String[] compradores;
            ListaUsuario listaComprador;

            strId = request.getParameter("id");
            if (strId == null || strId.isEmpty()) {// Crear nueva lista comprador
                listaComprador = new ListaUsuario();
            } else {                               // Editar lista comprador
                listaComprador = this.listaUsuarioFacade.find(Integer.parseInt(strId));
            }

            strNombre = request.getParameter("nombre");
            listaComprador.setNombre(strNombre);

            compradores = request.getParameterValues("compradores");
            for (String idComprador: compradores){
                Integer id = Integer.parseInt(idComprador);
                Usuario comprador = this.usuarioFacade.find(id);          

                List<Usuario> compradoresRelacionados = listaComprador.getUsuarioList() == null?new ArrayList(): listaComprador.getUsuarioList();
                if (!compradoresRelacionados.contains(comprador)){
                    compradoresRelacionados.add(comprador);
                }
                listaComprador.setUsuarioList(compradoresRelacionados);
            }

            if (strId == null || strId.isEmpty()) {    // Crear nueva lista comprador
                listaUsuarioFacade.create(listaComprador);
            } else {                                   // Editar lista comprador
                listaUsuarioFacade.edit(listaComprador);
            } 


            response.sendRedirect(request.getContextPath() + "/ListaCompradorServlet");
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
