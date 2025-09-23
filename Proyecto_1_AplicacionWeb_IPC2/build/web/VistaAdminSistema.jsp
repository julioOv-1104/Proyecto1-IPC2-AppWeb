<%-- 
    Document   : VistaAdminSistema
    Created on : 14/09/2025, 10:09:09
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="Entidades.*" %>
<!DOCTYPE html>
<html>
    <head>


        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="CSS/estilo.css"/><!-- se agrega el estilo -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Configuracion Sistema</title>

        <style>

            #PorcentajeForm{
                background: white;
                padding: 5px 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                height: 140px;
                width: 350px;

            }

            #AdminCongresosForm{
                background: white;
                padding: 5px 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                height: 730px;
                width: 300px;

            }

            body{
                min-height: 100vh; /* Ocupa el 100% de la altura de la ventana */
                margin: 0;
            }
        </style>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    </head>
    <body>
        <div class="row">
            <div class="col-7"> 
                <h1>VISTA DEL ADMIN_SISTEMA</h1>
                <a href="Login.jsp">Salir de la vista del administrador del sistema</a>
                <p>Total de dinero recaudado: Q<%= request.getAttribute("totalRecaudado") %></p>
                <p>Ganancia: Q<%= request.getAttribute("ganancia") %></p>
                <p>Porcentaje aplicado: <%= request.getAttribute("porcentaje")%>%</p>
            </div>

            <div class="col-7">
                <form action="AdminSistemaServlet" method="post" id="PorcentajeForm">
                    <label>Cambiar porcentaje de comision</label>
                    <input type="number" name="porcentajeCambiado" step="any" min="0.1" max="0.5" required>
                    <button type="submit">Aceptar</button><br>
                </form>
            </div>
            <div class="col-7">
                <h3>Instituciones Registradas</h3>
                <table class="table table-bordered border-primary">
                    <thead>
                        <tr>
                            <th>Codigo de congreso</th>
                            <th>ID del participante</th>
                            <th>Nombre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        java.util.List<Institucion> instituciones = (java.util.List<Institucion>) request.getAttribute("listaInstituciones");
                    
                        if (instituciones != null){
                        
                            for(Institucion inst: instituciones){
                        %>
                        <tr>
                            <td><%= inst.getCodigo_congreso() %></td>
                            <td><%= inst.getId_administrador() %></td>
                            <td><%= inst.getNombre_institucion() %></td>
                        </tr>
                        <%
                            }
                           }
                        %>
                    </tbody>
                </table>
                    
                    
                    <h3>Instalaciones Registradas</h3>
                <table class="table table-bordered border-primary">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Congreso</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        java.util.List<Instalacion> instalaciones = (java.util.List<Instalacion>) request.getAttribute("ListaInstalaciones");
                    
                        if (instalaciones != null){
                        
                            for(Instalacion insta: instalaciones){
                        %>
                        <tr>
                            <td><%= insta.getNombreInstalacion() %></td>
                            <td><%= insta.getCodigoCongreso() %></td>
                        </tr>
                        <%
                            }
                           }
                        %>
                    </tbody>
                </table>

                <h3>Usuarios Registrados</h3>
                <table class="table table-bordered border-primary">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Institucion</th>
                            <th>Nombre</th>
                            <th>Correo</th>
                            <th>Tipo de usuario</th>
                        </tr>
                    </thead>

                    <tbody>
                        <%
                        java.util.List<Usuario> usuariosR = (java.util.List<Usuario>) request.getAttribute("ListaUsuarios");
                    
                        if (usuariosR != null){
                        
                            for(Usuario u: usuariosR){
                        %>
                        <tr>
                            <td><%= u.getId() %></td>
                            <td><%= u.getInstitucion() %></td>
                            <td><%= u.getNombre() %></td>
                            <td><%= u.getCorreo() %></td>
                            <td><%= u.getTipo()%></td>
                        </tr>
                        <%
                            }
                           }
                        %>

                    </tbody>

                </table>
            </div>

            <div class="col-7">

                <form action="UsuarioServlet" method="post" id="AdminCongresosForm">
                    <input type="hidden" name="accion" value="CrearAdminCongreso">
                    <h3>Registrar Administardor de congresos</h3>
                    <label>ID</label><br>

                    <input type="text" name="IDcreado" placeholder="Obligatorio" required><br>

                    <label>Institucion</label><br>

                    <input type="text" name="Institucion" placeholder="Opcional" ><br>


                    <label>Nombre</label><br>

                    <input type="text" name="Nombre" placeholder="Obligatorio" required><br>


                    <label>Numero de telefono</label><br>
                    <input type="text" name="telefono" placeholder="Obligatorio" required><br>


                    <label>Correo electronico</label><br>

                    <input type="text" name="Email" placeholder="Obligatorio" required><br>


                    <label>URL de foto</label><br>

                    <input type="text" name="FOTO" placeholder="Opcional"><br>


                    <label>Contraseña</label><br>

                    <input type="password" name="Contraseña" placeholder="Obligatorio" required><br>


                    <label>Tipo de usuario</label><br>

                    <select name="tipoUsuario">
                        <option value="ADMIN_CONGRESO">Administrador de congresos</option>
                    </select>

                    <button type="submit">Registrar</button>
                </form>

            </div>
        </div>

        <div>
            <form action="InstalacionServlet" method="post">

                <h3>Registrar Instalaciones</h3>
                <label>Nombre</label>
                <input type="text" name="nombre" placeholder="Obligatorio" required>
                <button type="submit">Registrar</button>

            </form>
        </div>





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


        <div class="modal fade" id="exitoModal" tabindex="-1" aria-labelledby="exitoModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Encabezado verde -->
                    <div class="modal-header bg-success text-white">
                        <h5 class="modal-title" id="exitoModalLabel">Éxito</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                    </div>

                    <!-- Mensaje desde el Servlet -->
                    <div class="modal-body">
                        <%= request.getAttribute("mensajeExito") %>
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
        <% } else if(request.getAttribute("mensajeExito") != null){ %>
        <script>
            var myModal = new bootstrap.Modal(document.getElementById('exitoModal'));
            myModal.show()
        </script>
        <%}%>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
