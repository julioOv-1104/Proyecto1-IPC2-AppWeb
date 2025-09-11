package Paquete_Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/CrearUsuarioServlet")
public class CrearUsuarioServlet extends HttpServlet {

    Connection conn;

    public CrearUsuarioServlet() {
        this.conn = ConexionBD.getInstancia().getConexionbd();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sql = "SELECT 1 FROM usuario WHERE id = ? AND password = ?;";

        String idUsuario = request.getParameter("userID");
        String contraseña = request.getParameter("userPassword");

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idUsuario);
            ps.setString(2, contraseña);
            System.out.println("SQL: " +sql);
            //ps.executeUpdate();
        } catch (Exception e) {
        }

    }

}
