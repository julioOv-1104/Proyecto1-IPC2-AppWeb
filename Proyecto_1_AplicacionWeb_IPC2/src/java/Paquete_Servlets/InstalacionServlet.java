package Paquete_Servlets;

import DAOS.*;
import Entidades.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/InstalacionServlet")
public class InstalacionServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InstalacionDAO inst = new InstalacionDAO();

        String nombre = request.getParameter("nombre");

        Instalacion nuevaInstalacion = new Instalacion();
        nuevaInstalacion.setNombreInstalacion(nombre);

        if (nombre.isEmpty()) {
            //error
            request.setAttribute("mensajeError", "Debe llenar el campo");
            mostrarVista(request, response);
            return;
        }

        if (inst.buscarSalon(nuevaInstalacion)) {//Si la instalacion ya existe
            //error
            request.setAttribute("mensajeError", "La instalacion ya existe");
            mostrarVista(request, response);
            return;
        }

        inst.registrarInstalacion(nuevaInstalacion);
        request.setAttribute("mensajeExito", "Instalacion registrada");
        mostrarVista(request, response);
    }

    private void mostrarVista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AdminSistemaServlet admin = new AdminSistemaServlet();
        admin.mostrarInformacion(request, response);
    }

}
