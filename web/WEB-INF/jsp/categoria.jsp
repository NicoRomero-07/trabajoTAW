<%-- 
    Document   : categoria
    Created on : 30-abr-2022, 14:13:46
    Author     : nicor
--%>

<%@page import="trabajoTAW.dto.CategoriaDTO"%>
<%@page import="trabajoTAW.entity.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% 
        CategoriaDTO categoria = (CategoriaDTO)request.getAttribute("categoria");
    %> 
    <body>
        <jsp:include page="cabecera.jsp" /> 
        <a href="CategoriasServlet">Volver</a>
        <h1>Datos del usuario</h1>
        <form method="POST" action="CategoriaGuardarServlet">
            <input type="hidden" name="id" value="<%= categoria==null? "": categoria.getIdCategoria() %>" />
            Nombre: <input type="text" size="30" name="nombre" value="<%= categoria==null? "": categoria.getNombre() %>" /> <br/>
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
