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
import trabajoTAW.dao.EstudioFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.entity.Estudio;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author Alfonso
 */
@WebServlet(name = "EstudioGuardarServlet", urlPatterns = {"/EstudioGuardarServlet"})
public class EstudioGuardarServlet extends HttpServlet {

    @EJB EstudioFacade estudioFacade;
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
        
            String strId, str;
            Estudio estudio;

            strId = request.getParameter("id");

            if (strId == null || strId.isEmpty()) {    // Crear nuevo estudio
                estudio = new Estudio();
            } else {                               // Editar estudio
                estudio = this.estudioFacade.find(Integer.parseInt(strId));
            }

            str = request.getParameter("analista");
            Usuario user = this.usuarioFacade.find(Integer.parseInt(str));
            estudio.setAnalista(user);

            str = request.getParameter("vendedor");
            user = this.usuarioFacade.find(Integer.parseInt(str));
            estudio.setVendedor(user);

            str = request.getParameter("ingreso");
            estudio.setIngreso(Double.parseDouble(str));


            if (strId == null || strId.isEmpty()) {    // Crear nuevo estudio
                estudioFacade.create(estudio);
            } else {                                   // Editar estudio
                estudioFacade.edit(estudio);
            }        

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
