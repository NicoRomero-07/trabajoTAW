<%-- 
    Document   : listasCompradores
    Created on : 17-abr-2022, 14:49:02
    Author     : nicol
--%>

<%@page import="trabajoTAW.entity.ListaUsuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de listas de compradores</title>
    </head>
    <body>
        <h1>Listado de listas de compradores</h1>
        <form method="post" action="ListaCompradorServlet">
            ID: <input type="text" name="filtroId" value="" /><br>
            Nombre: <input type="text" name="filtroNombre" value="" /><br>
            <input type="submit" value="Filtrar" />
        </form><br>
        <table border="1">
            <tr>
                <th>ID_LISTA</th>
                <th>NOMBRE_LISTA</th>                  
                <th></th>                     
                <th></th>              
                <th></th>
            </tr>
            <%
                List<ListaUsuario> listas = (List)request.getAttribute("listasCompradores");
                for (ListaUsuario lista: listas) {
            %>
            <tr>
                <td><%= lista.getIdListaUsuario() %></td>
                <td><%= lista.getNombre()%></td>        
                <td><a href="ListaCompradorNuevoEditarServlet?id=<%= lista.getIdListaUsuario() %>">Editar</a></td>      
                <td><a href="ListaCompradorBorrarServlet?id=<%= lista.getIdListaUsuario() %>">Borrar</a></td>       
                <td><a href="ListaCompradorEnviarNotificacionServlet?id=<%= lista.getIdListaUsuario() %>">Notificar promociones</a></td>
            </tr>
            <%
                }
            %>
        </table>
        <br>
        <a href="ListaCompradorNuevoEditarServlet">Crear nueva lista ...</a>
    </body>
</html>
