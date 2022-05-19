/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trabajoTAW.dto.ProductoDTO;
import trabajoTAW.dto.PujaDTO;
import trabajoTAW.dto.UsuarioDTO;
import trabajoTAW.entity.Usuario;
import trabajoTAW.service.ProductoService;
import trabajoTAW.service.PujaService;
import trabajoTAW.service.UsuarioService;

/**
 *
 * @author Pablo
 */
@WebServlet(name = "TerminarPujaServlet", urlPatterns = {"/TerminarPujaServlet"})
public class TerminarPujaServlet extends HttpServlet {

    @EJB ProductoService pds;
    @EJB PujaService pjs;
    @EJB UsuarioService us;
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            ProductoDTO producto = this.pds.buscarProducto(Integer.parseInt(id));
            List<PujaDTO> pujas = this.pjs.buscarPujas(Integer.parseInt(id));
            String comprador = null;
            Date preciofin = producto.getFechaFinSubasta();
            if(!pujas.isEmpty()) {
                UsuarioDTO usuario = this.us.getUsuarioPujaMax(Integer.parseInt(id));
                comprador = usuario.getNombreUsuario();
                preciofin = new Date();
            }
            this.pds.modificarProducto(Integer.parseInt(id), producto.getNombre(), producto.getDescripcion(), producto.getPrecioSalida(), producto.getUrlFoto(), producto.getFechaInicioSubasta(), preciofin, comprador, producto.getPublicador().getNombreUsuario(), producto.getEnPromocion(), producto.getCategoria());
            
            response.sendRedirect(request.getContextPath() + "/ListaVendedorServlet");
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
