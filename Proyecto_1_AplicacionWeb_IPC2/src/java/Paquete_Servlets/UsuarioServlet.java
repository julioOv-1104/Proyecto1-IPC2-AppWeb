package Paquete_Servlets;

import DAOS.UsuarioDAO;
import Entidades.TipoUsuarios;
import Entidades.Usuario;
import java.util.Base64;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    private double monederoUsuarios = 0.0;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDAO usuarioDao = new UsuarioDAO();

        String IDcreado = request.getParameter("IDcreado");
        String Institucion = request.getParameter("Institucion");
        String Nombre = request.getParameter("Nombre");
        String telefono = request.getParameter("telefono");
        String Email = request.getParameter("Email");
        String Foto = request.getParameter("FOTO");
        String Contrasenna = request.getParameter("Contraseña");
        String tipoDeUsuario = request.getParameter("tipoUsuario");

        if (IDcreado.isBlank() || Nombre.isBlank() || telefono.isBlank() || Email.isBlank() || Contrasenna.isBlank()) {
            request.setAttribute("mensajeError", "Debe llenar todos los campos obligatorios");
            request.getRequestDispatcher("Registrarse.jsp").forward(request, response);
            return;
        }
        
        
        //se codifica la contraseña
        String contrasennaCodificada = Base64.getEncoder().encodeToString(Contrasenna.getBytes());
        System.out.println("Contraseña codificada: " + contrasennaCodificada);
        TipoUsuarios tipo = decidirTipo(tipoDeUsuario);//elige el tipo de usuario

        //Crea un nuevo usuario con los datos obtenidos
        Usuario nuevoUsuario = new Usuario(IDcreado, Nombre, telefono, Email, monederoUsuarios, tipo, contrasennaCodificada);

        //en dado caso que haya adjuntado estos campos opcionales
        if (!Institucion.isBlank()) {
            nuevoUsuario.setInstitucion(Institucion);

        } else {
            nuevoUsuario.setInstitucion(null);
        }

        if (!Foto.isBlank()) {
            nuevoUsuario.setFoto(Foto);
        } else {
            nuevoUsuario.setFoto(null);
        }

        if (nuevoUsuario.getInstitucion() != null) {
            if (usuarioDao.existeInstitucion(nuevoUsuario)) {//revisa si la institucion existe

            } else {
                //si la institucion no existe

                request.setAttribute("mensajeError", "La institucion ingresada no está registrada.");
                request.getRequestDispatcher("Registrarse.jsp").forward(request, response);
                return;
            }
        }

        if (!usuarioDao.existeID(nuevoUsuario)) {//revisa que el id no este en uso
            usuarioDao.insertarUsuario(nuevoUsuario);
        } else {

            request.setAttribute("mensajeError", "Este ID ya está siendo usado");
            request.getRequestDispatcher("Registrarse.jsp").forward(request, response);
            return;
        }

    }

    private TipoUsuarios decidirTipo(String tipoUsuario) {
        TipoUsuarios tipo = TipoUsuarios.ADMIN_SISTEMA;

        if (tipoUsuario.equalsIgnoreCase("ADMIN_SISTEMA")) {
            tipo = TipoUsuarios.ADMIN_SISTEMA;
        } else if (tipoUsuario.equalsIgnoreCase("ADMIN_CONGRESO")) {
            tipo = TipoUsuarios.ADMIN_CONGRESO;
        } else if (tipoUsuario.equalsIgnoreCase("PARTICIPANTE")) {
            tipo = TipoUsuarios.PARTICIPANTE;
        }

        return tipo;
    }

    public double getMonederoUsuarios() {
        return monederoUsuarios;
    }

    public void setMonederoUsuarios(double monederoUsuarios) {
        this.monederoUsuarios = monederoUsuarios;
    }

}
