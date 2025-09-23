<%-- 
    Document   : VistaParticipante
    Created on : 14/09/2025, 10:10:38
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
        <title>Participante</title>

        <style>

            #InscribirseForm {
                background: white;
                padding: 5px 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                height:480px;
                width: 300px;

            }

            #RecargaForm {
                background: white;
                padding: 5px 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                height:220px;
                width: 300px;

            }

        </style>

    </head>
    <body>
        <div class="row">
            <h1>VISTA_PARTICIPANTE</h1>
            <a href="Login.jsp">Salir de vista de Participante</a>
            <div class="col-7">
                <form action="PagoServlet" method="post" id="InscribirseForm">
                    <h3>Inscribete a un Congreso</h3><br>
                    <label>Codigo del congreso</label>
                    <input type="text" name="codigo_congreso" placeholder="Obligatorio" required>

                    <label>ID usuario</label>
                    <input type="text" name="id_usuario" placeholder="Obligatorio" required>

                    <label>Monto a pagar</label>
                    <input type="number" name="monto" placeholder="Obligatorio" required>

                    <label>Fecha de pago</label>
                    <input type="date" name="fecha" placeholder="Obligatorio" required>

                    <label>Tipo de participacion</label>
                    <select name="tipo_participante">
                        <option value="PONENTE">Ponente</option>
                        <option value="TALLERISTA">Tallerista</option>
                        <option value="ASITENTE">Asistente</option>
                    </select>


                    <button type="submit">Inscribirse</button>

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
                <h3>Recargar cartera virtual</h3>
                <form action="UsuarioServlet" method="post" id="RecargaForm" >
                    <input type="hidden" name="accion" value="recargarDinero">
                    <label>ID</label>
                    <input type="text" name="id" required>

                    <label>Monto a Acreditar</label>
                    <input type="number" name="recarga"required >

                    <button type="submit">Acreditar</button>
                </form>
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
