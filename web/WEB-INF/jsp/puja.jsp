<%-- 
    Document   : puja
    Created on : 13-may-2022, 10:43:08
    Author     : Victor
--%>

<%@page import="trabajoTAW.dto.PujaDTO"%>
<%@page import="java.util.List"%>
<%@page import="trabajoTAW.dto.ProductoDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Puja</title>
    </head>
    <body>
        <h1>Puja</h1>
        <%
          ProductoDTO p = (ProductoDTO) request.getAttribute("producto");
          List<PujaDTO> listaPujas = (List) request.getAttribute("listaPujas");
          Double precioActual = (Double) request.getAttribute("precioActual");
        %>
        Producto: <%= p.getNombre()%> <br>
        Precio actual: <%= listaPujas.isEmpty()? p.getPrecioSalida() : precioActual %>
        <form method="post" action="PujaNuevoServlet?id=<%=p.getIdProducto()%>">
        Cantidad: <input type="text" name="cantidad"><br>
        Enviar: <input type="submit">
        </form>
        
        <a href="BuscarProductosServlet">Volver</a>
        
    </body>
</html>
