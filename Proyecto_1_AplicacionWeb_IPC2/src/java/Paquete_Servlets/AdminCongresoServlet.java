
package Paquete_Servlets;

import DAOS.*;
import Entidades.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminCongresoServlet")
public class AdminCongresoServlet extends HttpServlet{
    
    public void mostrarInformacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        CongresoDAO congresoDao = new CongresoDAO();
        SalonDAO salonDao = new SalonDAO();
        ActividadDAO actividadDao = new ActividadDAO();
        
        List<Congreso> congresos = congresoDao.obtenerCongresos();
        List<Salon> salones = salonDao.obtenerSalones();
        List<Actividad> actividades = actividadDao.obtenerActividades();
        

        request.setAttribute("ListaCongresos", congresos);
        request.setAttribute("ListaSalones", salones);
        request.setAttribute("ListaActividades", actividades);


        request.getRequestDispatcher("VistaAdminCongreso.jsp").forward(request, response);

    }
    
}
