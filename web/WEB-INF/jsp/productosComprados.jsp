<%-- 
    Document   : productosComprados
    Created on : 13-may-2022, 19:06:10
    Author     : Victor
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="trabajoTAW.dto.ProductoDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos comprados</title>
    </head>
    <body>
        <h1>Productos comprados</h1>
        
        <form method="post" action="BuscarProductosCompradosServlet">
            Buscar productos: <input type="text" name="buscador" value="" />
        </form>
        <br>
        
        <table border="1">
            <tr>
                <th>ID_PRODUCTO</th>
                <th>NOMBRE</th>
                <th>DESCRIPCIÓN</th>
                <th>URL_FOTO</th>
                <th>CATEGORÍA</th>
                <th>FECHA_DE_COMPRA</th>
            </tr>
            
                <%
                List<ProductoDTO> productos = (List)request.getAttribute("productos");
                SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yy");

                for (ProductoDTO prod: productos) {
                %>
            <tr>
                <td><%= prod.getIdProducto()%></td>
                <td><%= prod.getNombre()%></td>
                <td><%= prod.getDescripcion()%></td>
                <td><%= prod.getUrlFoto()%></td>
                <td><%= prod.getCategoria()%></td>
                <td><%= fecha.format(prod.getFechaFinSubasta())%></td>
                <%
                  }  
                %>
            </tr>
        </table>
            <a href="CompradorPrincipalServlet">Volver</a>

    </body>
</html>
