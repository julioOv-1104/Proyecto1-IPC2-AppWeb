
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
        List<Congreso> congresos = congresoDao.obtenerCongresos();
        

        request.setAttribute("ListaCongresos", congresos);


        request.getRequestDispatcher("VistaAdminCongreso.jsp").forward(request, response);

    }
    
}
