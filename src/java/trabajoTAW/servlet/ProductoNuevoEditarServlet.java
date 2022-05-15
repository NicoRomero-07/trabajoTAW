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
import trabajoTAW.dao.CategoriaFacade;
import trabajoTAW.dto.ProductoDTO;
import trabajoTAW.entity.Categoria;
import trabajoTAW.service.ProductoService;

/**
 *
 * @author Pablo
 */
@WebServlet(name = "ProductoNuevoEditarServlet", urlPatterns = {"/ProductoNuevoEditarServlet"})
public class ProductoNuevoEditarServlet extends trabajoTAWServlet {

    @EJB CategoriaFacade cf;
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
        
        List<Categoria> listaCategorias = this.cf.findAll();
        
        String id = request.getParameter("id");
        request.setAttribute("categorias", listaCategorias);
        
        ProductoDTO producto = null;
        if(id != null) {
            //Editando
            producto = this.ps.buscarProducto(Integer.parseInt(id));
        }
        request.setAttribute("producto", producto);
        
        String tipo = super.comprobarTipoUsuario(request, response);
        if("Administrador".equals(tipo)){
            request.getRequestDispatcher("producto.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/jsp/publicarProducto.jsp").forward(request, response);
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
