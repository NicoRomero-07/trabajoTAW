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
        <table border="1">
            <tr>
                <th>ID LISTA</th>
                <th>NOMBRE LISTA</th>                  
                <th></th>                     
                <th></th>                                                                                                       
            </tr>
            <%
            List<ListaUsuario> listas = (List)request.getAttribute("listasCompradores");
            for (ListaUsuario lista: listas) {
            %>
            <tr>
                <td>< %= lista.getIdListausuario() %></td>
                <td>< %= lista.getNombreListaUsuario() %></td>            
                <td><a href="ListaCompradorBorrarServlet?id=<%= lista.getIdListaUsuario() %>" ></td>                     
                <td><a href="ListaCompradorNuevoEditarServlet?id=<%= lista.getIdListaUsuario() %>" ></td>                                                         
            </tr>
            <%
            }
            %>
        </table>
        <a href="ListaCompradorNuevoEditarServlet">Crear nueva lista ...</a>
    </body>
</html>
