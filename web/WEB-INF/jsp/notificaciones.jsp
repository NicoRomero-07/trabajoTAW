<%-- 
    Document   : notificaciones
    Created on : 15-may-2022, 10:22:10
    Author     : Victor
--%>

<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="trabajoTAW.dto.UsuarioDTO"%>
<%@page import="trabajoTAW.dto.ProductoDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notificaciones</title>
    </head>
    <body>
        <h1>Notificaciones</h1>
        <table border="1">
            <tr>
                <th>ID_PRODUCTO</th>
                <th>NOMBRE</th>
                <th>DESCRIPCION</th>
                <th>URL_FOTO</th>
                <th>CATEGORIA</th>
                <th>ESTADO_DE_LA_SUBASTA</th>               
            </tr>
            
            <%
                List<ProductoDTO> pujas = (List) request.getAttribute("pujas");
                List<ProductoDTO> favoritos = (List) request.getAttribute("favoritos");
                UsuarioDTO usuario = (UsuarioDTO) request.getAttribute("usuario");
                
                Set<ProductoDTO> productos = new HashSet();
                productos.addAll(pujas);
                productos.addAll(favoritos);
                for(ProductoDTO p: productos){
                    
                
            %>
            
            <tr>
                <td><%=p.getIdProducto()%></td>
                <td><%=p.getNombre()%></td>
                <td><%=p.getDescripcion()%></td>
                <td><%=p.getUrlFoto()%></td>
                <td><%=p.getCategoria()%></td>
            
            <%
                Date hoy = new Date();
                if(p.getFechaFinSubasta().compareTo(hoy) < 0){
                    if(p.getComprador().getIdUsuario().equals(usuario.getIdUsuario())){
            %>
            
            <td>Has ganado la subasta :)</td>
            <%
                    }else{
            %>
            
            <td>Has perdido la subasta :(</td>
            <%                        
                    }
            %>
            
            <%
                }else{
                    
            %>
            <td>En subasta</td>
            <%
                }
              }  
            %>
            
            </tr>
        </table>
            <a href="CompradorPrincipalServlet">Volver</a>
    </body>
</html>
