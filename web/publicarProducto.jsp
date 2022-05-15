<%-- 
    Document   : publicarProducto
    Created on : Apr 25, 2022, 9:04:48 AM
    Author     : Pablo
--%>

<%@page import="trabajoTAW.dto.ProductoDTO"%>
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
            <%
                ProductoDTO producto = (ProductoDTO) request.getAttribute("producto");
            %>
            Nombre del Producto: <input type="text" name="nombreproducto" value="<%=producto==null ? "": producto.getNombre()%>" /><br><br>
            Descripción: <br><br><textarea name="descripcion" rows="10" cols="50" ><%=producto==null ? "": producto.getDescripcion()%></textarea><br><br>
            Precio Salida: <input type="number" name="preciosalida" value="<%=producto==null ? "": producto.getPrecioSalida()%>" min="0" /><br><br>
            URL Imagen: <input type="text" name="imagen" value="<%=producto==null ? "": producto.getUrlFoto()%>" /><br><br>
            Categoría:
            <select name="categorias">
            <%
                List<Categoria> categoria = (List) request.getAttribute("categorias");
                int i = 0;
                for(Categoria c : categoria) {
                    i++;
            %>  
            <option <%=c.getNombre()%> value="<%=i%>" <%=producto==null? "": (producto.getCategoria()==c.getIdCategoria() ? "selected": "")%>><%=c.getNombre()%></option>
            <%
                }
            %>
            </select>
            
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
