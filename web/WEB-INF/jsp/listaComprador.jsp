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
            Boolean error = (Boolean) request.getAttribute("error");
    %>    
    <body>
        <h1>Datos de la lista</h1>
        <%
            if (error== null || !error){
           
        %>
        <form method="POST" action="ListaCompradorGuardarServlet">
            <input type="hidden" name="id" value="<%= listaComprador==null? "": listaComprador.getIdListaUsuario() %>" />
            <table>
                <tr>
                    <th>Nombre:</th>
                    <td><input type="text" size="30" name="nombre" value="<%= listaComprador==null? "": listaComprador.getNombre() %>" required/></td>
                </tr>
                <tr>
                    <th>Compradores: </th>
                </tr>
                    <%                  
                        for (Usuario comprador: compradores){
                            List<ListaUsuario> listasRelacionadas = comprador.getListaUsuarioList();
                            String checked = "";
                            if(listaComprador != null && listasRelacionadas != null && listasRelacionadas.contains(listaComprador)){
                                checked = "checked";
                            }

                %>
                <tr><td><input type="checkbox" name="compradores" value="<%= comprador.getIdUsuario()%>"  <%= checked %>/> <%= comprador.getNombreUsuario()%></td></tr>
                <%
                        }
                %>
                
            </table>
                <br/>
            <input type="submit" value="Confirmar" />
        </form>
            <%
                }else{
            %>
            <h2>NO SE HA ELEGIDO NINGUN COMPRADOR</h2>
            <%
                }
            %>
            <br/>
    <a href="ListaCompradorServlet">Volver</a>
    </body>
</html>
