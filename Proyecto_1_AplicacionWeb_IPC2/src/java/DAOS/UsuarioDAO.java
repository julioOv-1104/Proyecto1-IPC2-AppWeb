package DAOS;

import Entidades.*;
import java.sql.*;

import Paquete_Servlets.ConexionBD;

public class UsuarioDAO {

    Connection conn;

    public UsuarioDAO() {
        this.conn = ConexionBD.getInstancia().getConexionbd();
    }

    public void insertarUsuario() {

        String sql = "INSERT INTO usuario (id, nombre, numero_telefonico, correo, monedero, tipo_usuario, password)"
                + " VALUES ('69', 'julio', '12345', 'julio@algo.com', 0.0, 'ADMIN_SISTEMA', 'contrasenna')";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            System.out.println("SQL EJECUTADO " + sql);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR AL REALIZAR EL INSERT " + e.getMessage());

        }

    }

}
