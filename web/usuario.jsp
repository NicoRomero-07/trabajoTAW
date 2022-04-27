<%-- 
    Document   : usuario
    Created on : 20-abr-2022, 17:12:51
    Author     : nicor
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="trabajoTAW.entity.Categoria"%>
<%@page import="trabajoTAW.entity.Usuario"%>
<%@page import="trabajoTAW.entity.TipoUsuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        List<TipoUsuario> listaTipoUsuario = (List)request.getAttribute("tipoUsuarios");
        List<Categoria> listaCategorias = (List)request.getAttribute("categorias");
        List<Character> listaSexo = new ArrayList();
        listaSexo.add('H');
        listaSexo.add('M');
        List<String> listaTipoVia = new ArrayList();
        listaTipoVia.add("OFICINA");
        listaTipoVia.add("CALLE");
        Usuario usuario = (Usuario)request.getAttribute("usuario");
    %> 
    <body>
        <h1>Datos del usuario</h1>
        <form method="POST" action="UsuarioGuardarServlet">
            <input type="hidden" name="id" value="<%= usuario==null? "": usuario.getIdUsuario() %>" />
            Nombre de Usuario: <input type="text" size="30" name="nombreUsuario" value="<%= usuario==null? "": usuario.getNombreUsuario() %>" /> <br/>
            Nombre: <input type="text" size="30" name="nombre" value="<%= usuario==null? "": usuario.getNombre() %>" /> <br/>
            Apellidos: <input type="text" size="30" name="primerApellido" value="<%= usuario==null? "": usuario.getPrimerApellido() %>" /> <input type="text" name="segundoApellido" size="30" value="<%= usuario==null? "": usuario.getSegundoApellido() %>" /><br/>
            Email:<input type="text" size="40" name="email" value="<%= usuario==null? "": usuario.getEmail() %>" /> <br/>              
            Sexo:
            <select name = "sexo">
                <% 
                for (Character s : listaSexo) {
                    String selected = "";
                    if (usuario != null && usuario.getSexo().equals(s)) {
                        selected = "selected";
                    }
                %>
                <option <%= selected %> value="<%= s %>"><%= s %></option>
                
                <% 
                    }
                %>  
            </select><br/>
            Tipo Usuario: 
            <select name="tipoUsuario">
            <% 
                for (TipoUsuario uu : listaTipoUsuario) {
                    String selected = "";
                    if (usuario != null && usuario.getTipoUsuario().getTipo().equals(uu.getTipo())) {
                        selected = "selected";
                    }
            %>
            <option <%= selected %> value="<%= uu.getIdTipoUsuario() %>"><%= uu.getTipo() %></option>
                
            <% 
                }
            %>                
            </select><br/>
           
            Categorias Favoritas: 
            <select name="categorias">
            <% 
                
                for (Categoria dc: listaCategorias) {
                    if(usuario!=null && usuario.getCategoriaList().contains(dc)){
                        
            %>
            <option value="<%= dc.getNombre() %>"><%= dc.getNombre() %></option>
                
            <% 
                    }
                }
            %>                
            </select><br/>
            Tipo de via:
            <select name = "tipoVia">
                <% 
                for (String s : listaTipoVia) {
                    String selected = "";
                    if (usuario != null && usuario.getDireccion().getTipo().equals(s)) {
                        selected = "selected";
                    }
                %>
                <option <%= selected %> value="<%= s %>"><%= s %></option>
                
                <% 
                    }
                %>  
            </select><br/>
            Calle:<input type="text" size="40" name="calle" value="<%= usuario==null? "": usuario.getDireccion().getCalle() %>" /> <br/>  
            Numero:<input type="number" size="40" name="numero" value="<%= usuario==null? "": usuario.getDireccion().getNumero()%>" /> <br/>
            Codigo Postal:<input type="number" size="40" name="codigoPostal" value="<%= usuario==null? "": usuario.getDireccion().getCodigoPostal() %>" /> <br/>  
            Planta:<input type="number" size="40" name="planta" value="<%= usuario==null? "": usuario.getDireccion().getPlanta() %>" /> <br/>  
            Puerta:<input type="text" size="40" name="puerta" value="<%= usuario==null? "": usuario.getDireccion().getPuerta() %>" /> <br/>
            
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
