<%-- 
    Document   : listaComprador
    Created on : 22-abr-2022, 10:29:30
    Author     : nicol
--%>

<%@page import="trabajoTAW.entity.UsuarioListaUsuario"%>
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
            List<UsuarioListaUsuario> relacionUsuariosListas = (List)request.getAttribute("relacionUsuariosListas");
    %>    
    <body>
        <h1>Datos de la lista</h1>
        <form method="POST" action="ListaCompradorGuardarServlet">
            <input type="hidden" name="id" value="<%= listaComprador==null? "": listaComprador.getIdListaUsuario() %>" />
            Nombre: <input type="text" size="30" name="nombre" value="<%= listaComprador==null? "": listaComprador.getNombre() %>" /> <br/>
            Compradores: 
            <% 
                for (Usuario comprador: compradores){
                    String checked = "";
                    boolean found = false;
                    int index = 0;
                    
                    while (!found && index < relacionUsuariosListas.size()){
                        if (relacionUsuariosListas.get(index).getUsuario1().equals(comprador) && relacionUsuariosListas.get(index).getListaUsuario().equals(listaComprador)){
                            checked = "checked";
                            found = true;
                        }
                        index++;
                    }
            %>
            <input type="checkbox" name="compradores" value="<%= comprador.getNombreUsuario()%>"  <%= checked %>/> <%= comprador.getNombreUsuario()%> <br/>
            <%
                }
            %>
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
