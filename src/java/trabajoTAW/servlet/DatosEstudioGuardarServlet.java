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
import trabajoTAW.dao.DatosEstudioProductoFacade;
import trabajoTAW.dao.DatosEstudioUsuarioFacade;
import trabajoTAW.dao.EstudioFacade;
import trabajoTAW.entity.DatosEstudioProducto;
import trabajoTAW.entity.DatosEstudioUsuario;
import trabajoTAW.entity.Estudio;

/**
 *
 * @author Alfonso
 */
@WebServlet(name = "DatosEstudioGuardarServlet", urlPatterns = {"/DatosEstudioGuardarServlet"})
public class DatosEstudioGuardarServlet extends trabajoTAWServlet {

    @EJB
    DatosEstudioProductoFacade estudioProductoFacade;
    @EJB
    DatosEstudioUsuarioFacade estudioUsuarioFacade;
    @EJB
    EstudioFacade estudioFacade;

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
            String strIdEstudio, strIdEstudioProducto, strIdEstudioUsuario, string;
            Estudio estudio;
            DatosEstudioProducto estudioProducto;
            DatosEstudioUsuario estudioUsuario;
            String[] str;

            strIdEstudio = request.getParameter("idEstudio");
            estudio = this.estudioFacade.find(Integer.parseInt(strIdEstudio));
            strIdEstudioProducto = request.getParameter("idEstudioProducto");
            strIdEstudioUsuario = request.getParameter("idEstudioUsuario");

            if (estudio != null && estudio.getProducto() == Boolean.TRUE) {

                if (strIdEstudioProducto == null || strIdEstudioProducto.isEmpty()) {
                    estudioProducto = new DatosEstudioProducto();
                } else {
                    estudioProducto = this.estudioProductoFacade.find(Integer.parseInt(strIdEstudioProducto));
                }

                estudioProducto.setEstudio(estudio);
                estudioProducto.setId(estudio.getIdEstudio());

                str = request.getParameterValues("estudioProducto");

                Boolean categorias = Boolean.FALSE;
                Boolean vendidos = Boolean.FALSE;
                Boolean enPromocion = Boolean.FALSE;

                if (str != null) {
                    for (String s : str) {
                        if (s.equals("categorias")) {
                            categorias = Boolean.TRUE;
                        } else if (s.equals("vendidos")) {
                            vendidos = Boolean.TRUE;
                        } else if (s.equals("enPromocion")) {
                            enPromocion = Boolean.TRUE;
                        }
                    }
                }
                estudioProducto.setCategorias(categorias);
                estudioProducto.setVendidos(vendidos);
                estudioProducto.setPromocion(enPromocion);

                string = request.getParameter("precioSalida");
                if (string != null && !string.isEmpty()) {
                    Double d = Double.parseDouble(string);
                    estudioProducto.setPrecioSalida(d);
                }

                string = request.getParameter("precioActual");
                if (string != null && !string.isEmpty()) {
                    Double d = Double.parseDouble(string);
                    estudioProducto.setPrecioActual(d);
                }

                if (strIdEstudioProducto == null || strIdEstudioProducto.isEmpty()) {
                    this.estudioProductoFacade.create(estudioProducto);
                } else {
                    this.estudioProductoFacade.edit(estudioProducto);
                }
                estudio.setDatosEstudioProducto(estudioProducto);
                estudioFacade.edit(estudio);

            } else {

                if (strIdEstudioUsuario == null || strIdEstudioUsuario.isEmpty()) {
                    estudioUsuario = new DatosEstudioUsuario();
                } else {
                    estudioUsuario = this.estudioUsuarioFacade.find(Integer.parseInt(strIdEstudioUsuario));
                }
                estudioUsuario.setEstudio(estudio);
                estudioUsuario.setId(estudio.getIdEstudio());

                str = request.getParameterValues("estudioUsuario");

                Boolean nombre = Boolean.FALSE;
                Boolean apellidos = Boolean.FALSE;
                Boolean ingresos = Boolean.FALSE;
                Boolean ascendente = Boolean.FALSE;

                if (str != null) {
                    for (String s : str) {
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

                estudioUsuario.setNombre(nombre);
                estudioUsuario.setApellidos(apellidos);
                estudioUsuario.setIngresos(ingresos);
                estudioUsuario.setAscendente(ascendente);

                if (strIdEstudioUsuario == null || strIdEstudioUsuario.isEmpty()) {
                    this.estudioUsuarioFacade.create(estudioUsuario);
                } else {
                    this.estudioUsuarioFacade.edit(estudioUsuario);
                }

                estudio.setDatosEstudioUsuario(estudioUsuario);
                estudioFacade.edit(estudio);
            }
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

    private DatosEstudioProducto DatosEstudioProducto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private DatosEstudioUsuario DatosEstudioUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
