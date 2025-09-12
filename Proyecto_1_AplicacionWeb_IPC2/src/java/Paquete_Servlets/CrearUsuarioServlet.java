package Paquete_Servlets;

import DAOS.UsuarioDAO;
import Entidades.TipoUsuarios;
import Entidades.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CrearUsuarioServlet")
public class CrearUsuarioServlet extends HttpServlet {

    private double monederoUsuarios = 0.0;


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String IDcreado = request.getParameter("IDcreado");
        String Institucion = request.getParameter("Institucion");
        String Nombre = request.getParameter("Nombre");
        String telefono = request.getParameter("telefono");
        String Email = request.getParameter("Email");
        String Foto = request.getParameter("FOTO");
        String Contraseña = request.getParameter("Contraseña");
        String tipoDeUsuario = request.getParameter("tipoUsuario");

        TipoUsuarios tipo = decidirTipo(tipoDeUsuario);//elige el tipo de usuario

        //Crea un nuevo usuario con los datos obtenidos
        Usuario nuevoUsuario = new Usuario(IDcreado, Nombre, telefono, Email, monederoUsuarios, tipo, Contraseña);

        //en dado caso que haya adjuntado estos campos opcionales
        nuevoUsuario.setInstitucion(Institucion);
        nuevoUsuario.setFoto(Foto);

        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuarioDao.insertarUsuario(nuevoUsuario);//Inserta el nuevo usuario en la base de datos

    }

    private TipoUsuarios decidirTipo(String tipoUsuario) {
        TipoUsuarios tipo = TipoUsuarios.ADMIN_SISTEMA;

        switch (tipoUsuario) {
            case "ADMIN_SISTEMA":
                tipo = TipoUsuarios.ADMIN_SISTEMA;
            case "ADMIN_CONGRESO":
                tipo = TipoUsuarios.ADMIN_CONGRESO;
            case "PARTICIPANTE":
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
