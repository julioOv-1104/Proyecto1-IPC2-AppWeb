<%-- 
    Document   : Registrarse
    Created on : 10/09/2025, 22:28:38
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="CSS/estilo.css"/><!-- se agrega el estilo -->
        <style>

            form{
                background: white;
                padding: 5px 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                height: 700px;
            }
        </style>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear usuario</title>

    </head>
    <body>

        <h1>Registrarse en sistema de congresos Code 'n Bugs</h1>


        <form action="UsuarioServlet" method="post">
            <h4>Ingrese la informacion para crear un usuario</h4>
            <label>ID</label>
            <input type="text" name="IDcreado" placeholder="Obligatorio" required>

            <label>Institucion</label>
            <input type="text" name="Institucion" placeholder="Opcional" >

            <label>Nombre</label>
            <input type="text" name="Nombre" placeholder="Obligatorio" required>

            <label>Numero de telefono</label>
            <input type="text" name="telefono" placeholder="Obligatorio" required>

            <label>Correo electronico</label>
            <input type="text" name="Email" placeholder="Obligatorio" required>

            <label>URL de foto</label>
            <input type="text" name="FOTO" placeholder="Opcional">

            <label>Contraseña</label>
            <input type="text" name="Contraseña" placeholder="Obligatorio" required>

            <label>Tipo de usuario</label>
            <select name="tipoUsuario">
                <option value="PARTICIPANTE">Participante</option>
                <option value="ADMIN_CONGRESO">Administrador de congresos</option>
                <option value="ADMIN_SISTEMA">Administrador del sistemacipante</option>
            </select>

            <button>Registrar</button><br>
            <a href="Login.jsp">Iniciar Sesion</a>



        </form>



        <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Encabezado rojo con el título -->
                    <div class="modal-header bg-danger text-white">
                        <h5 class="modal-title" id="errorModalLabel">Error</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>

                    <!-- Se muestra el mensaje desde el Servlet -->
                    <div class="modal-body">
                        <%= request.getAttribute("mensajeError") %>
                    </div>

                    <!-- Botón para cerrar -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>

                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

        <% if (request.getAttribute("mensajeError") != null) { %>
        <script>
            var myModal = new bootstrap.Modal(document.getElementById('errorModal'));
            myModal.show();
        </script>
        <% } %>

    </body>
</html>
