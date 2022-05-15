/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import trabajoTAW.dto.NotificacionDTO;
import trabajoTAW.dto.UsuarioDTO;
import trabajoTAW.service.ListaUsuarioService;
/*
import trabajoTAW.dao.NotificacionFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.entity.Notificacion;
import trabajoTAW.entity.Usuario;
*/
import trabajoTAW.service.NotificacionService;
import trabajoTAW.service.UsuarioService;

/**
 *
 * @author nicol
 */
@WebServlet(name = "MensajeBorrarServlet", urlPatterns = {"/MensajeBorrarServlet"})
public class MensajeBorrarServlet extends trabajoTAWServlet {
    /*
    @EJB NotificacionFacade notificacionFacade;
    @EJB UsuarioFacade compradorFacade;
    */
    @EJB NotificacionService notificacionService;
    @EJB UsuarioService usuarioService;
    @EJB ListaUsuarioService listaUsuarioService;
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
        
            String str = request.getParameter("id");
            String strComprador = request.getParameter("idComprador");
            String strLista = request.getParameter("idlista");
            UsuarioDTO comprador = this.usuarioService.buscarUsuario(Integer.parseInt(strComprador));
            NotificacionDTO notificacion = this.notificacionService.buscarNotificacion(Integer.parseInt(str));
            List<NotificacionDTO> notificaciones = this.usuarioService.notificacionesUsuario(Integer.parseInt(strComprador));
            notificaciones.remove(notificacion);
            this.usuarioService.modificarNotificacionesUsuario(Integer.parseInt(strComprador), notificacionesDTOtoIdList(notificaciones));
            List<UsuarioDTO> usuarios =  this.notificacionService.receptores(Integer.parseInt(str));
            usuarios.remove(comprador);
            if (usuarios.isEmpty()){
                this.notificacionService.borrarNotificacion(Integer.parseInt(str)); 
            }else{
                this.notificacionService.modificarNotificacion(Integer.parseInt(str),usuariosDTOtoIdList(usuarios));
            }

            response.sendRedirect(request.getContextPath()+"/CompradorVerMensajeServlet?id="+strComprador+"&idlista="+strLista);
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
