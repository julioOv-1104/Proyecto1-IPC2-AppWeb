package Paquete_Servlets;

import DAOS.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Base64;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idUsuario = request.getParameter("userID");
        String contraseña = request.getParameter("userPassword");

        String contraseñaCodificada = Base64.getEncoder().encodeToString(contraseña.getBytes());
        System.out.println("CONTRASEÑA CODIFICADA EN LOGIN " + contraseñaCodificada);

        UsuarioDAO usuarioDao = new UsuarioDAO();
        if (usuarioDao.iniciarSesion(idUsuario, contraseñaCodificada)) {
            //Si devuelve true es porque si existe ese usuario con esa contraseña
            autenticarTipoUsuario(idUsuario, contraseñaCodificada, request, response);

        } else {
            request.setAttribute("mensajeError", "Contraeña o ID invalido");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }

    }

    private void autenticarTipoUsuario(String idUsuario, String contraseña, HttpServletRequest request, HttpServletResponse response) {

        UsuarioDAO usuarioDao = new UsuarioDAO();
        String tipo = usuarioDao.obtenerTipoUsuario(idUsuario, contraseña);

        try {

            if (tipo.equals("ADMIN_SISTEMA")) {
                
                AdminSistemaServlet admin = new AdminSistemaServlet();
                admin.mostrarInformacion(request, response);
                //request.getRequestDispatcher("VistaAdminSistema.jsp").forward(request, response);

            } else if (tipo.equals("ADMIN_CONGRESO")) {


                 request.getRequestDispatcher("VistaAdminCongreso.jsp").forward(request, response);

            } else if (tipo.equals("PARTICIPANTE")) {
                
                 request.getRequestDispatcher("VistaParticipante.jsp").forward(request, response);

            }

        } catch (Exception e) {
            System.out.println("ERROR AL REDIRIGIR AL USUARIO");
            e.printStackTrace();
        }

    }

}
