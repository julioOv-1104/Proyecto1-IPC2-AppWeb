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
        </style>

    </head>
    <body>
        <h2>VISTA DEL ADMIN_SISTEMA</h2>
        <p>Total de dinero recaudado: Q<%= request.getAttribute("totalRecaudado") %></p>
        <p>Ganancia: Q<%= request.getAttribute("ganancia") %></p>
        <p>Porcentaje aplicado: <%= request.getAttribute("porcentaje")%>%</p>

        <form action="AdminSistemaServlet" method="post">
            <label>Cambiar porcentaje de comision</label>
            <input type="number" name="porcentajeCambiado" step="any" min="0.1" max="0.5" required>
            <button type="submit">Aceptar</button><br>
        </form>


        <div>
            <h3>Instituciones Registradas</h3>
            <table>
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
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
