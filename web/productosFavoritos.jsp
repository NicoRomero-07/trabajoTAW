<%-- 
    Document   : productosFavoritos
    Created on : 12-may-2022, 13:49:23
    Author     : Victor
--%>

<%@page import="trabajoTAW.dto.ProductoDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ProductosFavoritos</title>
    </head>
    <body>
        <h1>Lista de favoritos</h1>
        <table border="1">
            <tr>
                <th>ID_PRODUCTO</th>
                <th>NOMBRE</th>
                <th>DESCRIPCIÓN</th>
                <th>PRECIO_SALIDA</th>
                <th>URL_FOTO</th>
                <th>CATEGORÍA</th>
                <th>EN_PROMOCIÓN</th>
                <th></th>
            </tr>
            
                <%
                List<ProductoDTO> productos = (List)request.getAttribute("productos");
                for (ProductoDTO prod: productos) {
                %>
            <tr>
                <td><%= prod.getIdProducto()%></td>
                <td><%= prod.getNombre()%></td>
                <td><%= prod.getDescripcion()%></td>
                <td><%= prod.getPrecioSalida()%></td>
                <td><%= prod.getUrlFoto()%></td>
                <td><%= prod.getCategoria()%></td>
                <%
                if(prod.getEnPromocion()) {
                %>
                <td>Si</td>
                <%
                } else {
                %>
                <td>No</td>      
                <%
                }
}
                %>
    </body>
</html>
