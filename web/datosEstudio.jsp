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
    %>
    <body>
        <input type="hidden" name="id" value="<%= estudio == null ? "" : estudio.getIdEstudio()%>" />
        <form method="POST" action="DatosEstudioGuardarServlet">
        <%
            if(estudio.getProducto()){
        %>
                <h1>Estudio de productos</h1>
                Ordenar por:<br/>
                <input type="checkbox" name="ordenEstudio" value="categorias"/>Categorias<br/>
                <input type="checkbox" name="ordenEstudio" value="vendidos"/>Vendidos<br/>
                <input type="checkbox" name="ordenEstudio" value="enPromocion"/>En promoción<br/><br/>
                Precio de Salida: &nbsp;&nbsp;<input type="text" size="20" name="precioSalida" value=""/><br/><br/>
                Precio Actual: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="20" name="precioSalida" value=""/><br/>
        <%
            }else{
        %>
                <h1>Estudio de compradores/vendedores</h1>
                Ordenar por:<br/>
                <input type="checkbox" name="ordenEstudio" value="nombre"/>Nombre<br/>
                <input type="checkbox" name="ordenEstudio" value="apellidos"/>Apellidos<br/>
                <input type="checkbox" name="ordenEstudio" value="ingreso"/>Ingresos &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="checkbox" name="ascendente" value="ascendente"/>Ascendente<br/>
        </form>
        <%   
            }
        %>
        
        

        

    </body>
</html>
