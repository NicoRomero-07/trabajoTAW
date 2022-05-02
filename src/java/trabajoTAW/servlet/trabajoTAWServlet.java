/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajoTAW.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trabajoTAW.entity.Usuario;

/**
 *
 * @author nicor
 */
public abstract class trabajoTAWServlet extends HttpServlet {

    @Override
    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
    


    @Override
    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected boolean comprobarSession (HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario admin = (Usuario)session.getAttribute("usuario");
        if (admin == null) {
            response.sendRedirect(request.getContextPath());
            return false;
        } else {
            return true;
        }        
        
    }
    
    protected String comprobarTipoUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        String tipoUsuario = "";
        if(comprobarSession(request,response)){
            Usuario usuario = (Usuario)session.getAttribute("usuario");
            tipoUsuario = usuario.getTipoUsuario().getTipo();
        }
        return tipoUsuario;    
    }
}

   

