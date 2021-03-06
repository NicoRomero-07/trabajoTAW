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
import javax.servlet.http.HttpSession;
import trabajoTAW.dto.ProductoDTO;
import trabajoTAW.dto.PujaDTO;
import trabajoTAW.dto.UsuarioDTO;
import trabajoTAW.service.ProductoService;
import trabajoTAW.service.PujaService;

/**
 *
 * @author Victor
 */
@WebServlet(name = "PujaServlet", urlPatterns = {"/PujaServlet"})
public class PujaServlet extends trabajoTAWServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB ProductoService ps;
    @EJB PujaService pus;
    protected void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(super.comprobarSession(request, response)){
        
            HttpSession session = request.getSession();
            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        
            request.setAttribute("usuario", usuario);
        
            String idProducto = request.getParameter("id");
            ProductoDTO p = ps.buscarProducto(Integer.parseInt(idProducto));
        
            request.setAttribute("producto", p);
        
            List<PujaDTO> listaPujas = pus.buscarPujas(Integer.parseInt(idProducto));
            request.setAttribute("listaPujas", listaPujas);
        
            double precioActual = pus.calcularPrecioActual(listaPujas, p);
            request.setAttribute("precioActual", precioActual);
        
        request.getRequestDispatcher("/WEB-INF/jsp/puja.jsp").forward(request, response);
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
