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
import trabajoTAW.dao.DatosEstudioProductoFacade;
import trabajoTAW.dao.DatosEstudioUsuarioFacade;
import trabajoTAW.dao.EstudioFacade;
import trabajoTAW.dao.ProductoFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.entity.DatosEstudioProducto;
import trabajoTAW.entity.DatosEstudioUsuario;
import trabajoTAW.entity.Estudio;
import trabajoTAW.entity.Producto;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author Alfonso
 */
@WebServlet(name = "EstudioVisualizarServlet", urlPatterns = {"/EstudioVisualizarServlet"})
public class EstudioVisualizarServlet extends trabajoTAWServlet {

    @EJB EstudioFacade estudioFacade;
    @EJB UsuarioFacade usuarioFacade;
    @EJB ProductoFacade productoFacade;
    
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
        
        if(super.comprobarSession(request, response)){
            
            String idEstudio = request.getParameter("id");
            Estudio estudio = (Estudio) this.estudioFacade.find(Integer.parseInt(idEstudio));
            request.setAttribute("estudio",estudio);
            DatosEstudioProducto estudioProducto = estudio.getDatosEstudioProducto();
            DatosEstudioUsuario estudioUsuario = estudio.getDatosEstudioUsuario();
            
            if(estudioProducto != null){
                List<Producto> listaProductos = this.productoFacade.visualizarEstudio(estudioProducto);
                request.setAttribute("listaProductos",listaProductos);
            }else if(estudioUsuario != null){
                List<Usuario> listaUsuarios = this.usuarioFacade.visualizarEstudio(estudio,estudioUsuario);
                request.setAttribute("listaUsuarios",listaUsuarios);
            }
            
            request.getRequestDispatcher("visualizarEstudio.jsp").forward(request, response);
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
