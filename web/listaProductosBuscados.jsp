<%-- 
    Document   : listaProductosBuscados
    Created on : 03-may-2022, 20:02:14
    Author     : Victor
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="trabajoTAW.dto.ProductoDTO"%>
<%@page import="trabajoTAW.entity.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Productos</title>
    </head>
    <body>
        
        <form method="post" action="BuscarProductosServlet">
            Buscar productos: <input type="text" name="buscador" value="" />
        </form>
        <br>
        <table border="1">
            <tr>
                <th>ID_PRODUCTO</th>
                <th>NOMBRE</th>
                <th>DESCRIPCIÓN</th>
                <th>PRECIO_SALIDA</th>
                <th>URL_FOTO</th>
                <th>CATEGORÍA</th>
                <th>FECHA_INICIO_SUBASTA</th>
                <th>FECHA_FIN_SUBASTA</th>
                <th>EN_PROMOCIÓN</th>
                <th></th>
                <th></th>
            </tr>
            
                <%
                List<ProductoDTO> productos = (List)request.getAttribute("productos");
                List<ProductoDTO> productosFavoritos = (List)request.getAttribute("productosFavoritos");
                SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yy");
                Date hoy = new Date();
                for (ProductoDTO prod: productos) {
                    boolean subastado = prod.getFechaFinSubasta().compareTo(hoy) > 0;
                %>
            <tr>
                <td><%= prod.getIdProducto()%></td>
                <td><%= prod.getNombre()%></td>
                <td><%= prod.getDescripcion()%></td>
                <td><%= prod.getPrecioSalida()%></td>
                <td><%= prod.getUrlFoto()%></td>
                <td><%= prod.getCategoria()%></td>
                <td><%= fecha.format(prod.getFechaInicioSubasta())%></td>
                <td><%= fecha.format(prod.getFechaFinSubasta())%></td>
                <%
                if(prod.getEnPromocion()) {
                %>
                <td>Si</td>
                <%
                } else {
                %>
                <td>No</td>      
                <%
                }
                    if(subastado){


                        if(!productosFavoritos.contains(prod)){
                %>
                <td><a href="ProductoFavoritoNuevoServlet?id=<%=prod.getIdProducto() %>"><input type="submit" value="Añadir a favoritos"></a></td>
                <%
                        }else{
                
                %>
                <td><a href="ProductoFavoritoBorrarServlet?id=<%=prod.getIdProducto() %>"><input type="submit" value="Quitar de favoritos"></a></td>

                <%
                        }
                %>
                
                <%
                    }
                    if(subastado){
                %>
                
                <td><a href="PujaServlet?id=<%=prod.getIdProducto()%>"><input type="submit" value="Pujar"></a></td>
                <%
                    }else{

                %>
                
                <td>No disponible</td>
                <%
}
                  }  
                %>
            </tr>
        </table>
            <a href="CompradorPrincipalServlet">Volver</a>
    </body>
</html>
