<%-- 
    Document   : usuarios
    Created on : 20-abr-2022, 15:18:27
    Author     : Nicolás Zhao (100%)
--%>
<%@page import="trabajoTAW.dto.ListaUsuarioDTO"%>
<%@page import="trabajoTAW.dto.UsuarioDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%--
<%@page import="trabajoTAW.entity.ListaUsuario"%>
<%@page import="trabajoTAW.entity.Usuario"%>
--%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compradores</title>
    </head>
    <body>
        <jsp:include page="cabeceraMarketing.jsp" /> 
        <h1>Compradores</h1>
    <table border="1">
        <tr>
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
        </tr>
    <%
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");    
        List<UsuarioDTO> compradores = (List)request.getAttribute("compradores");
        ListaUsuarioDTO lista = (ListaUsuarioDTO) request.getAttribute("lista");
                for (UsuarioDTO user: compradores) {
    %> 
    
    <tr>
        <td><%= user.getNombreUsuario() %></td>
        <td><%= user.getContrasenya() %></td>
        <td><%= user.getEmail() %></td>
        <td><%= user.getNombre() %></td>
        <td><%= user.getPrimerApellido() %></td>
        <td><%= user.getSegundoApellido() %></td>
        <td><%= formatter.format(user.getFechaNacimiento()) %></td>
        <td><%= user.getSexo().charValue() %></td>
        <td><%= user.getTipoUsuario().getTipo() %></td>
        <td><a href="CompradorVerMensajeServlet?id=<%= user.getIdUsuario() %>&idlista=<%=lista.getIdListaUsuario() %>">Ver bandeja de mesajes</a></td> 
        
    </tr>
    <%
                }
    %>
    </table>
    <br/>
    <a href="ListaCompradorServlet"><input type="button" value="Volver"/></a>
    </body>
</html>
