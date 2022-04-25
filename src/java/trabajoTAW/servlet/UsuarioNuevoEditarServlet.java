/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import trabajoTAW.dao.CategoriaFacade;
import trabajoTAW.dao.TipoUsuarioFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.entity.Categoria;
import trabajoTAW.entity.TipoUsuario;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author nicor
 */
@WebServlet(name = "UsuarioNuevoEditarServlet", urlPatterns = {"/UsuarioNuevoEditarServlet"})
public class UsuarioNuevoEditarServlet extends trabajoTAWServlet {

    @EJB TipoUsuarioFacade tuf;
    @EJB CategoriaFacade cf;
    @EJB UsuarioFacade uf;
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
        
            List<TipoUsuario> listaTipoUsuario = this.tuf.findAll();
            List<Categoria> listaCategoria = this.cf.findAll();

            request.setAttribute("tipoUsuarios", listaTipoUsuario);
            request.setAttribute("categorias", listaCategoria);

            String str = request.getParameter("id");
            if (str != null) {
                Usuario usuario = this.uf.find(Integer.parseInt(str));

                request.setAttribute("usuario", usuario);
            }

            request.getRequestDispatcher("usuario.jsp").forward(request, response);
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
