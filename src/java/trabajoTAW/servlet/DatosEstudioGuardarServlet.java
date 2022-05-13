/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trabajoTAW.dto.DatosEstudioProductoDTO;
import trabajoTAW.dto.DatosEstudioUsuarioDTO;
import trabajoTAW.dto.EstudioDTO;
import trabajoTAW.service.DatosEstudioProductoService;
import trabajoTAW.service.DatosEstudioUsuarioService;
import trabajoTAW.service.EstudioService;

/**
 *
 * @author Alfonso
 */
@WebServlet(name = "DatosEstudioGuardarServlet", urlPatterns = {"/DatosEstudioGuardarServlet"})
public class DatosEstudioGuardarServlet extends trabajoTAWServlet {

    @EJB
    DatosEstudioProductoService estudioProductoService;
    @EJB
    DatosEstudioUsuarioService estudioUsuarioService;
    @EJB
    EstudioService estudioService;

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
        if (super.comprobarSession(request, response)) {
            EstudioDTO estudio;
            DatosEstudioProductoDTO estudioProducto;
            DatosEstudioUsuarioDTO estudioUsuario;

            String strIdEstudio = request.getParameter("idEstudio");
            String strIdEstudioProducto = request.getParameter("idEstudioProducto");
            String[] elementsProducto = request.getParameterValues("estudioProducto");
            String precioSalida = request.getParameter("precioSalida");
            String precioActual = request.getParameter("precioActual");
            String strIdEstudioUsuario = request.getParameter("idEstudioUsuario");
            String[] elementsUsuario = request.getParameterValues("estudioUsuario");
            
            Boolean categorias = Boolean.FALSE;
            Boolean vendidos = Boolean.FALSE;
            Boolean promocion = Boolean.FALSE;

            if (elementsProducto != null) {
                for (String s : elementsProducto) {
                    if (s.equals("categorias")) {
                        categorias = Boolean.TRUE;
                    } else if (s.equals("vendidos")) {
                        vendidos = Boolean.TRUE;
                    } else if (s.equals("enPromocion")) {
                         promocion = Boolean.TRUE;
                    }
                }
            }
                
            if (strIdEstudioProducto == null || strIdEstudioProducto.isEmpty()) {    // Crear nuevo estudio
                estudioProductoService.create(categorias,vendidos,
                    promocion,Double.parseDouble(precioSalida),
                    Double.parseDouble(precioActual),strIdEstudio);
            } else {                                                                // Editar estudio
                estudioProductoService.edit(strIdEstudioProducto,categorias,
                    vendidos,promocion,Double.parseDouble(precioSalida),
                    Double.parseDouble(precioActual),strIdEstudio);
            }

            estudioService.edit(strIdEstudio,null,null,null,null,strIdEstudioProducto,strIdEstudioUsuario);        
            
            Boolean nombre = Boolean.FALSE;
            Boolean apellidos = Boolean.FALSE;
            Boolean ingresos = Boolean.FALSE;
            Boolean ascendente = Boolean.FALSE;

            if (elementsUsuario != null) {
                for (String s : elementsUsuario) {
                    if (s.equals("nombre")) {
                        nombre = Boolean.TRUE;
                    } else if (s.equals("apellidos")) {
                        apellidos = Boolean.TRUE;
                    } else if (s.equals("ingresos")) {
                        ingresos = Boolean.TRUE;
                    } else if (s.equals("ascendente")) {
                        ascendente = Boolean.TRUE;
                    }
                }
            }
                
            if (strIdEstudioUsuario == null || strIdEstudioUsuario.isEmpty()) {    // Crear nuevo estudio
                estudioUsuarioService.create(nombre,apellidos,ingresos,ascendente);
            } else {                                                                // Editar estudio
                estudioUsuarioService.edit(strIdEstudio,nombre,apellidos,ingresos,ascendente);
            }

            estudioService.edit(strIdEstudio,null,null,null,null,strIdEstudioProducto,strIdEstudioUsuario);
                
            response.sendRedirect(request.getContextPath() + "/EstudiosServlet");
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
