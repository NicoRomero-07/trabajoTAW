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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import trabajoTAW.dto.CategoriaDTO;
import trabajoTAW.entity.Categoria;
import trabajoTAW.entity.Direccion;
import trabajoTAW.entity.TipoUsuario;
import trabajoTAW.entity.Usuario;
import trabajoTAW.service.CategoriaService;
import trabajoTAW.service.DireccionService;
import trabajoTAW.service.UsuarioService;

/**
 *
 * @author nicor
 */
@WebServlet(name = "UsuarioGuardarServlet", urlPatterns = {"/UsuarioGuardarServlet"})
public class UsuarioGuardarServlet extends trabajoTAWServlet {
    
    @EJB UsuarioService us;
    @EJB DireccionService ds;
    @EJB CategoriaService cs;
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
            String nombreUsuario = request.getParameter("nombreUsuario");
            String contrasenya= request.getParameter("contrasenya");
            String email = request.getParameter("email");
            String nombre = request.getParameter("nombre");
            String primerApellido = request.getParameter("primerApellido");
            String segundoApellido = request.getParameter("segundoApellido");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String sexo = request.getParameter("sexo");
            String direccion = request.getParameter("idDireccion");
            String tipoUsuario = request.getParameter("tipoUsuario");
            String[] categorias = request.getParameterValues("categorias");
            
            
            String tipoVia = request.getParameter("tipoVia");
            String calle = request.getParameter("calle");
            String numero = request.getParameter("numero");
            String codigoPostal = request.getParameter("codigoPostal");
            String planta = request.getParameter("planta");
            String puerta = request.getParameter("puerta");
            
            String strId = request.getParameter("id");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimientoDate = null;
            try {
                fechaNacimientoDate = formato.parse(fechaNacimiento);
            } catch (ParseException ex) {
                Logger.getLogger(UsuarioGuardarServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if("".equals(nombreUsuario) || "".equals(contrasenya) || "".equals(email)){
                response.sendRedirect(request.getContextPath() + "/UsuarioNuevoEditarServlet?id="+strId);
            }else{
                if (strId == null || strId.isEmpty()) {    
                    // Crear nuevo cliente
                    this.ds.crearDireccion(tipoVia, calle, Integer.parseInt(numero), Integer.parseInt(codigoPostal), Integer.parseInt(planta),puerta);
                    Direccion d = this.ds.findDireccion(calle, numero);

                this.us.crearUsuario(nombreUsuario, contrasenya, nombre, primerApellido, segundoApellido, email, d.getIdDireccion(), sexo.charAt(0), Integer.parseInt(tipoUsuario), fechaNacimientoDate, categorias);
                } else {                               // Editar cliente
                    this.ds.modificarDireccion(Integer.parseInt(direccion), tipoVia, calle, Integer.parseInt(numero), Integer.parseInt(codigoPostal), Integer.parseInt(planta),puerta);
                    this.us.modificarUsuario(Integer.parseInt(strId),
                                            nombreUsuario, contrasenya, nombre, primerApellido, segundoApellido, email, Integer.parseInt(direccion), sexo.charAt(0), Integer.parseInt(tipoUsuario), fechaNacimientoDate, categorias);
                }

                response.sendRedirect(request.getContextPath() + "/UsuariosServlet");
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
