<%-- 
    Document   : Login
    Created on : 10/09/2025, 20:59:10
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="CSS/estilo.css"/><!-- se agrega el estilo -->
        <title>Login Sistema congresos</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form id="loginForm">
            <h2>Inciar sesion</h2>

            <label>ID usuario</label>
            <input type="text" name="userID">

            <label>Contraseña</label>
            <input type="text" name="userPassword">

            <button type="submit">Aceptar</button><br>
            <a href="Registrarse.jsp">¿No tienes un usuario? Registrarse</a>


        </form>

    </body>
</html>
