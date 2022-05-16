<%-- 
    Document   : usuario
    Created on : 20-abr-2022, 17:12:51
    Author     : nicor(95%) Victor(5%)
--%>

<%@page import="java.util.Iterator"%>
<%@page import="trabajoTAW.dto.UsuarioDTO"%>
<%@page import="trabajoTAW.dto.CategoriaDTO"%>
<%@page import="trabajoTAW.dto.TipoUsuarioDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="trabajoTAW.entity.Categoria"%>
<%@page import="trabajoTAW.entity.Usuario"%>
<%@page import="trabajoTAW.entity.TipoUsuario"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        String tipoUsuario = (String)request.getAttribute("tipoUsuario");
        List<TipoUsuarioDTO> listaTipoUsuario = (List)request.getAttribute("tipoUsuarios");
        List<CategoriaDTO> listaCategorias = (List)request.getAttribute("categorias");
        List<CategoriaDTO> listaCategoriasUsuario = (List)request.getAttribute("categoriasFavoritas");
        List<Character> listaSexo = new ArrayList();
        listaSexo.add('H');
        listaSexo.add('M');
        List<String> listaTipoVia = new ArrayList();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        listaTipoVia.add("OFICINA");
        listaTipoVia.add("CALLE");
        UsuarioDTO usuario = (UsuarioDTO)request.getAttribute("usuario");
    %> 
    <body>
        <%if(tipoUsuario!=null){%>
            <jsp:include page="cabecera.jsp" /> 
        <%}%>
        <a href="UsuariosServlet">Volver</a>
        <h1>Datos del usuario</h1>
        <form method="POST" action="UsuarioGuardarServlet">
            <input type="hidden" name="idDireccion" value="<%= usuario==null? "": usuario.getDireccion().getIdDireccion() %>" />
            <input type="hidden" name="id" value="<%= usuario==null? "": usuario.getIdUsuario() %>" />
            Nombre de Usuario: <input type="text" size="30" name="nombreUsuario" value="<%= usuario==null? "": usuario.getNombreUsuario() %>" /> <br>
            Contraseña: <input type="password" size="30" name="contrasenya" value="<%= usuario==null? "": usuario.getContrasenya() %>" /> <br>
            Nombre: <input type="text" size="30" name="nombre" value="<%= usuario==null? "": usuario.getNombre() %>" /> <br>
            Apellidos: <input type="text" size="30" name="primerApellido" value="<%= usuario==null? "": usuario.getPrimerApellido() %>" /> <input type="text" name="segundoApellido" size="30" value="<%= usuario==null? "": usuario.getSegundoApellido() %>" /><br>
            Email:<input type="text" size="40" name="email" value="<%= usuario==null? "": usuario.getEmail() %>" /> <br>              
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
            </select><br>
            Fecha Nacimiento: <input type="date" size="30" name="fechaNacimiento" value="<%= usuario==null? "" : usuario.getFechaNacimiento()!=null? new java.sql.Date(usuario.getFechaNacimiento().getTime()):""  %>" /> <br>
            Tipo Usuario: 
            <select name="tipoUsuario">
            <% 
                if(tipoUsuario != null && tipoUsuario.equalsIgnoreCase("Administrador")){
                    for (TipoUsuarioDTO uu : listaTipoUsuario) {
                    String selected = "";
                    if (usuario != null && usuario.getTipoUsuario().getTipo().equals(uu.getTipo())) {
                        selected = "selected";
                    }
                
                
            %>
            <option <%= selected %> value="<%= uu.getIdTipoUsuario() %>"><%= uu.getTipo() %></option>
                
            <% 
                    }
                }else{
                    for (TipoUsuarioDTO uu : listaTipoUsuario.subList(1, 3)) {
                    String selected = "";
                    if (usuario != null && usuario.getTipoUsuario().getTipo().equals(uu.getTipo())) {
                        selected = "selected";
                    }

            %>    
        <option <%= selected %> value="<%= uu.getIdTipoUsuario() %>"><%= uu.getTipo() %></option>
        <%
                    }
                }
        %>
            </select><br>
           
            Categorias Favoritas: 
            
            <% 
                
                for (CategoriaDTO dc: listaCategorias) {
                    String checked = "";
                    if(tipoUsuario!=null && listaCategoriasUsuario !=null && listaCategoriasUsuario.contains(dc)) checked = "checked";
            %>
            <input name = "categorias" type = "checkbox" <%= checked %> value="<%= dc.getIdCategoria() %>"><%= dc.getNombre()%>  
                
            <% 
                    
                }
            %>                
            <br>
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
            </select><br>
            Calle:<input type="text" size="40" name="calle" value="<%= usuario==null? "": usuario.getDireccion().getCalle() %>" /> <br>  
            Numero:<input type="number" size="40" name="numero" value="<%= usuario==null? "": usuario.getDireccion().getNumero()%>" /> <br>
            Codigo Postal:<input type="number" size="40" name="codigoPostal" value="<%= usuario==null? "": usuario.getDireccion().getCodigoPostal() %>" /> <br>  
            Planta:<input type="number" size="40" name="planta" value="<%= usuario==null? "": usuario.getDireccion().getPlanta() %>" /> <br>  
            Puerta:<input type="text" size="40" name="puerta" value="<%= usuario==null? "": usuario.getDireccion().getPuerta() %>" /> <br>
            
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
