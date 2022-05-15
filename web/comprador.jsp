<%-- 
    Document   : comprador
    Created on : 02-may-2022, 14:35:59
    Author     : Victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VistaPrincipalComprador</title>
    </head>
    <body>
        <h1>Bienvenido!</h1>
        
        <form method="post" action="BuscarProductosServlet">
            Buscar productos: <input type="text" name="buscador" value="" />
        </form>
        <br>
        
        <a href="ProductosFavoritosServlet"><input type="button" value="Lista de favoritos" /></a>
        <a href="ProductosCompradosServlet"><input type="button" value="Productos comprados" /></a>
        <a href="NotificacionesServlet"><input type="button" value="Notificaciones" /></a> <br><br>
        
        <a href="LogoutServlet">Salir</a>
    </body>
</html>
