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
import trabajoTAW.dao.DatosEstudioProductoFacade;
import trabajoTAW.dao.DatosEstudioUsuarioFacade;
import trabajoTAW.dao.EstudioFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.entity.DatosEstudioProducto;
import trabajoTAW.entity.DatosEstudioUsuario;
import trabajoTAW.entity.Estudio;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author Alfonso
 */
@WebServlet(name = "EstudioCopiarServlet", urlPatterns = {"/EstudioCopiarServlet"})
public class EstudioCopiarServlet extends HttpServlet {

    @EJB UsuarioFacade usuarioFacade;
    @EJB EstudioFacade estudioFacade;
    @EJB DatosEstudioProductoFacade estudioProductoFacade;
    @EJB DatosEstudioUsuarioFacade estudioUsuarioFacade;
    
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
        
        List<Usuario> listaUsuarios = this.usuarioFacade.findAll();
        request.setAttribute("usuarios", listaUsuarios);

        String str = request.getParameter("id");
        if (str != null) {
            Estudio estudio = this.estudioFacade.find(Integer.parseInt(str));
            estudio.setDatosEstudioProducto(null);
            estudio.setDatosEstudioUsuario(null);
            estudioFacade.create(estudio);
            DatosEstudioProducto estudioProducto = this.estudioProductoFacade.find(Integer.parseInt(str));
            DatosEstudioUsuario estudioUsuario = this.estudioUsuarioFacade.find(Integer.parseInt(str));
            if(estudioProducto != null){
                estudioProducto.setEstudio(estudio);
                estudioProducto.setId(estudio.getIdEstudio());
                estudioProductoFacade.create(estudioProducto);
                estudio.setDatosEstudioProducto(estudioProducto);
            }else if(estudioUsuario != null){
                estudioUsuario.setEstudio(estudio);
                estudioUsuario.setId(estudio.getIdEstudio());
                estudioUsuarioFacade.create(estudioUsuario);
                estudio.setDatosEstudioUsuario(estudioUsuario);
            }
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
