<%-- 
    Document   : listaComprador
    Created on : 22-abr-2022, 10:29:30
    Author     : nicol
--%>

<%@page import="java.util.List"%>
<%@page import="trabajoTAW.entity.Usuario"%>
<%@page import="trabajoTAW.entity.ListaUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nueva lista</title>
    </head>
    <%
            ListaUsuario listaComprador = (ListaUsuario)request.getAttribute("listaComprador");
            List<Usuario> compradores = (List)request.getAttribute("compradores");
    %>    
    <body>
        <h1>Datos de la lista</h1>
        <form method="POST" action="ListaCompradorGuardarServlet">
            <input type="hidden" name="id" value="<%= listaComprador==null? "": listaComprador.getIdListaUsuario() %>" />
            Nombre: <input type="text" size="30" name="nombre" value="<%= listaComprador==null? "": listaComprador.getNombre() %>" /> <br>
            Compradores: <br>
            <% 
                if(compradores != null){                  
                    for (Usuario comprador: compradores){
                        List<ListaUsuario> listasRelacionadas = comprador.getListaUsuarioList();
                        String checked = "";
                        if(listasRelacionadas != null && listasRelacionadas.contains(listaComprador)){
                            checked = "checked";
                        }
                    
            %>
            <input type="checkbox" name="compradores" value="<%= comprador.getIdUsuario()%>"  <%= checked %>/> <%= comprador.getNombreUsuario()%> <br>
            <%
                    }
                }
            %>
            <input type="submit" value="Confirmar" />
        </form>
    </body>
</html>
