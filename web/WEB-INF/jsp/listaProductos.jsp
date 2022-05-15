<%-- 
    Document   : listaProductos
    Created on : Apr 25, 2022, 10:24:10 AM
    Author     : Pablo
--%>

<%--Pablo (100%)--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="trabajoTAW.dto.ProductoDTO"%>
<%@page import="trabajoTAW.entity.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Productos</title>
    </head>
    <body>
        <jsp:include page="cabeceraVendedor.jsp" />
        <table border="1">
            <tr>
                <th>ID_PRODUCTO</th>
                <th>NOMBRE</th>
                <th>DESCRIPCIÓN</th>
                <th>PRECIO_SALIDA</th>
                <th>URL_FOTO</th>
                <th>FECHA_INICIO</th>
                <th>FECHA_FIN</th>
                <th>COMPRADOR</th>
                <th>CATEGORÍA</th>
                <th>EN_PROMOCIÓN</th>
            </tr>
            
                <%
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm");
                List<ProductoDTO> productos = (List) request.getAttribute("productos");
                for (ProductoDTO prod: productos) {
                %>
            <tr>
                <td><%= prod.getIdProducto()%></td>
                <td><%= prod.getNombre()%></td>
                <td><%= prod.getDescripcion()%></td>
                <td><%= prod.getPrecioSalida()%></td>
                <td><%= prod.getUrlFoto()%></td>
                <td><%= sdf.format(prod.getFechaInicioSubasta())%></td>
                <td><%= sdf.format(prod.getFechaFinSubasta())%></td>
                <td><%= prod.getComprador()==null? "No Comprado":prod.getComprador().getNombreUsuario()%></td>
                <td><%= prod.getCategoria()%></td>
                <td><%=prod.getEnPromocion()? "Si": "No"%></td>
                <td><a href="ProductoNuevoEditarServlet?id=<%=prod.getIdProducto()%>"><input type="submit" value="Editar"></a></td>
                <td><a href="ProductoBorrarServlet?id=<%=prod.getIdProducto()%>"><input type="submit" value="Borrar"></a></td>
                <%
                    }
                %>
            </tr>
        </table>
            <br><a href="ProductoNuevoEditarServlet"><input type="submit" value="Nuevo Producto" /></a>
    </body>
</html>
