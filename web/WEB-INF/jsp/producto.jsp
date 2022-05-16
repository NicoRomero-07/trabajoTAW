<%-- 
    Document   : producto
    Created on : 15-may-2022, 12:23:59
    Author     : nicor
--%>

<%@page import="trabajoTAW.dto.ProductoDTO"%>
<%@page import="trabajoTAW.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Producto</title>
    </head>
    <body>
        <h1>Producto</h1>
        <a href="ProductosServlet">Volver</a>
        <form method="POST" action="ProductoGuardarServlet">
            <%
                ProductoDTO producto = (ProductoDTO) request.getAttribute("producto");
            %>
            <input type="hidden" name="id" value="<%= producto==null ? "":producto.getIdProducto() %>"/>
            Nombre del Producto: <input type="text" name="nombreproducto" value="<%=producto==null ? "": producto.getNombre()%>" /><br><br>
            Descripción: <br><br><textarea name="descripcion" rows="10" cols="50" ><%=producto==null ? "": producto.getDescripcion()%></textarea><br><br>
            Precio Salida: <input type="number" name="preciosalida" value="<%=producto==null ? "": producto.getPrecioSalida()%>" min="0" /><br><br>
            URL Imagen: <input type="text" name="imagen" value="<%=producto==null ? "": producto.getUrlFoto()%>" /><br><br>
            Fecha inicio de subasta: <input type = "date" name="fechaInicio" value="<%=producto==null ? "": new java.sql.Date(producto.getFechaInicioSubasta().getTime()) %>"/><br><br>
            Fecha fin de subasta: <input type = "date" name="fechaFin" value="<%=producto==null ? "": new java.sql.Date(producto.getFechaFinSubasta().getTime()) %>"/><br><br>
            Comprador: <input type = "text" name="comprador" value="<%=producto==null ? "": (producto.getComprador()==null ? "": producto.getComprador().getNombreUsuario()) %>"/><br><br>
            Promocion: <input type = "checkbox" name="promocion" <%= producto==null ? "":producto.getEnPromocion() ? "checked":"" %> value="<%=producto==null ? "": producto.getEnPromocion() %>"/><br><br>
            Publicador: <input type = "text" name="publicador" value="<%=producto==null ? "": producto.getPublicador().getNombreUsuario() %>"/><br><br>
            Categoría:
            <select name="categoria">
            <%
                List<Categoria> categorias = (List) request.getAttribute("categorias");
                
                for(Categoria c : categorias) {
                    String selected = "";
                    if(producto!=null && c.equals(producto.getCategoria())) selected ="selected";
            %>  
            <option <%= selected %> value="<%= c.getIdCategoria() %>"><%= c.getNombre()%></option>
            <%
                }
            %>
            </select>
            
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>

