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
import javax.servlet.http.HttpSession;
import trabajoTAW.service.ProductoService;

/**
 *
 * @author nicor
 */
@WebServlet(name = "ProductoGuardarServlet", urlPatterns = {"/ProductoGuardarServlet"})
public class ProductoGuardarServlet extends trabajoTAWServlet {

    @EJB ProductoService ps;
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
            String tipo = super.comprobarTipoUsuario(request, response);
            
            String nombreProducto = request.getParameter("nombreproducto");
            String descripcion= request.getParameter("descripcion");
            String precioSalida = request.getParameter("preciosalida");
            String imagen = request.getParameter("imagen");
            String fechaInicio = request.getParameter("fechaInicio");
            String fechaFin = request.getParameter("fechaFin");
            String comprador = request.getParameter("comprador");
            String publicador = "";
            if("Administrador".equals(tipo)){
                publicador = request.getParameter("publicador");
            }else{
                publicador = request.getParameter("vendedor");
            }
            String promocion = request.getParameter("promocion");
            
            String categoria = request.getParameter("categoria");
            
            String strId = request.getParameter("id");
            
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicioDate = null;
            Date fechaFinDate = null;
            try {
                fechaInicioDate = formato.parse(fechaInicio);
                fechaFinDate = formato.parse(fechaFin);
            } catch (ParseException ex) {
                Logger.getLogger(UsuarioGuardarServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (strId == null || strId.isEmpty()) {
                this.ps.crearProducto(nombreProducto, descripcion, Double.parseDouble(precioSalida), imagen, fechaInicioDate, fechaFinDate, comprador, publicador, Boolean.parseBoolean(promocion), Integer.parseInt(categoria));
            } else {                               
                this.ps.modificarProducto(Integer.parseInt(strId), nombreProducto, descripcion, Double.parseDouble(precioSalida), imagen, fechaInicioDate, fechaFinDate, comprador, publicador, Boolean.parseBoolean(promocion), Integer.parseInt(categoria));
            }

            
            if("Administrador".equals(tipo)){
                response.sendRedirect(request.getContextPath() + "/ProductosServlet");
            }else{
                response.sendRedirect(request.getContextPath() + "/ListaVendedorServlet");
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
