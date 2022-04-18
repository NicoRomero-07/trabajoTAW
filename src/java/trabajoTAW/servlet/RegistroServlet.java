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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trabajoTAW.dao.UsuarioFacade;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        
        Date fechaNacimiento = null;
        String nombreUsuario = request.getParameter("nombreusuario");
        String clave = request.getParameter("contrasenya"); 
        String email = request.getParameter("email");
        String nombre = request.getParameter("nombre");
        String primerApellido = request.getParameter("primerapellido"); 
        String segundoApellido = request.getParameter("segundoapellido");
        try{
            fechaNacimiento = simpleDateFormat.parse(request.getParameter("fechadenacimiento"));
        }catch(ParseException e){
            System.out.print(e);
        }
        String sexo = request.getParameter("sexo");
        String direccion = request.getParameter("direccion");
        
        if(!nombreUsuario.equalsIgnoreCase("") && !clave.equalsIgnoreCase("") && !email.equalsIgnoreCase("") &&
                !nombre.equalsIgnoreCase("") && !primerApellido.equalsIgnoreCase(sexo) &&
                fechaNacimiento != null && !sexo.equalsIgnoreCase("") &&
                !direccion.equalsIgnoreCase("")){
            Usuario usuario = new Usuario(1,nombreUsuario,clave,email,nombre,primerApellido,segundoApellido,
            fechaNacimiento,sexo.charAt(0));
            boolean sw = UsuarioFacade.agregarUsuario(usuario);
            if(sw){
                request.getRequestDispatcher("registro.jsp").forward(request, response);
            }else{
                PrintWriter out = response.getWriter();
                out.println("Algo ha salio mal");
            }
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
