<%-- 
    Document   : productosFavoritos
    Created on : 12-may-2022, 13:49:23
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
        <title>ProductosFavoritos</title>
    </head>
    <body>
        <h1>Lista de favoritos</h1>
        
        <form method="post" action="BuscarProductosFavoritosServlet">
            Buscar productos: <input type="text" name="buscador" value="" />
        </form>
        <br>
        
        <table border="1">
            <tr>
                <th>ID_PRODUCTO</th>
                <th>NOMBRE</th>
                <th>DESCRIPCIÓN</th>
                <th>PRECIO_ACTUAL</th>
                <th>URL_FOTO</th>
                <th>CATEGORÍA</th>
                <th>FECHA_INICIO_SUBASTA</th>
                <th>FECHA_FIN_SUBASTA</th>
                <th>EN_PROMOCIÓN</th>
                <th></th>
                <th></th>
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
                <td><%= prod.getPrecioSalida()%></td>
                <td><%= prod.getUrlFoto()%></td>
                <td><%= prod.getCategoria()%></td>
                <td><%= fecha.format(prod.getFechaInicioSubasta())%></td>
                <td><%= fecha.format(prod.getFechaFinSubasta())%></td>
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

                %>
                <td><a href="ProductoFavoritoBorrarServlet?id=<%=prod.getIdProducto() %>"><input type="submit" value="Quitar de favoritos"></a></td>
                <td><a href="PujaServlet?id=<%=prod.getIdProducto()%>"><input type="submit" value="Pujar"></a></td>
                <%
                  }  
                %>
            </tr>
        </table>
            <a href="CompradorPrincipalServlet">Volver</a>

    </body>
</html>
