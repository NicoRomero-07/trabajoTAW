<%-- 
    Document   : estudio
    Created on : 17-abr-2022, 20:57:42
    Author     : Alfonso
--%>

<%@page import="java.util.List"%>
<%@page import="trabajoTAW.entity.Usuario"%>
<%@page import="trabajoTAW.entity.Estudio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Estudio </title>
    </head>
    <%
        List<Usuario> listaUsuarios = (List)request.getAttribute("usuarios");
        Estudio estudio = (Estudio) request.getAttribute("estudio");
    %> 
    <body>
        <h1>Datos del estudio</h1>
        <form method="POST" action="EstudioGuardarServlet">
            <input type="hidden" name="id" value="<%= estudio == null ? "" : estudio.getIdEstudio()%>" />
            
            Nombre: 
            <input type="text" size="20" name="nombre" value="nombre" required /> <br/><br/>
            
            Analista: 
            <select name="analista">
                <%
                    for (Usuario user : listaUsuarios) {
                        // Solo se mostraran los administradores y los analistas para asignarles un estudio
                        if(user.getTipoUsuario().getTipo().equalsIgnoreCase("administrador") || user.getTipoUsuario().getTipo().equalsIgnoreCase("analista")){
                        String selected = "";
                        
                            if (estudio != null && estudio.getAnalista().getIdUsuario().equals(user.getIdUsuario())) {
                                selected = "selected";
                            }
                %>
                <option <%= selected%> value="<%= user.getIdUsuario() %>"><%= user.getNombre() %></option>

                <%
                        }
                    }
                %>          
            </select><br/><br/>
            
            Descripcion:
            <br/><textarea cols="100" rows="5"></textarea><br/><br/>
            
            Elementos a estudiar:<br/>
            <input type="radio" name="element" value="comprador"/>Comprador<br/>
            <input type="radio" name="element" value="vendedor"/>Vendedor<br/>
            <input type="radio" name="element" value="producto"/>Producto<br/><br/>
            
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
