/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trabajoTAW.dao.ListaUsuarioFacade;
import trabajoTAW.dao.UsuarioFacade;
//import trabajoTAW.dao.UsuarioListaUsuarioFacade;
import trabajoTAW.entity.ListaUsuario;
import trabajoTAW.entity.Usuario;
//import trabajoTAW.entity.UsuarioListaUsuario;

/**
 *
 * @author nicol
 */
@WebServlet(name = "ListaCompradorGuardarServlet", urlPatterns = {"/ListaCompradorGuardarServlet"})
public class ListaCompradorGuardarServlet extends HttpServlet {
    
        @EJB ListaUsuarioFacade listaUsuarioFacade;
        @EJB UsuarioFacade usuarioFacade;
      //  @EJB UsuarioListaUsuarioFacade usuarioListaUsuarioFacade;

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
        String strId, strNombre;
        String[] compradores;
        ListaUsuario listaComprador;
        //UsuarioListaUsuario usuarioListaUsuario;

        strId = request.getParameter("id");
        if (strId == null || strId.isEmpty()) {// Crear nueva lista comprador
            listaComprador = new ListaUsuario();
        } else {                               // Editar lista comprador
            listaComprador = this.listaUsuarioFacade.find(Integer.parseInt(strId));
        }
        
        strNombre = request.getParameter("nombre");
        listaComprador.setNombre(strNombre);
        
        compradores = request.getParameterValues("compradores");
        for (String nombreUsuario: compradores){
         
            /*
         usuarioListaUsuario = new UsuarioListaUsuario();
         usuarioListaUsuario.setUsuario1(this.usuarioFacade.find(nombreUsuario));
         usuarioListaUsuario.setListaUsuario(this.listaUsuarioFacade.find(strId));
         usuarioListaUsuarioFacade.create(usuarioListaUsuario);   
            */
        }
        
        if (strId == null || strId.isEmpty()) {    // Crear nuevo cliente
            listaUsuarioFacade.create(listaComprador);
        } else {                                   // Editar cliente
            listaUsuarioFacade.edit(listaComprador);
        } 
        
        
        response.sendRedirect(request.getContextPath() + "/ListaCompradorServlet");
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
