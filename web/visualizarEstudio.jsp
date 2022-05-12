<%-- 
    Document   : visualizarEstudio
    Created on : 28-abr-2022, 20:04:41
    Author     : Alfonso
--%>


<%@page import="trabajoTAW.entity.Producto"%>
<%@page import="java.util.List"%>
<%@page import="trabajoTAW.entity.Usuario"%>
<%@page import="trabajoTAW.entity.Estudio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizar Estudio</title>
    </head>
    <%
        Estudio estudio = (Estudio) request.getAttribute("estudio");
        List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
        List<Producto> listaProductos = (List<Producto>) request.getAttribute("listaProductos");
    %>
    <body>
        <h1><%=estudio.getNombre()%></h1>
        <br>
        <% if (listaUsuarios != null) {%>
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
            </tr>
            <%
                for (Usuario user : listaUsuarios) {
            %> 

            <tr>
                <td><%= user.getIdUsuario()%></td>
                <td><%= user.getNombreUsuario()%></td>
                <td><%= user.getContrasenya()%></td>
                <td><%= user.getEmail()%></td>
                <td><%= user.getNombre()%></td>
                <td><%= user.getPrimerApellido()%></td>
                <td><%= user.getSegundoApellido()%></td>
                <td><%= user.getFechaNacimiento().toString()%></td>
                <td><%= user.getSexo().charValue()%></td>
                <td><%= user.getTipoUsuario().getTipo()%></td>

            </tr>
            <%
                }
            %>
        </table>
        <%} else if (listaProductos != null) {%>
        <table border="1">
            <tr>
                <th>ID_PRODUCTO</th>
                <th>NOMBRE</th>
                <th>DESCRIPCIÓN</th>
                <th>PRECIO_SALIDA</th>
                <th>URL_FOTO</th>
                <th>CATEGORÍA</th>
                <th>EN_PROMOCIÓN</th>
            </tr>
            <%
                for (Producto prod : listaProductos) {
            %>
             <tr>
                <td><%= prod.getIdProducto()%></td>
                <td><%= prod.getNombre()%></td>
                <td><%= prod.getDescripcion()%></td>
                <td><%= prod.getPrecioSalida()%></td>
                <td><%= prod.getUrlFoto()%></td>
                <td><%= prod.getCategoria()%></td>
                <td><%= prod.getEnPromocion()%></td>
            </tr>
            <%}%>
        </table>
        <%}%>
    </body>
</html>
