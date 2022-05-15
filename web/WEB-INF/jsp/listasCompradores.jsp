<%-- 
    Document   : listasCompradores
    Created on : 17-abr-2022, 14:49:02
    Author     : Nicolás Zhao (100%)
--%>

<%@page import="trabajoTAW.dto.ListaUsuarioDTO"%>
<%--<%@page import="trabajoTAW.entity.ListaUsuario"%> --%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de listas de compradores</title>
    </head>
    <%
        List<ListaUsuarioDTO> listas = (List)request.getAttribute("listasCompradores");
    %>
    <body>
        <jsp:include page="cabeceraMarketing.jsp" /> 
        <h1>Listado de listas de compradores</h1>
        
        <%
            if (listas!= null && !listas.isEmpty()){
        %>
        <form method="post" action="ListaCompradorServlet">
            Nombre: <input type="text" name="filtroNombre" value="" />
            <input type="submit" value="Filtrar" />
        </form>
        <br>
        <table border="1">
            <tr>
                <th>ID_LISTA</th>
                <th>NOMBRE_LISTA</th>                  
                <th></th>                     
                <th></th>              
                <th></th>
                <th></th>
            </tr>
            <%
                for (ListaUsuarioDTO lista: listas) {
            %>
            <tr>
                <td><%= lista.getIdListaUsuario() %></td>
                <td><%= lista.getNombre()%></td>        
                <td><a href="ListaCompradorNuevoEditarServlet?id=<%= lista.getIdListaUsuario() %>">Editar</a></td>      
                <td><a href="ListaCompradorBorrarServlet?id=<%= lista.getIdListaUsuario() %>">Borrar</a></td>       
                <td><a href="ListaCompradorEnviarNotificacionServlet?id=<%= lista.getIdListaUsuario() %>">Notificar promociones</a></td>
                <td><a href="CompradorServlet?id=<%= lista.getIdListaUsuario() %>">Ver compradores</a></td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            }else{
        %>
        <h2>NO HAY LISTAS</h2>
        <%
            }
        %>
        <br>
        <a href="ListaCompradorNuevoEditarServlet">Crear nueva lista ...</a>
    </body>
</html>
