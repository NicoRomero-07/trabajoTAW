/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                direccion = this.df.find(usuario.getDireccion().getIdDireccion()); 
            }

            str = request.getParameter("nombreUsuario");
            usuario.setNombreUsuario(str);

            str = request.getParameter("contrasenya");
            usuario.setContrasenya(str);

            str = request.getParameter("nombre");
            usuario.setNombre(str);
            
            str = request.getParameter("primerApellido");
            usuario.setPrimerApellido(str);

            str = request.getParameter("segundoApellido");
            usuario.setSegundoApellido(str);
            
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            str = request.getParameter("fechaNacimiento");
            try {
                usuario.setFechaNacimiento(formato.parse(str));
            } catch (ParseException ex) {
                Logger.getLogger(UsuarioGuardarServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            str = request.getParameter("email");
            usuario.setEmail(str);

            str = request.getParameter("sexo");
            usuario.setSexo(str.charAt(0));
            
            str = request.getParameter("tipoVia");
            direccion.setTipo(str);

            str = request.getParameter("calle");
            direccion.setCalle(str);
            
            str = request.getParameter("numero");
            direccion.setNumero(Integer.parseInt(str));
            
            str = request.getParameter("codigoPostal");
            direccion.setCodigoPostal(Integer.parseInt(str));
            
            str = request.getParameter("planta");
            if(!str.equals("")){
                direccion.setPlanta(Integer.parseInt(str));
            }
            
            str = request.getParameter("puerta");
            direccion.setPuerta(str);
            
            str = request.getParameter("tipoUsuario"); 
            TipoUsuario tu = this.tuf.find(Integer.parseInt(str));
            usuario.setTipoUsuario(tu);


            if (strId == null || strId.isEmpty()) {    // Crear nuevo usuario
                df.create(direccion);
                usuario.setDireccion(direccion);
                uf.create(usuario);
            } else {                                   // Editar usuario
                df.edit(direccion);
                usuario.setDireccion(direccion);
                uf.edit(usuario);
            }        

           response.sendRedirect(request.getContextPath() + "/UsuariosServlet");
        
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
