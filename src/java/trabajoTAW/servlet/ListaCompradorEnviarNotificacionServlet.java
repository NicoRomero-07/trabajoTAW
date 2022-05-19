/**
 *
 * @author Nicolás Zhao(100%)
 */
package trabajoTAW.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trabajoTAW.dto.ListaUsuarioDTO;
import trabajoTAW.dto.NotificacionDTO;
import trabajoTAW.dto.ProductoDTO;
import trabajoTAW.dto.UsuarioDTO;
/*
import trabajoTAW.dao.ListaUsuarioFacade;
import trabajoTAW.dao.NotificacionFacade;
import trabajoTAW.dao.ProductoFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.entity.ListaUsuario;
import trabajoTAW.entity.Notificacion;
import trabajoTAW.entity.Producto;
import trabajoTAW.entity.Usuario;
*/

import trabajoTAW.service.ListaUsuarioService;
import trabajoTAW.service.NotificacionService;
import trabajoTAW.service.ProductoService;
import trabajoTAW.service.UsuarioService;

@WebServlet(name = "ListaCompradorEnviarNotificacionServlet", urlPatterns = {"/ListaCompradorEnviarNotificacionServlet"})
public class ListaCompradorEnviarNotificacionServlet extends trabajoTAWServlet {
    
    /*
    @EJB ListaUsuarioFacade listaCompradorFacade;
    @EJB UsuarioFacade usuarioFacade;
    @EJB ProductoFacade productoFacade;
    @EJB NotificacionFacade notificacionFacade;
    */
    @EJB ListaUsuarioService listaCompradorService;
    @EJB UsuarioService usuarioService;
    @EJB ProductoService productoService;
    @EJB NotificacionService notificacionService;
    
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
            String strId;
            strId = request.getParameter("id");
            List<ProductoDTO> promociones = productoService.getProductosEnPromocion();
            StringBuilder contenido = new StringBuilder();
            for (ProductoDTO promocion: promociones){
                contenido.append("Nombre: ").append(promocion.getNombre()).append("<br/>");
                contenido.append("Publicador: ").append(promocion.getPublicador().getNombreUsuario()).append("<br/>");
                contenido.append("Descripcion: ").append(promocion.getDescripcion()).append("<br/>");
                contenido.append("Precio de salida: ").append(promocion.getPrecioSalida()).append("<br/><br/>");
            }
            Date now = new Date();
            HttpSession session = request.getSession();
            UsuarioDTO notificante = (UsuarioDTO)session.getAttribute("usuario");
            NotificacionDTO notificacionCreada = notificacionService.crearNotificacion(contenido.toString(), now, notificante.getIdUsuario());

            List<UsuarioDTO> compradores = new ArrayList();
            for(UsuarioDTO comprador: this.listaCompradorService.usuariosRelacionados(Integer.parseInt(strId))){
                List<NotificacionDTO> notificaciones = this.usuarioService.notificacionesUsuario(comprador.getIdUsuario())== null?new ArrayList(): this.usuarioService.notificacionesUsuario(comprador.getIdUsuario());
                notificaciones.add(notificacionCreada);
                this.usuarioService.modificarNotificacionesUsuario(comprador.getIdUsuario(), notificacionesDTOtoIdList(notificaciones));
                compradores.add(comprador);
            }
            this.notificacionService.modificarNotificacion(notificacionCreada.getIdNotificacion(), usuariosDTOtoIdList(compradores));
            response.sendRedirect(request.getContextPath()+"/ListaCompradorServlet");
        }
    }
        
     private List<Integer> usuariosDTOtoIdList (List<UsuarioDTO> usuariosDTO){
        List<Integer> listaId = new ArrayList();
        for (UsuarioDTO udto:usuariosDTO){
            listaId.add(udto.getIdUsuario());
        }
        return listaId;
    }
     
    private List<Integer> notificacionesDTOtoIdList (List<NotificacionDTO> notificacionesDTO){
        List<Integer> listaId = new ArrayList();
        for (NotificacionDTO ndto:notificacionesDTO){
            listaId.add(ndto.getIdNotificacion());
        }
        return listaId;
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
