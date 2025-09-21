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
        UsuarioDAO usuarioDao = new UsuarioDAO();
        
        String nombre = request.getParameter("nombreSalon");
        String institucion = request.getParameter("nombreInstitucion");
        
        if (nombre.isBlank() || institucion.isBlank()) {
            request.setAttribute("mensajeError", "Deve llenar todos los campos");
            request.getRequestDispatcher("VistaAdminCongreso.jsp").forward(request, response);
            return;
        }
        
        Salon nuevoSalon = new Salon(nombre, institucion);
        
        if (!usuarioDao.existeInstitucion(institucion)) {//revisa si la institucion existe
            //si NO existe
            request.setAttribute("mensajeError", "Esta institucion no está registrada");
            request.getRequestDispatcher("VistaAdminCongreso.jsp").forward(request, response);
            return;
        }else{
        //Si SI existe
            if (!salonDao.buscarSalon(nuevoSalon)) {
                //No hay ningun salon con ese nombre
                salonDao.registrarSalon(nuevoSalon);
                request.setAttribute("mensajeExito", "Salon registrado con exito");
            request.getRequestDispatcher("VistaAdminCongreso.jsp").forward(request, response);
            return;
            }else{
            request.setAttribute("mensajeError", "Este salon ya está registrado");
            request.getRequestDispatcher("VistaAdminCongreso.jsp").forward(request, response);
            return;
            }
        }
        
    }
    
}
