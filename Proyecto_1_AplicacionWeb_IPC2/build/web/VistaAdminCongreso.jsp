<%-- 
    Document   : VistaAdminCongreso
    Created on : 14/09/2025, 10:10:01
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
        <title>Configuracion Congreso</title>

        <style>

            #SalonForm{
                background: white;
                padding: 5px 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                height: 270px;
                width: 300px;

            }

            #CongresoForm{
                background: white;
                padding: 5px 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                height: 500px;
                width: 350px;

            }

            #ActividadForm{
                background: white;
                padding: 5px 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                height: 750px;
                width: 350px;

            }

            body{
                min-height: 100vh; /* Ocupa el 100% de la altura de la ventana */
                margin: 0;
            }
        </style>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>


    </head>
    <body>
        <div class="row">

            <h1>VISTA ADMIN_CONGRESO</h1>
            <a href="Login.jsp">Salir de la vista del administrador de congresos</a>

            <div class="col-7">
                <form action="CongresoServlet" method="post" id="CongresoForm">
                    <h3>Crear nuevo Congreso</h3>
                    <label>Codigo congreso</label>
                    <input type="text" name="codigo_congreso" placeholder="Obligatorio" required>

                    <label>Fecha inicio</label>
                    <input type="date" name="fecha_inicio" placeholder="Obligatorio" required>

                    <label>Precio</label>
                    <input type="number" name="precio" placeholder="Obligatorio" required>

                    <label>Institucion</label>
                    <input type="text" name="institucion" placeholder="Obligatorio" required>

                    <label>Instalacion</label>
                    <input type="text" name="instalacion" placeholder="Obligatorio" required><br>

                    <button type="submit">Crear</button>
                </form>
            </div>


            <div class="col-7">

                <h3>Congresos Registrados</h3>
                <table class="table table-bordered border-primary">
                    <thead>
                        <tr>
                            <th>Codigo congreso</th>
                            <th>Fecha inicio</th>
                            <th>Precio</th>
                            <th>Institucion</th>
                            <th>Instalacion</th>
                        </tr>
                    </thead>

                    <tbody>
                        <%
                        java.util.List<Congreso> listaCongreso = (java.util.List<Congreso>) request.getAttribute("ListaCongresos");
                    
                        if (listaCongreso != null){
                        
                            for(Congreso c: listaCongreso){
                        %>
                        <tr>
                            <td><%= c.getCodigoCongreso() %></td>
                            <td><%= c.getFechaInicio() %></td>
                            <td><%= c.getPrecio() %></td>
                            <td><%= c.getInstitucion() %></td>
                            <td><%= c.getInstalacion() %></td>
                        </tr>
                        <%
                            }
                           }
                        %>

                    </tbody>

                </table>

            </div>


            <div class="col-7">
                <form action="SalonServlet" method="post" id="SalonForm">
                    <h3>Registrar Salones</h3>
                    <label>Nombre del salon</label>
                    <input type="text" name="nombreSalon" placeholder="Obligatorio" required>

                    <label>Instalacion a la que pertenece</label>
                    <input type="text" name="nombreInstalacion" placeholder="Obligatorio" required><br>

                    <button type="submit">Registrar</button> 

                </form>
            </div>



            <div class="col-7">

                <h3>Salones Registrados</h3>
                <table class="table table-bordered border-primary">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Instalacion</th>
                            <th>Actividad</th>

                        </tr>
                    </thead>

                    <tbody>
                        <%
                        java.util.List<Salon> listaSalon = (java.util.List<Salon>) request.getAttribute("ListaSalones");
                    
                        if (listaSalon != null){
                        
                            for(Salon s: listaSalon){
                        %>
                        <tr>
                            <td><%= s.getNombreSalon() %></td>
                            <td><%= s.getNombreInstalacion() %></td>
                            <td><%= s.getCodigoActividad() %></td>
                        </tr>
                        <%
                            }
                           }
                        %>

                    </tbody>

                </table>

            </div>

            <div class="col-7">

                <form action="ActividadServlet" method="post" id="ActividadForm">
                    <h3>Asignar Actividades</h3>
                    <label>ID del encargado</label>
                    <input type="text" name="id_encargado" placeholder="Obligatorio" required>

                    <label>Descripcion</label>
                    <input type="text" name="descripcion" placeholder="Obligatorio" required>

                    <label>Hora Inicio (Obligatorio)</label>
                    <input type="time" name="hora_inicio" required>

                    <label>Hora Fin (Obligatorio)</label>
                    <input type="time" name="hora_fin" required>

                    <label>Nombre de la actividad</label>
                    <input type="text" name="nombre_actividad" placeholder="Obligatorio" required>

                    <label>Codigo de la Actividad</label>
                    <input type="text" name="codigo_actividad" placeholder="Obligatorio" required>

                    <label>Codigo de Congreso al que pertenece</label>
                    <input type="text" name="codigo_congreso" placeholder="Obligatorio" required>

                    <label>Tipo de Actividad</label>
                    <select name="tipo">
                        <option value="PONENCIA">Ponencia</option>
                        <option value="TALLER">Taller</option>
                    </select>

                    <label>Salon Asignado</label>
                    <input type="text" name="salon" placeholder="Obligatorio" required>

                    <button type="submit">Aceptat</button>
                </form>

            </div>


            <div class="col-7">

                <h3>Actividades Registradas</h3>
                <table class="table table-bordered border-primary">
                    <thead>
                        <tr>
                            <th>ID del encargado</th>
                            <th>Nombre Actividad</th>
                            <th>Codigo Actividad</th>
                            <th>Codigo Congreso</th>
                            <th>Salon</th>

                        </tr>
                    </thead>

                    <tbody>
                        <%
                        java.util.List<Actividad> listaActividad = (java.util.List<Actividad>) request.getAttribute("ListaActividades");
                    
                        if (listaActividad != null){
                        
                            for(Actividad a: listaActividad){
                        %>
                        <tr>
                            <td><%= a.getIdEncargado() %></td>
                            <td><%= a.getNombreActividad() %></td>
                            <td><%= a.getCodigoActividad() %></td>
                            <td><%= a.getCodigoCongreso() %></td>
                            <td><%= a.getSalon() %></td>
                        </tr>
                        <%
                            }
                           }
                        %>

                    </tbody>

                </table>

            </div>

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

    </body>
</html>
