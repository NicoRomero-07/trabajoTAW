/**
 *
 * @author Nicolás Zhao(100%)
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
import trabajoTAW.dto.ListaUsuarioDTO;
import trabajoTAW.dto.UsuarioDTO;
/*
import trabajoTAW.dao.ListaUsuarioFacade;
import trabajoTAW.dao.UsuarioFacade;
import trabajoTAW.entity.ListaUsuario;
import trabajoTAW.entity.Usuario;
*/
import trabajoTAW.service.ListaUsuarioService;
import trabajoTAW.service.UsuarioService;

@WebServlet(name = "ListaCompradorGuardarServlet", urlPatterns = {"/ListaCompradorGuardarServlet"})
public class ListaCompradorGuardarServlet extends trabajoTAWServlet {
    /*
    @EJB ListaUsuarioFacade listaUsuarioFacade;
    @EJB UsuarioFacade usuarioFacade;
    */
    @EJB ListaUsuarioService listaUsuarioService;
    @EJB UsuarioService usuarioService;

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
            String strId, strNombre;
            String[] compradores;
            ListaUsuarioDTO listaComprador = null;

            strId = request.getParameter("id");
            strNombre = request.getParameter("nombre");
            compradores = request.getParameterValues("compradores");
            
            if(strId != null && !strId.isEmpty()) {    
                listaComprador = this.listaUsuarioService.buscarLista(Integer.parseInt(strId));
            }
            
            if (compradores == null){ // si no se elige ningun comprador salta un error
                boolean error = true;
                request.setAttribute("error", error);
                request.getRequestDispatcher("/WEB-INF/jsp/listaComprador.jsp").forward(request, response);
            }else{
                //guardamos en la lista todos los compradores seleccionados
                List<UsuarioDTO> compradoresRelacionados = new ArrayList();
                for (String idComprador: compradores){
                    UsuarioDTO comprador = this.usuarioService.buscarUsuario(Integer.parseInt(idComprador));          
                    compradoresRelacionados.add(comprador);
                }
                

                //eliminamos referencias de los compradores que pertenecían a la lista
                if (strId != null && !strId.isEmpty()){
                    for (UsuarioDTO comprador: listaUsuarioService.usuariosRelacionados(Integer.parseInt(strId))){
                        if (!compradoresRelacionados.contains(comprador)){
                            List<ListaUsuarioDTO> listasRelacionadas = usuarioService.listasUsuario(comprador.getIdUsuario());
                            listasRelacionadas.remove(this.listaUsuarioService.buscarLista(Integer.parseInt(strId)));
                            List<Integer> listaId = listaUsuarioDTOtoIdList(listasRelacionadas);
                            this.usuarioService.modificarUsuario(comprador.getIdUsuario(), listaId);
                        }
                    }
                }
                //añadimos la referencia a la lista de compradores
                if (strId == null || strId.isEmpty()) {// Crear nueva lista comprador
                    listaComprador = this.listaUsuarioService.crearLista(strNombre,compradores);
                } else {                               // Editar lista comprador
                    this.listaUsuarioService.modificarLista(Integer.parseInt(strId),strNombre,compradores);
                }
                

                //añadimos las referencias a los compradores
                for (String idComprador: compradores){                   
                    UsuarioDTO comprador = this.usuarioService.buscarUsuario(Integer.parseInt(idComprador)); 
                    List<ListaUsuarioDTO> listas = usuarioService.listasUsuario(comprador.getIdUsuario())==null?new ArrayList():usuarioService.listasUsuario(comprador.getIdUsuario());
                    if (!listas.contains(listaComprador)){
                        listas.add(listaComprador);
                        List<Integer> listaId = listaUsuarioDTOtoIdList(listas);
                        this.usuarioService.modificarUsuario(comprador.getIdUsuario(),listaId);
                    }  
                }
                response.sendRedirect(request.getContextPath() + "/ListaCompradorServlet?w");
            }
        }
    }
    
    private List<Integer> listaUsuarioDTOtoIdList (List<ListaUsuarioDTO> listasRelacionadas){
        List<Integer> listaId = new ArrayList();
        for (ListaUsuarioDTO ludto:listasRelacionadas){
            listaId.add(ludto.getIdListaUsuario());
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
