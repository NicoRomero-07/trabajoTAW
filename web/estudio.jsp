<%-- 
    Document   : estudio
    Created on : 17-abr-2022, 20:57:42
    Author     : Alfonso 100%
--%>

<%@page import="trabajoTAW.dto.EstudioDTO"%>
<%@page import="trabajoTAW.dto.UsuarioDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Estudio </title>
    </head>
    <%
        List<UsuarioDTO> listaUsuarios = (List)request.getAttribute("usuarios");
        EstudioDTO estudio = (EstudioDTO) request.getAttribute("estudio");
    %> 
    <body>
        <h1>Datos del estudio</h1>
        <form method="POST" action="EstudioGuardarServlet">
            <input type="hidden" name="id" value="<%= estudio == null ? "" : estudio.getIdEstudio()%>" />
            
            Nombre: 
            <input type="text" size="20" name="nombre" value="<%= estudio == null ? "" : estudio.getNombre() %>" required /> <br><br>
            
            Analista: 
            <select name="analista">
                <%
                    if(listaUsuarios != null){
                        for (UsuarioDTO user : listaUsuarios) {
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
            </select><br><br>
            
            Descripcion:
            <br><textarea name="descripcion" cols="100" rows="5" maxlength="100"><%= estudio == null || estudio.getDescripcion() == null ? "" : estudio.getDescripcion() %></textarea><br><br>
            
            Elementos a estudiar:<br>
            <input type="radio" name="element" value="comprador" <% 
            String check = "";
            if(estudio == null){
                check = "checked";
            }else if(estudio.getComprador() == Boolean.TRUE){
                check = "checked";
            }
            %>        
            <%= check %>/>Comprador<br>
            <input type="radio" name="element" value="vendedor"  <%= estudio == null || estudio.getVendedor() == Boolean.FALSE ? "" : "checked" %>/>Vendedor<br>
            <input type="radio" name="element" value="producto" <%= estudio == null || estudio.getProducto() == Boolean.FALSE ? "" : "checked" %> />Producto<br><br>
            
            <button type="submit" value="Enviar"><a href="DatosEstudioNuevoEditarServlet?id=<%= estudio == null || estudio.getIdEstudio() == null ? "" : estudio.getIdEstudio() %>">Editar</a></button>
        </form>
    </body>
</html>
