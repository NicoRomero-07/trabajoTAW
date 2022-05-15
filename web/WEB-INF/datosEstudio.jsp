<%-- 
    Document   : datosEstudio
    Created on : 20-abr-2022, 20:09:51
    Author     : Alfonso 100 %
--%>

<%@page import="trabajoTAW.dto.DatosEstudioUsuarioDTO"%>
<%@page import="trabajoTAW.dto.DatosEstudioProductoDTO"%>
<%@page import="trabajoTAW.dto.EstudioDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos de Estudio</title>
    </head>
    <%
        EstudioDTO estudio = (EstudioDTO) request.getAttribute("estudio");
        DatosEstudioProductoDTO estudioProducto = (DatosEstudioProductoDTO) request.getAttribute("estudioProducto");
        DatosEstudioUsuarioDTO estudioUsuario = (DatosEstudioUsuarioDTO) request.getAttribute("estudioUsuario");
        String errorCantidad = (String) request.getAttribute("error");
    %>
    <body>
        <form method="POST" action="DatosEstudioGuardarServlet">
            
            <input type="hidden" name="idEstudio" value="<%= estudio == null ? "" : estudio.getIdEstudio()%>" />
            <input type="hidden" name="idEstudioProducto" value="<%= estudioProducto == null ? "" : estudioProducto.getId() %>" />
            <input type="hidden" name="idEstudioUsuario" value="<%= estudioUsuario == null ? "" : estudioUsuario.getId() %>" />
        <%
            if(estudio.getProducto()){
        %>
                <h1>Estudio de productos</h1>
                Ordenar por:<br>
                <input type="checkbox" name="estudioProducto" value="categorias"<%= estudioProducto != null && estudioProducto.getCategorias() ? "checked" : "" %>/>Categorias<br>
                <input type="checkbox" name="estudioProducto" value="vendidos"<%= estudioProducto != null && estudioProducto.getVendidos() ? "checked" : "" %>/>Vendidos<br>
                <input type="checkbox" name="estudioProducto" value="enPromocion"<%= estudioProducto != null && estudioProducto.getPromocion() ? "checked" : "" %>/>En promoción<br><br>
                Precio de Salida: &nbsp;&nbsp;
                <input type="number" step="0.01" size="20" name="precioSalida" value="<%= estudioProducto != null && estudioProducto.getPrecioSalida()!= null ? estudioProducto.getPrecioSalida() : ""  %>" /><br><br>
                Precio Actual: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="number" step="0.01" size="20" name="precioActual" value="<%= estudioProducto != null && estudioProducto.getPrecioActual()!= null ? estudioProducto.getPrecioActual() : "" %>"/><br>
                
                <%
                    if(errorCantidad != null && errorCantidad.equals("1")){
                        %> <br> ERROR: El precio actual no puede ser mayor que el precio de salida <%
                    }
                %>
                
        <%
            }else{
        %>
                <h1>Estudio de <%= estudio.getVendedor() ? "Vendedores" : "Compradores" %></h1>
                Ordenar por:<br>
                <input type="checkbox" name="estudioUsuario" value="nombre"
                       <%= estudioUsuario != null && estudioUsuario.getNombre() ? " checked" : "" %>/>Nombre<br>
                <input type="checkbox" name="estudioUsuario" value="apellidos"
                       <%= estudioUsuario != null && estudioUsuario.getApellidos()? " checked" : "" %>/>Apellidos<br>
                <input type="checkbox" name="estudioUsuario" value="<%= estudio.getVendedor() ? "ingresos" : "gastos" %>" 
                       <%= estudioUsuario != null && estudioUsuario.getIngresos()? " checked" : "" %>/>
                       <%= estudio.getVendedor() ? "Ingresos" : "Gastos" %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="checkbox" name="estudioUsuario" value="ascendente"
                       <%= estudioUsuario != null && estudioUsuario.getAscendente()? " checked" : "" %>/>Ascendente<br>
        <%   
            }
        %>
                <br>
                <input type="submit" value="Enviar">
        </form>

        

    </body>
</html>
