<%-- 
    Document   : categorias
    Created on : 30-abr-2022, 14:03:29
    Author     : nicor
--%>

<%@page import="trabajoTAW.dto.CategoriaDTO"%>
<%@page import="trabajoTAW.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categorias</title>
    </head>
    <body>
        <jsp:include page="cabecera.jsp" /> 
        <a href="AdministradorServlet">Volver</a>
        <h1>Categorias</h1>
    <form method="post" action="CategoriasServlet">
            Nombre de Categoria: <input type="text" name="filtroNombre" value="" />
            <input type="submit" value="Filtrar" />
    </form>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>NOMBRE</th>
            <th></th>                                                     
            <th></th>
        </tr>
    <%
            List<CategoriaDTO> categorias = (List)request.getAttribute("categorias");
                for (CategoriaDTO categoria: categorias) {
    %> 
    
    <tr>
        <td><%= categoria.getIdCategoria() %></td>
        <td><%= categoria.getNombre() %></td>
        
        <td><a href="CategoriaBorrarServlet?id=<%= categoria.getIdCategoria() %>">Borrar</a></td> 
        <td><a href="CategoriaNuevoEditarServlet?id=<%= categoria.getIdCategoria() %>">Editar</a></td>  
        
    </tr>
    <%
                }
    %>
    </table>
    <a href="CategoriaNuevoEditarServlet">Crear nueva categoria ... </a>
    </body>
</html>
