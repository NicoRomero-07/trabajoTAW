<%-- 
    Document   : datosEstudio
    Created on : 20-abr-2022, 20:09:51
    Author     : Alfonso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos de Estudio</title>
    </head>
    <body>
        <h1>Estudio de compradores/vendedores</h1>
        <!--<h1>Estudio de productos</h1>-->
        
        Ordenar por:<br/>
        <input type="radio" name="ordenEstudio" value="nombre"/>Nombre<br/>
        <input type="radio" name="ordenEstudio" value="apellidos"/>Apellidos<br/>
        <input type="radio" name="ordenEstudio" value="ingreso"/>Ingresos &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="checkbox" name="ascendente" value="ascendente"/>Ascendente<br/>

        Ordenar por:<br/>
        <input type="checkbox" name="ordenEstudio" value="categorias"/>Categorias<br/>
        <input type="checkbox" name="ordenEstudio" value="vendidos"/>Vendidos<br/>
        <input type="checkbox" name="ordenEstudio" value="enPromocion"/>En promoción<br/><br/>
        Precio de Salida: &nbsp;&nbsp;<input type="text" size="20" name="precioSalida" value=""/><br/><br/>
        Precio Actual: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" size="20" name="precioSalida" value=""/><br/>
        
    </body>
</html>
