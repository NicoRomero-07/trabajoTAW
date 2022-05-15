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
import trabajoTAW.dto.DatosEstudioProductoDTO;
import trabajoTAW.dto.DatosEstudioUsuarioDTO;
import trabajoTAW.dto.EstudioDTO;
import trabajoTAW.dto.ProductoDTO;
import trabajoTAW.dto.UsuarioDTO;
import trabajoTAW.service.EstudioService;
import trabajoTAW.service.ProductoService;
import trabajoTAW.service.UsuarioService;

/**
 *
 * @author Alfonso 100%
 */
@WebServlet(name = "EstudioVisualizarServlet", urlPatterns = {"/EstudioVisualizarServlet"})
public class EstudioVisualizarServlet extends trabajoTAWServlet {

    @EJB EstudioService estudioService;
    @EJB UsuarioService usuarioService;
    @EJB ProductoService productoService;
    
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
            EstudioDTO estudio = (EstudioDTO) this.estudioService.find(Integer.parseInt(idEstudio));
            request.setAttribute("estudio",estudio);
            DatosEstudioProductoDTO estudioProducto = estudio.getDatosEstudioProducto();
            DatosEstudioUsuarioDTO estudioUsuario = estudio.getDatosEstudioUsuario();
            
            if(estudioProducto != null){
                List<ProductoDTO> listaProductos = this.productoService.visualizarEstudio(estudioProducto.getId());
                request.setAttribute("listaProductos",listaProductos);
            }else if(estudioUsuario != null){
                List<UsuarioDTO> listaUsuarios = this.usuarioService.visualizarEstudio(estudio.getIdEstudio(),estudioUsuario.getId());
                request.setAttribute("listaUsuarios",listaUsuarios);
                if(estudioUsuario.getIngresos()){
                   List<Double> ingresos = this.usuarioService.getIngresosUsuarios(estudio.getIdEstudio(),estudioUsuario.getId());
                   request.setAttribute("ingresos",ingresos);
                }
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
