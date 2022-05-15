<%-- 
    Document   : cabeceraVendedor
    Created on : May 15, 2022, 12:38:22 PM
    Author     : Pablo
--%>

<%--Pablo (100%)--%>

<%@page import="trabajoTAW.dto.UsuarioDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
    if (user == null) {
        response.sendRedirect(request.getContextPath());
    }
%>

<table width="80%">
    <tr width="80%">
        <td>Bienvenido, <%= user.getEmail() %></td>
        <td>Session ID: <%= session.getId() %></td>
        <td><a href="ListaVendedorServlet">Tus Productos</a></td>        
        <td><a href="LogoutServlet">Salir</a></td>        
    </tr>
</table>
