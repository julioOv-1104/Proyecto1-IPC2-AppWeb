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

@WebServlet("/AdminSistemaServlet")
public class AdminSistemaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        double nuevo = Double.parseDouble(request.getParameter("porcentajeCambiado"));

        GananciaDAO gananaciaDao = new GananciaDAO();

        gananaciaDao.cambiarPorcentaje(nuevo);

        mostrarInformacion(request, response);

    }

    public void mostrarInformacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GananciaDAO gananciaDao = new GananciaDAO();
        double totalRecaudado = gananciaDao.obtenerDatosGanancia("total_recaudado");//total recaudado
        double porcentaje = gananciaDao.obtenerDatosGanancia("porcentaje_comision");//Porcentaje de comision
        double ganancia = gananciaDao.obtenerDatosGanancia("total_ganancia");//Ganancia total 

        request.setAttribute("totalRecaudado", totalRecaudado);
        request.setAttribute("porcentaje", porcentaje * 100);
        request.setAttribute("ganancia", ganancia);

        InstitucionDAO institucionDAO = new InstitucionDAO();
        List<Institucion> instituciones = institucionDAO.obtenerInstituciones();
        
        UsuarioDAO usuario = new UsuarioDAO();
        List<Usuario> usuariosRegistrados = usuario.obtenerTodosUsuarios();

        request.setAttribute("listaInstituciones", instituciones);
        request.setAttribute("ListaUsuarios", usuariosRegistrados);

        request.getRequestDispatcher("VistaAdminSistema.jsp").forward(request, response);

        /*PagoDAO pagoDao = new PagoDAO();

        double totalRecaudado = pagoDao.obtenerPagos();
        double porcentaje = 0.10;//Porcentaje de comision
        double ganancia = totalRecaudado * porcentaje;//Ganancia total 

        request.setAttribute("totalRecaudado", totalRecaudado);
        request.setAttribute("porcentaje", porcentaje*100);
        request.setAttribute("ganancia", ganancia);
        
        request.getRequestDispatcher("VistaAdminSistema.jsp").forward(request, response);*/
    }

}
