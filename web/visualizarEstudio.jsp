<%-- 
    Document   : visualizarEstudio
    Created on : 28-abr-2022, 20:04:41
    Author     : Alfonso
--%>



<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="trabajoTAW.dto.ProductoDTO"%>
<%@page import="trabajoTAW.dto.UsuarioDTO"%>
<%@page import="trabajoTAW.dto.EstudioDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizar Estudio</title>
    </head>
    <%
        EstudioDTO estudio = (EstudioDTO) request.getAttribute("estudio");
        List<UsuarioDTO> listaUsuarios = (List<UsuarioDTO>) request.getAttribute("listaUsuarios");
        List<ProductoDTO> listaProductos = (List<ProductoDTO>) request.getAttribute("listaProductos");
        List<Double> ingresos = (List<Double>) request.getAttribute("ingresos");
    %>
    <body>
        <h1><%=estudio.getNombre()%></h1>
        Analista: <%= estudio.getAnalista().getNombre() %> <br>
        Descripcion: <%= estudio.getDescripcion() == null ? "" : estudio.getDescripcion() %> <br>
        
        <br>
        <% if (listaUsuarios != null && !listaUsuarios.isEmpty() ) {%>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>NOMBRE_USUARIO</th>
                <th>CONTRASEÑA</th>
                <th>EMAIL</th>
                <th>NOMBRE</th>
                <th>PRIMER_APELLIDO</th>
                <th>SEGUNDO_APELLIDO</th>
                <th>FECHA_NACIMIENTO</th>
                <th>SEXO</th>
                <th>TIPO_USUARIO</th>
                <%
                  if(ingresos != null && !ingresos.isEmpty()){
                      if(estudio.getVendedor()){
                          %><th>INGRESOS</th><%
                      }else{
                        %><th>GASTOS</th><%
                      }
                  }  
                %>
            </tr>
            <%
                int i = 0;
                for (UsuarioDTO user : listaUsuarios) {
            %> 
                <tr>
                    <td><%= user.getIdUsuario() != null ? user.getIdUsuario() : ""%></td>
                    <td><%= user.getNombreUsuario() != null ? user.getNombreUsuario() : ""%></td>
                    <td><%= user.getContrasenya() != null ? user.getContrasenya() : ""%></td>
                    <td><%= user.getEmail() != null ? user.getEmail() : ""%></td>
                    <td><%= user.getNombre() != null ? user.getNombre() : ""%></td>
                    <td><%= user.getPrimerApellido() != null ? user.getPrimerApellido() : ""%></td>
                    <td><%= user.getSegundoApellido() != null ? user.getSegundoApellido() : ""%></td>
                    <td><%= user.getFechaNacimiento().toString() != null ? user.getFechaNacimiento().toString() : ""%></td>
                    <td><%= user.getSexo() != null ? user.getSexo().charValue() : ""%></td>
                    <td><%= user.getTipoUsuario().getTipo() != null ? user.getTipoUsuario().getTipo() : ""%></td>
                    <%
                        if(ingresos != null && !ingresos.isEmpty() && i < ingresos.size()){
                    %><td><%= ingresos.get(i)%></td><%
                        }else if(ingresos != null && !ingresos.isEmpty() && i >= ingresos.size()){
                            %><td>0.00</td><%
                        }
                     %>
                </tr>
            <%
                    i++;
                }
            %>
        </table>
        <%} else if (listaProductos != null && !listaProductos.isEmpty() ) {%>
        <table border="1">
            <tr>
                <th>ID_PRODUCTO</th>
                <th>NOMBRE</th>
                <th>DESCRIPCIÓN</th>
                <th>PRECIO_SALIDA</th>
                <th>URL_FOTO</th>
                <th>CATEGORÍA</th>
                <th>PUBLICADOR</th>
                <th>EN_PROMOCIÓN</th>
                <th>FECHA_INICIO</th>
                <th>FECHA_FIN</th>
                <th>COMPRADOR</th>
            </tr>
            <%
                for (ProductoDTO prod : listaProductos) {
            %>
             <tr>
                <td><%= prod.getIdProducto() != null ? prod.getIdProducto() : ""%></td>
                <td><%= prod.getNombre() != null ? prod.getNombre() : ""%></td>
                <td><%= prod.getDescripcion() != null ? prod.getDescripcion() : ""%></td>
                <td><%= prod.getPrecioSalida() > 0 ? prod.getPrecioSalida() : 0.00 %></td>
                <td><%= prod.getUrlFoto() != null ? prod.getUrlFoto() : ""%></td>
                <td><%= prod.getCategoria()  %></td>
                <td><%= prod.getPublicador() != null ? prod.getPublicador().getNombreUsuario() : "" %></td>
                <td><%= prod.getEnPromocion() != null ? prod.getEnPromocion() : ""%></td>
                <td><% SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");%>
                    <%= fecha.format(prod.getFechaInicioSubasta())  != null ? fecha.format(prod.getFechaInicioSubasta()) : ""%></td>
                <td><%= fecha.format(prod.getFechaFinSubasta())  != null ? fecha.format(prod.getFechaFinSubasta()) : ""%></td>
                <td><%= prod.getComprador() != null ? prod.getComprador().getNombreUsuario() : ""  %></td>
            </tr>
            <%}%>
        </table>
        <%} else{
         %> 
            No hay informacion diponible
         <%
          }%>
    </body>
</html>
