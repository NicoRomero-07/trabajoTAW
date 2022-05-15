<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenidos al sistema</title>
    </head>
    <%
        String strError = (String)request.getAttribute("error");
        if (strError == null) strError = "";
    %>    
    <body>
        <h1>Login</h1>
        <%= strError %>
        <form method="POST" action="LoginServlet">
            <table>
                <tr>
                    <th>Usuario:</th> 
                    <td><input type="text" name="nombreusuario" value="" /></td>
                </tr>
                <tr>
                    <th>Clave:</th> 
                    <td><input type="password" name="contrasenya" value="" /></td>
                </tr>
            </table>
            <input type="submit" value="Enviar" />
            <a href="RegistroServlet"><input type="button" value="Registrarse"/></a>
        </form>
    </body>
</html>
