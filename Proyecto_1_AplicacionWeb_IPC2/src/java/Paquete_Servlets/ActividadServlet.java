package Paquete_Servlets;

import DAOS.*;
import Entidades.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.*;

@WebServlet("/ActividadServlet")
public class ActividadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ActividadDAO actividadDao = new ActividadDAO();
        UsuarioDAO usuarioDao = new UsuarioDAO();
        CongresoDAO congresoDao = new CongresoDAO();
        SalonDAO salonDao = new SalonDAO();

        String id = request.getParameter("id_encargado");
        String descripcion = request.getParameter("descripcion");
        String horaIni = request.getParameter("hora_inicio");
        String horaF = request.getParameter("hora_fin");
        String nombre = request.getParameter("nombre_actividad");
        String codigoAct = request.getParameter("codigo_actividad");
        String codigoCong = request.getParameter("codigo_congreso");
        String tipo = request.getParameter("tipo");
        String salon = request.getParameter("salon");

        
        
        LocalTime horaInicio = LocalTime.parse(horaIni);
        LocalTime horaFin = LocalTime.parse(horaF);
        EnumTipoActvidad tipoAct = EnumTipoActvidad.valueOf(tipo);
        
        System.out.println("hora inicio "+horaInicio);
        System.out.println("hora fin "+horaFin);
        
        

        if (id.isEmpty() || descripcion.isEmpty() || horaIni.isEmpty() || horaF.isEmpty() || nombre.isEmpty() || codigoAct.isEmpty()
                || codigoCong.isEmpty() || tipo.isEmpty() || salon.isEmpty()) {
            //mensaje de error por no llenar todos los campos
            request.setAttribute("mensajeError", "Debe llenar todos los campos");
            reiniciarVista(request, response);
            return;
        }
        
        if (horaInicio.isAfter(horaFin) || horaInicio.equals(horaFin)) {
            System.out.println("La hora de inicio es despues o igual a la hora fin");
            request.setAttribute("mensajeError", "Horas inicio y fin incongruentes");
            reiniciarVista(request, response);
            return;
        }

        Actividad nuevaActividad = new Actividad(id, descripcion, horaInicio, horaFin, nombre, codigoAct, codigoCong, tipoAct, salon);

        if (!actividadDao.existeActividad(codigoAct)) {//verifica que no exista la actividad
            //error
            request.setAttribute("mensajeError", "Ya existe esta actividad");
            reiniciarVista(request, response);
            return;
        }

        if (!usuarioDao.comprobarEncargado(id)) {
            //error con comprobar si el usuario existe y es admin de congreso
            request.setAttribute("mensajeError", "Erro con la informacion de encargado");
            reiniciarVista(request, response);
            return;
        }

        if (!congresoDao.buscarCongreso(codigoCong)) {//verifica que el congreso si exista
            //error
            request.setAttribute("mensajeError", "El congreso no está registrado");
            reiniciarVista(request, response);
            return;
        }

        if (!salonDao.buscarSalonVacio(salon)) { //Verifica que el salon esxista y esté vacio
            //error
            request.setAttribute("mensajeError", "El salon no existe o ya está ocupado");
            reiniciarVista(request, response);
            return;
        }

        actividadDao.registrarActividades(nuevaActividad);
        request.setAttribute("mensajeExito", "Actividad registrada con exito");
        reiniciarVista(request, response);
    }

    private void reiniciarVista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdminCongresoServlet admin = new AdminCongresoServlet();
        admin.mostrarInformacion(request, response);
    }

}
