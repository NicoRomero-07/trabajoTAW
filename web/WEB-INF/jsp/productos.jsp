<%-- 
    Document   : productos
    Created on : 15-may-2022, 10:32:13
    Author     : nicor
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="trabajoTAW.dto.ProductoDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="cabecera.jsp" /> 
        <a href="AdministradorServlet">Volver</a>
        <h1>Productos</h1>
        
        <form method="post" action="ProductosServlet">
            Buscar productos: <input type="text" name="filtroNombre" value="" />
        </form>
        <br>
        
        <table border="1">
            <tr>
                <th>ID_PRODUCTO</th>
                <th>NOMBRE</th>
                <th>DESCRIPCIÓN</th>
                <th>URL_FOTO</th>
                <th>CATEGORÍA</th>
                <th>FECHA_DE_INICIO</th>
                <th>FECHA_DE_FIN</th>
                <th>EN_PROMOCIÓN</th>
                <th>PUBLICADOR</th>
                <th>COMPRADOR</th>
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
                <td><%= prod.getUrlFoto()%></td>
                <td><%= prod.getCategoria()%></td>
                <td><%= fecha.format(prod.getFechaInicioSubasta())%></td>
                <td><%= fecha.format(prod.getFechaFinSubasta())%></td>
                <td><%= prod.getEnPromocion()? "Si":"No" %></td>
                <td><%= prod.getPublicador()==null ? "":prod.getPublicador().getNombreUsuario() %></td>
                <td><%= prod.getComprador()==null? "":prod.getComprador().getNombreUsuario() %></td>
                <td><a href="ProductoBorrarServlet?id=<%= prod.getIdProducto() %>">Borrar</a></td> 
                <td><a href="ProductoNuevoEditarServlet?id=<%= prod.getIdProducto() %>">Editar</a></td>  
            </tr>
                <%
                  }  
                %>
            
        </table>
                
        <a href="ProductoNuevoEditarServlet"> Crear nuevo producto... </a>            
    </body>
</html>
