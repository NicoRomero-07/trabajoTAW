<%-- 
    Document   : mensajes
    Created on : 02-may-2022, 9:49:28
    Author     : Nicolás Zhao (100%)
--%>

<%@page import="trabajoTAW.dto.ListaUsuarioDTO"%>
<%@page import="trabajoTAW.dto.UsuarioDTO"%>
<%@page import="trabajoTAW.dto.NotificacionDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%-- 
<%@page import="trabajoTAW.entity.ListaUsuario"%>
<%@page import="trabajoTAW.entity.Usuario"%>
<%@page import="trabajoTAW.entity.Notificacion"%>
--%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bandeja de mensajes</title>
    </head>
    <%
        List<NotificacionDTO> notificaciones = (List)request.getAttribute("notificaciones");
        UsuarioDTO comprador = (UsuarioDTO)request.getAttribute("comprador");
        ListaUsuarioDTO lista = (ListaUsuarioDTO) request.getAttribute("lista");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
    %>
    <body>
        <jsp:include page="cabeceraMarketing.jsp" /> 
        <h1>Bandeja de mensajes</h1>
        <%
            
        if (notificaciones != null && !notificaciones.isEmpty()){
        %>
        <table border="1">
        <tr>
            <th>FECHA ENVIO</th>
            <th>CONTENIDO</th>
            <th></th>                                                     
        </tr>
        <%
                for (NotificacionDTO notificacion : notificaciones) {
        %> 
         <tr>
             <td><%= formatter.format(notificacion.getFechaEnvio())%></td>
             <td><%= notificacion.getContenido() %></td>
             <td><a href="MensajeBorrarServlet?id=<%= notificacion.getIdNotificacion()%>&idComprador=<%= comprador.getIdUsuario() %>&idlista=<%=lista.getIdListaUsuario() %>">Borrar</a></td> 
         </tr>
        <%
             }
        %>
        </table>
        <%
            }else{
        %>
        <h2>NO TIENE MENSAJES</h2>
        <%
            }
        %>
        <br/>
        <a href="CompradorServlet?id=<%=lista.getIdListaUsuario()%>"><input type="button" value="Volver"/></a>
    </body>
</html>
