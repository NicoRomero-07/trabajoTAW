<%-- 
    Document   : publicarProducto
    Created on : Apr 25, 2022, 9:04:48 AM
    Author     : Pablo
--%>

<%@page import="trabajoTAW.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Publicar Producto</title>
    </head>
    <body>
        <form method="POST" action="ProductoGuardarServlet">
            Nombre del Producto: <input type="text" name="nombreproducto" value="" /><br/><br/>
            Descripción: <br/><br/><textarea name="descripcion" rows="10" cols="50" ></textarea><br/><br/>
            Precio Salida: <input type="number" name="preciosalida" value="" min="0" /><br/><br/>
            URL Imagen: <input type="text" name="imagen" value="" /><br/><br/>
            Categoría:
            <select name="categorias">
            <%
                List<Categoria> categoria = (List) request.getAttribute("categorias");
                for(Categoria c : categoria) {
                    
            %>  
            <option <%=c.getNombre()%> value="<%=c.getNombre()%>"><%=c.getNombre()%></option>
            <%
                }
            %>
            </select>
            
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
