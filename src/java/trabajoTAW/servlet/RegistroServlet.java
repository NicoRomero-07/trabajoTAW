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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trabajoTAW.dao.DireccionFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.entity.Direccion;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author Victor
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @EJB UsuarioFacade usuarioFacade;
    @EJB DireccionFacade direccionFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        
        Usuario usuario = new Usuario();
        Direccion direccion = new Direccion();
        
        String str = request.getParameter("tipo");
        direccion.setTipo(str);
        
        str = request.getParameter("calle");
        direccion.setCalle(str);
        
        str = request.getParameter("numero");
        direccion.setNumero(Integer.parseInt(str));
        
        str = request.getParameter("codigopostal");
        //direccion.setCodigoPostal(Integer.parseInt(str));
        
        str = request.getParameter("planta");
        //direccion.setPlanta(Integer.parseInt(str)); 
        
        str = request.getParameter("puerta");
        direccion.setPuerta(str);

        str = request.getParameter("nombreusuario");
        usuario.setNombreUsuario(str);
        
        str = request.getParameter("contrasenya");
        usuario.setContrasenya(str);

        str = request.getParameter("email");
        usuario.setEmail(str);

        str = request.getParameter("nombre");
        usuario.setNombre(str);
        
        str = request.getParameter("primerapellido");
        usuario.setPrimerApellido(str);
        
        str = request.getParameter("segundoapellido");
        usuario.setSegundoApellido(str);
        
        str = request.getParameter("fechanacimiento");
        /*try {
            usuario.setFechaNacimiento(simpleDateFormat.parse(str));
        } catch (ParseException ex) {
            Logger.getLogger(RegistroServlet.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        str = request.getParameter("sexo");
        //usuario.setSexo(str.charAt(0));

        usuario.setDireccion(direccion);
        
        usuarioFacade.create(usuario);
        request.getRequestDispatcher("registro.jsp").forward(request, response);
        

        
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
