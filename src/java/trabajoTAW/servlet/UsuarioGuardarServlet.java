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
import trabajoTAW.dao.CategoriaFacade;
import trabajoTAW.dao.DireccionFacade;
import trabajoTAW.dao.TipoUsuarioFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.entity.Categoria;
import trabajoTAW.entity.Direccion;
import trabajoTAW.entity.TipoUsuario;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author nicor
 */
@WebServlet(name = "UsuarioGuardarServlet", urlPatterns = {"/UsuarioGuardarServlet"})
public class UsuarioGuardarServlet extends trabajoTAWServlet {
    @EJB TipoUsuarioFacade tuf;
    @EJB CategoriaFacade cf;
    @EJB UsuarioFacade uf;
    @EJB DireccionFacade df;
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
        
            String strId, str;
            int number;
            Usuario usuario;
            Direccion direccion;
            strId = request.getParameter("id");

            if (strId == null || strId.isEmpty()) {    // Crear nuevo usuario
                usuario = new Usuario();
                direccion = new Direccion();
            } else {                               // Editar usuario
                usuario = this.uf.find(Integer.parseInt(strId));
                direccion = this.df.find(usuario.getDireccion()); // ERROR EN ESTA LINEA
            }

            str = request.getParameter("nombreUsuario");
            usuario.setNombreUsuario(str);

            str = request.getParameter("nombre");
            usuario.setNombre(str);
            
            str = request.getParameter("primerApellido");
            usuario.setPrimerApellido(str);

            str = request.getParameter("segundoApellido");
            usuario.setSegundoApellido(str);
          
            str = request.getParameter("email");
            usuario.setEmail(str);

            str = request.getParameter("tipoVia");
            direccion.setTipo(str);

            str = request.getParameter("calle");
            direccion.setCalle(str);
            
            str = request.getParameter("numero");
            direccion.setNumero(Integer.parseInt(str));
            
            str = request.getParameter("codigoPostal");
            direccion.setCodigoPostal(Integer.parseInt(str));
            
            str = request.getParameter("planta");
            direccion.setPlanta(Integer.parseInt(str));
            
            str = request.getParameter("puerta");
            direccion.setPuerta(str);
            
            usuario.setDireccion(direccion);
            
            str = request.getParameter("tipoUsuario");   
            TipoUsuario tu = this.tuf.find(str);
            usuario.setTipoUsuario(tu);

            str = request.getParameter("categoria");  
            Categoria c = this.cf.find(str);
            usuario.setCategoriaFavorita(c);

            if (strId == null || strId.isEmpty()) {    // Crear nuevo usuario
                df.create(direccion);
                uf.create(usuario);
            } else {                                   // Editar usuario
                df.edit(direccion);
                uf.edit(usuario);
            }        

           response.sendRedirect(request.getContextPath() + "/UsuarioServlet");
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
