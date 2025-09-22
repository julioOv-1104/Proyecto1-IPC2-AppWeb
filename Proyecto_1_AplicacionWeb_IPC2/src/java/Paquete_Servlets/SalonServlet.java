package Paquete_Servlets;

import DAOS.*;
import Entidades.Salon;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SalonServlet")
public class SalonServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SalonDAO salonDao = new SalonDAO();

        String nombre = request.getParameter("nombreSalon");
        String instalacion = request.getParameter("nombreInstalacion");

        if (nombre.isBlank() || instalacion.isBlank()) {
            request.setAttribute("mensajeError", "Deve llenar todos los campos");
            reiniciarVista(request, response);
            return;
        }

        Salon nuevoSalon = new Salon(nombre, instalacion, "");

        if (!salonDao.existeInstalacion(instalacion)) {//revisa si la instalacion existe
            //si NO existe
            request.setAttribute("mensajeError", "Esta instalacion no está registrada");
            reiniciarVista(request, response);
            return;
        } else {
            //Si SI existe
            if (!salonDao.buscarSalon(nuevoSalon)) {
                //No hay ningun salon con ese nombre
                salonDao.registrarSalon(nuevoSalon);
                request.setAttribute("mensajeExito", "Salon registrado con exito");
                reiniciarVista(request, response);
                return;
            } else {
                request.setAttribute("mensajeError", "Este salon ya está registrado");
                reiniciarVista(request, response);
                return;
            }
        }

    }

    private void reiniciarVista(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        AdminCongresoServlet admin = new AdminCongresoServlet();
        admin.mostrarInformacion(request, response);
    }

}
