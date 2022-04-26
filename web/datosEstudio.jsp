<%-- 
    Document   : datosEstudio
    Created on : 20-abr-2022, 20:09:51
    Author     : Alfonso
--%>

<%@page import="trabajoTAW.entity.DatosEstudioUsuario"%>
<%@page import="trabajoTAW.entity.DatosEstudioProducto"%>
<%@page import="trabajoTAW.entity.Estudio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos de Estudio</title>
    </head>
    <%
        Estudio estudio = (Estudio) request.getAttribute("estudio");
        DatosEstudioProducto estudioProducto = (DatosEstudioProducto) request.getAttribute("estudioProducto");
        DatosEstudioUsuario estudioUsuario = (DatosEstudioUsuario) request.getAttribute("estudioUsuario");
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
                Ordenar por:<br/>
                <input type="checkbox" name="estudioProducto" value="categorias"<%= estudioProducto != null && estudioProducto.getCategorias() ? "checked" : "" %>Categorias<br/>
                <input type="checkbox" name="estudioProducto" value="vendidos"<%= estudioProducto != null && estudioProducto.getVendidos() ? "checked" : "" %>/>Vendidos<br/>
                <input type="checkbox" name="estudioProducto" value="enPromocion"<%= estudioProducto != null && estudioProducto.getPromocion() ? "checked" : "" %>/>En promoción<br/><br/>
                Precio de Salida: &nbsp;&nbsp;
                <input type="text" size="20" name="precioSalida" value="<%= estudioProducto != null && estudioProducto.getPrecioSalida()== null ? estudioProducto.getPrecioSalida() : ""  %>" /><br/><br/>
                Precio Actual: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="text" size="20" name="precioActual" value="<%= estudioProducto != null && estudioProducto.getPrecioActual()== null ? estudioProducto.getPrecioActual() : "" %>"/><br/>
        <%
            }else{
        %>
                <h1>Estudio de compradores/vendedores</h1>
                Ordenar por:<br/>
                <input type="checkbox" name="estudioUsuario" value="nombre"<%= estudioUsuario != null && estudioUsuario.getNombre() ? " checked" : "" %>/>Nombre<br/>
                <input type="checkbox" name="estudioUsuario" value="apellidos"<%= estudioUsuario != null && estudioUsuario.getApellidos()? " checked" : "" %>/>Apellidos<br/>
                <input type="checkbox" name="estudioUsuario" value="ingresos"<%= estudioUsuario != null && estudioUsuario.getIngresos()? " checked" : "" %>/>Ingresos &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="checkbox" name="estudioUsuario" value="ascendente"<%= estudioUsuario != null && estudioUsuario.getAscendente()? " checked" : "" %>/>Ascendente<br/>
        <%   
            }
        %>
                <input type="submit" value="Enviar">
        </form>

        

    </body>
</html>
