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
                <th>ID USUARIO</th>
                <th>NOMBRE USUARIO</th>                
                <th>DIRECCION</th>                                
                <th>EMAIL</th>     
                <th>FECHA NACIMIENTO</th>                     
                <th>SEXO</th>                                                                                                       
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
            </tr>
            <%
            }
            %>
        </table>
    </body>
</html>

