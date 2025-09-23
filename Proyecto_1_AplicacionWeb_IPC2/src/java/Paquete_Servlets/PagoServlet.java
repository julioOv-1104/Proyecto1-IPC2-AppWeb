package Paquete_Servlets;

import DAOS.*;
import Entidades.*;
import Entidades.Pago;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/PagoServlet")
public class PagoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PagoDAO Dao = new PagoDAO();
        ParticipanteDAO partiDao = new ParticipanteDAO();
        CongresoDAO congresoDao = new CongresoDAO();
        UsuarioDAO userDao = new UsuarioDAO();

        String codigo = request.getParameter("codigo_congreso");
        LocalDate fecha = LocalDate.parse(request.getParameter("fecha"));
        String fechaTexto = request.getParameter("fecha");
        double monto = Double.parseDouble(request.getParameter("monto"));
        String montoTexto = request.getParameter("monto");
        String id = request.getParameter("id_usuario");
        EnumTipoParticipante tipo = EnumTipoParticipante.valueOf(request.getParameter("tipo_participante"));

        if (codigo.isEmpty() || fechaTexto.isEmpty() || montoTexto.isEmpty() || id.isEmpty()) {
            //No puede haber ningun campo vacio
            request.setAttribute("mensajeError", "Debe llenar todos los campos");
            reiniciarVista(request, response);
            return;
        }

        Pago nuevoPago = new Pago(codigo, fecha, monto, id);
        
        if (!Dao.buscarPorParametro("pago", "codigo_congreso", codigo)) {//verifica si ya pagó para que no pague otra vez
            //error ya pagó
            request.setAttribute("mensajeError", "Ya está inscrito a este congreso");
            reiniciarVista(request, response);
            return;
        }

        if (!Dao.buscarPorParametro("congreso", "codigo_congreso", codigo)) {//verifica que el congreso exista
            //error porque no existe
            request.setAttribute("mensajeError", "El congreso no existe");
            reiniciarVista(request, response);
            return;
        }

        if (!Dao.buscarPorParametro("usuario", "id", id)) {//veriica que el usuario exista
            //error el usuario no existe
            request.setAttribute("mensajeError", "El usuario no existe");
            reiniciarVista(request, response);
            return;
        }

        if (!congresoDao.validarPagoSuficiente(codigo, monto)) {//verifica que el monto sea igual al precio del congreso
            //error monto != precio
            request.setAttribute("mensajeError", "El monto ingresado no es correcto");
            reiniciarVista(request, response);
            return;
        }

        if (!userDao.comprobarMonederoSuficiente(id, monto)) {//verifica que tenga dinero para pagar
            //error no tiene dinero suficiente
            request.setAttribute("mensajeError", "No tiene dinero suficiente en el mondero");
            reiniciarVista(request, response);
            return;
        }

        if (!congresoDao.comprobarFechacongresoPago(codigo, fecha)) {
            //error las fechas no son correctas
            request.setAttribute("mensajeError", "Las fechas no son correctas");
            reiniciarVista(request, response);
            return;
        }

        String nombre = userDao.obtenerNombreUsuario();//Ya que el usuario existe, se crea un participante
        Participante nuevoParticipante = new Participante(nombre, id, tipo);

        Dao.pagarInscripcion(nuevoPago);//se registra el pago en la BD
        partiDao.RegistrarParticipante(nuevoParticipante);//se crea un nuevo participante
        userDao.cobrarPago(monto, id);//se le resta el dinero al usuario
        request.setAttribute("mensajeExito", "Pago de inscripcion realizado con exito");
        reiniciarVista(request, response);
        return;
    }

    private void reiniciarVista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        VistaParticipanteServlet vista = new VistaParticipanteServlet();
        vista.mostrarVistaParticipante(request, response);

    }

}
