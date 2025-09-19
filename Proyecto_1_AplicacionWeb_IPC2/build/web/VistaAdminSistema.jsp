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

            form{
                background: white;
                padding: 5px 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                height: 150px;
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
                <p>Total de dinero recaudado: Q<%= request.getAttribute("totalRecaudado") %></p>
                <p>Ganancia: Q<%= request.getAttribute("ganancia") %></p>
                <p>Porcentaje aplicado: <%= request.getAttribute("porcentaje")%>%</p>
            </div>

            <div class="col-7">
                <form action="AdminSistemaServlet" method="post">
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
                    
                    <table>
                        <tr>
                            <th>Header</th>
                            <th>Header2</th>
                        </tr>
                        <tr>
                            <td>Cell1</td>
                            <td>Cell2</td>
                        </tr>
                        <tr>
                            <td>Cell3</td>
                            <td>Cell4</td>
                        </tr>
                    </table>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
