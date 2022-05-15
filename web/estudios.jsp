<%-- 
    Document   : Estudios
    Created on : 17-abr-2022, 16:41:54
    Author     : Alfonso 100%
--%>

<%@page import="trabajoTAW.dto.EstudioDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estudios</title>
    </head>
    <%
        String filtroNombre = (String) request.getAttribute("filtroNombre");
        List<EstudioDTO> estudios = (List) request.getAttribute("estudios");
    %>
    <body>
        <jsp:include page="cabecera2.jsp" /> 
        <% 
            if(estudios != null && !estudios.isEmpty()){
                %>
                <h1>Estudios</h1>
                <form method="post" action="EstudiosServlet">
                    Nombre: <input type="text" name="filtroNombre" value="<%=filtroNombre == null ? "" : filtroNombre%>" />
                    <input type="submit" value="Filtrar" />
                </form>
                <br>
                <table border="1">
                <tr>
                    <th>ID</th>
                    <th>NOMBRE</th>
                    <th>ANALISTA</th>
                    <td>DESCRIPCION</td>   
                </tr>
            <%
                for (EstudioDTO est : estudios) {
            %> 
                    <tr>
                        <td><%= est.getIdEstudio()%></td>
                        <td><%= est.getNombre()%> </td>            
                        <td><%= est.getAnalista().getNombre()%></td>
                        <td><%= est.getDescripcion()%></td>   
                        <td><a href="EstudiosBorrarServlet?id=<%= est.getIdEstudio() %>">Borrar</a></td> 
                        <td><a href="EstudioNuevoEditarServlet?id=<%= est.getIdEstudio()%>">Editar</a></td>
                        <td><a href="EstudioCopiarServlet?id=<%= est.getIdEstudio()%>">Copiar</a></td>
                        <td><a href="EstudioVisualizarServlet?id=<%= est.getIdEstudio()%>">Visualizar</a></td>
                    </tr>
            <%
                }
            %>
                </table><br>
            <%
            }else{
                %>
                No hay estudios disponibles desea 
                <%
            }
            %>
            <a href="EstudioNuevoEditarServlet">Crear nuevo estudio</a>
    </body>
</html>
