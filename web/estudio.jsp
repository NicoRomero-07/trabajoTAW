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
            Analista: 
            <select name="analista">
                <%
                    for (Usuario user : listaUsuarios) {
                        String selected = "";
                        if (user != null) {
                            selected = "selected";
                        }
                %>
                <option <%= selected%> value="<%= user.getIdUsuario() %>"><%= user.getNombre() %></option>

                <%
                    }
                %>          
            </select><br>
            Vendedor:
            <select name="vendedor">
                <%
                    for (Usuario user : listaUsuarios) {
                        String selected = "";
                        if (user != null) {
                            selected = "selected";
                        }
                %>
                <option <%= selected%> value="<%= user.getIdUsuario() %>"><%= user.getNombre() %></option>

                <%
                    }
                %>          
            </select><br/>
            Ingreso: <input type="text" size="20" name="ingreso" value="<%= estudio == null ? "" : estudio.getIngreso()%>" /> <br/>
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
