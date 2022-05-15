<%-- 
    Document   : usuarios
    Created on : 20-abr-2022, 15:18:27
    Author     : nicor
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="trabajoTAW.dto.UsuarioDTO"%>
<%@page import="trabajoTAW.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
    </head>
    <%
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    %>
    <body>
        <jsp:include page="cabecera.jsp" /> 
        
        <a href="AdministradorServlet">Volver</a>
        <h1>Usuarios</h1>
    <form method="post" action="UsuariosServlet">
            Nombre de Usuario: <input type="text" name="filtroNombre" value="" />
            <input type="submit" value="Filtrar" />
    </form>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>NOMBRE_USUARIO</th>
            <th>CONTRASEÑA</th>
            <th>EMAIL</th>
            <th>NOMBRE</th>
            <th>PRIMER_APELLIDO</th>
            <th>SEGUNDO_APELLIDO</th>
            <th>FECHA_NACIMIENTO</th>
            <th>SEXO</th>
            <th>TIPO_USUARIO</th>
            <th></th>                                                     
            <th></th>
        </tr>
    <%
            List<UsuarioDTO> usuarios = (List)request.getAttribute("usuarios");
                for (UsuarioDTO user: usuarios) {
    %> 
    
    <tr>
        <td><%= user.getIdUsuario() %></td>
        <td><%= user.getNombreUsuario() %></td>
        <td><%= user.getContrasenya() %></td>
        <td><%= user.getEmail() %></td>
        <td><%= user.getNombre() %></td>
        <td><%= user.getPrimerApellido() %></td>
        <td><%= user.getSegundoApellido() %></td>
        <td><%= format.format(user.getFechaNacimiento()) %></td>
        <td><%= user.getSexo().charValue() %></td>
        <td><%= user.getTipoUsuario().getTipo() %></td>
        <td><a href="UsuarioBorrarServlet?id=<%= user.getIdUsuario() %>">Borrar</a></td> 
        <td><a href="UsuarioNuevoEditarServlet?id=<%= user.getIdUsuario() %>">Editar</a></td>  
        
    </tr>
    <%
                }
    %>
    </table>
    <a href="UsuarioNuevoEditarServlet">Crear nuevo cliente ... </a>
    </body>
</html>
