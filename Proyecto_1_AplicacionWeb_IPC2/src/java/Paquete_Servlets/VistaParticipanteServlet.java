
package Paquete_Servlets;

import DAOS.CongresoDAO;
import Entidades.Congreso;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/VistaParticipanteServlet")
public class VistaParticipanteServlet extends HttpServlet{
    
    public void mostrarVistaParticipante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        CongresoDAO congresoDao = new CongresoDAO();

        
        List<Congreso> congresos = congresoDao.obtenerCongresos();

        

        request.setAttribute("ListaCongresos", congresos);


        request.getRequestDispatcher("VistaParticipante.jsp").forward(request, response);

    }
    
}
