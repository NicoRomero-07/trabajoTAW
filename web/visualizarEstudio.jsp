<%-- 
    Document   : visualizarEstudio
    Created on : 28-abr-2022, 20:04:41
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
        <title>Visualizar Estudio</title>
    </head>
    <%
        Estudio estudio = (Estudio) request.getAttribute("estudio");
        List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
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
        <%}%>
    </body>
</html>
