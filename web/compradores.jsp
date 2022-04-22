<%-- 
    Document   : compradores
    Created on : 17-abr-2022, 14:39:27
    Author     : nicol
--%>

<%@page import="java.util.List"%>
<%@page import="trabajoTAW.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de compradores</title>
    </head>
    <body>
        <h1>Listado de compradores</h1>
        <table border="1">
            <tr>
                <th>ID_USUARIO</th>
                <th>NOMBRE_USUARIO</th>                
                <th>DIRECCION</th>                                
                <th>EMAIL</th>     
                <th>FECHA_NACIMIENTO</th>                     
                <th>SEXO</th>    
                <th>TIPO_USUARIO</th>
                <th></th>
            </tr>
            <%
            List<Usuario> compradores = (List)request.getAttribute("compradores");
            for (Usuario comprador: compradores) {
            %>
            <tr>
                <td><%= comprador.getIdUsuario()%></td>
                <td><%= comprador.getNombreUsuario()%></td>            
                <td><%= comprador.getDireccion().getCalle()%></td>                     
                <td><%= comprador.getEmail() %></td>                                 
                <td><%= comprador.getFechaNacimiento() %></td>                      
                <td><%= comprador.getSexo()%></td>  
                <td><%= comprador.getTipoUsuario().getTipo() %></td>
                <td><a href="CompradorBorrarServlet?id=<%= comprador.getIdUsuario()%>">Borrar de la lista</a></td> 
            </tr>
        </table>
    </body>
</html>

