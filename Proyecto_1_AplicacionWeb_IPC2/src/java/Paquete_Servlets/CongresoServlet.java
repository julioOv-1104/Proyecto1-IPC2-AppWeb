package Paquete_Servlets;

import DAOS.*;
import Entidades.Congreso;
import Entidades.Institucion;
import Entidades.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/CongresoServlet")
public class CongresoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        CongresoDAO congresoDao = new CongresoDAO();

        String codigoCongreso = request.getParameter("codigo_congreso");
        String fechaInicio = request.getParameter("fecha_inicio");
        String precio = request.getParameter("precio");
        String institucion = request.getParameter("institucion");
        String instalacion = request.getParameter("instalacion");
        
        double datoPrecio = Double.parseDouble(precio);
        LocalDate fecha = LocalDate.parse(fechaInicio);

        if (codigoCongreso.isEmpty() || fechaInicio.isEmpty() || precio.isEmpty() || instalacion.isEmpty() || institucion.isEmpty()) {
            request.setAttribute("mensajeError", "Debe llenar todos los campos");
            request.getRequestDispatcher("VistaAdminCongreso.jsp").forward(request, response);
            return;
        }

        Congreso nuevoCongreso = new Congreso(codigoCongreso, fecha, datoPrecio, institucion, instalacion);

        if (!usuarioDao.existeInstitucion(institucion)) {
            //Si la institucion SI existe
            request.setAttribute("mensajeError", "La institucion no está registrada");
            request.getRequestDispatcher("VistaAdminCongreso.jsp").forward(request, response);
            return;

        }
        if (!congresoDao.buscarInstalacion(instalacion)) {
            //Si la instalacion SI existe
            request.setAttribute("mensajeError", "La instalacion no está registrada");
            request.getRequestDispatcher("VistaAdminCongreso.jsp").forward(request, response);
            return;
        }
        if (congresoDao.buscarCongreso(codigoCongreso)) {
            //Si YA existe el congreso
            request.setAttribute("mensajeError", "Ya existe este congreso");
            request.getRequestDispatcher("VistaAdminCongreso.jsp").forward(request, response);
            return;
        }

        congresoDao.registrarCongreso(nuevoCongreso);
        request.setAttribute("mensajeExito", "Congreso registrado con exito");
        request.getRequestDispatcher("VistaAdminCongreso.jsp").forward(request, response);
        return;
    }
    
    

}
