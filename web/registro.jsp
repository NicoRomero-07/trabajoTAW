<%-- 
    Document   : Registro
    Created on : 18-abr-2022, 10:53:26
    Author     : Victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="trabajoTAW.entity.Usuario"%>
<%@page import="trabajoTAW.entity.Categoria"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
    </head>
    <body>
        <h1>Registro</h1>
        <form method="POST" action="LoginServlet">
            Usuario: <input type="text" name="nombreusuario" value="" /><br/>
            Clave: <input type="password" name="contrasenya" value="" /><br/>
            Email: <input type="email" name="email" value="" /><br/>            
            Nombre: <input type="text" name="nombre" value="" /><br/>
            Primer apellido: <input type="text" name="primerapellido" value="" /><br/>
            Segundo apellido: <input type="text" name="segundoapellido" value="" /><br/>
            Fecha de nacimiento: <input type="date" name="fechanacimiento" value="dd/mm/aaaa"
                                        min="1900-01-01" max="2022-01-01"><br/>
            Sexo:<br/> <input type="radio" id='h' name="sexo" value="H"/>
            <label for='h'>Hombre</label><br/>
            <input type="radio" id='m' name="sexo" value="M" />
            <label for='m'>Mujer</label><br/>
            <input type="radio" id='n' name="sexo" value="N" />
            <label for='n'>No binario</label><br/>
            
            Direccion: <br/><br/>
            
            <label for="tipo">Tipo:</label>
            <select name="tipo">
            <option value="casa">casa</option>
            <option value="oficina">oficina</option>
            </select><br/>
            Calle: <input type="text" name="calle" value="" /><br/>
            Numero: <input type="number" name="numero" value="" /><br/>
            Planta <input type="number" name="planta" value="" /><br/>
            Puerta <input type="text" name="puerta" value="" /><br/>
            Codigo postal <input type="number" name="codigoPostal" value="" /><br/><br/>
            
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
