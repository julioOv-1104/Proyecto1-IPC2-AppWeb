<%-- 
    Document   : Login
    Created on : 10/09/2025, 20:59:10
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="CSS/estilo.css"/><!-- se agrega el estilo -->
        <title>Login Sistema congresos</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <style>

            form{
                background: white;
                padding: 5px 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                height: 250px;
            }
        </style>
        
    </head>
    <body>
        <form action="LoginServlet" method="post" id="loginForm">
            <h2>Inciar sesion</h2>

            <label>ID usuario</label>
            <input type="text" name="userID" required>

            <label>Contraseña</label>
            <input type="text" name="userPassword" required>

            <button type="submit">Aceptar</button><br>
            <a href="Registrarse.jsp">¿No tienes un usuario? Registrarse</a>


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
