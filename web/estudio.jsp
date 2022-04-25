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
            <input type="text" size="20" name="nombre" value="<%= estudio == null ? "" : estudio.getNombre() %>" required /> <br/><br/>
            
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
                <option <%= selected%> value="<%= user.getIdUsuario() %>" required><%= user.getNombre() %></option>

                <%
                        }
                    }
                %>          
            </select><br/><br/>
            
            Descripcion:
            <br/><textarea name="descripcion" cols="100" rows="5" maxlength="100"><%= estudio.getDescripcion() == null ? "" : estudio.getDescripcion() %></textarea><br/><br/>
            
            Elementos a estudiar:<br/>
            <input type="radio" name="element" value="comprador" <%= estudio == null || estudio.getComprador()== Boolean.FALSE ? "" : "checked" %>/>Comprador<br/>
            <input type="radio" name="element" value="vendedor"  <%= estudio == null || estudio.getVendedor() == Boolean.FALSE ? "" : "checked" %>/>Vendedor<br/>
            <input type="radio" name="element" value="producto" <%= estudio == null || estudio.getProducto() == Boolean.FALSE ? "" : "checked" %> />Producto<br/><br/>
            
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
