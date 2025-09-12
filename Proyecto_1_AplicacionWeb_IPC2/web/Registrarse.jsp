<%-- 
    Document   : Registrarse
    Created on : 10/09/2025, 22:28:38
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="CSS/estilo.css"/><!-- se agrega el estilo -->
        <style>

            form{
                background: white;
                padding: 5px 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                height: 550px;
            }
        </style>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear usuario</title>
    </head>
    <body>
        <form action="CrearUsuarioServlet" method="post">
            <h4>Ingrese la informacion para crear un usuario</h4>
            <label>ID</label>
            <input type="text" name="IDcreado">

            <label>Institucion</label>
            <input type="text" name="Institucion">

            <label>Nombre</label>
            <input type="text" name="Nombre">

            <label>Numero de telefono</label>
            <input type="text" name="telefono">

            <label>Correo electronico</label>
            <input type="text" name="Email">

            <label>URL de foto</label>
            <input type="text" name="FOTO">

            <label>Contraseña</label>
            <input type="text" name="Contraseña">

            <label>Tipo de usuario</label>
            <select name="tipoUsuario">
                <option value="ADMIN_SISTEMA">Administrador del sistema</option>
                <option value="ADMIN_CONGRESO">Administrador de congresos</option>
                <option value="PARTICIPANTE">Participante</option>
            </select><br>
            
            <button>Registrar</button>
        </form>
    </body>
</html>
