package DAOS;

import Entidades.*;
import java.sql.*;

import Paquete_Servlets.ConexionBD;

public class UsuarioDAO {


    public void insertarUsuario(Usuario nuevoUsuario) {
        
        String id = nuevoUsuario.getNombre();
        
        String consulta = "SELECT 1 FROM usuario WHERE id = ?";

        String sql = "INSERT INTO usuario (id, nombre, numero_telefonico, correo, monedero, tipo_usuario, password)"
                + " VALUES (?,?,?,?,?,?,?)";

        try (Connection conn = ConexionBD.getInstancia().getConexionbd();
              PreparedStatement ps = conn.prepareStatement(sql)){
           
            ps.setString(1, nuevoUsuario.getId());
            ps.setString(2, nuevoUsuario.getNombre());
            ps.setString(3, nuevoUsuario.getNumero_telefonico());
            ps.setString(4, nuevoUsuario.getCorreo());
            ps.setDouble(5, nuevoUsuario.getMonedero());
            ps.setString(6, nuevoUsuario.getTipo().name());
            ps.setString(7, nuevoUsuario.getPassword());
            
            System.out.println("SQL EJECUTADO " + sql);
            int i = ps.executeUpdate();
            System.out.println("LINEAS AFECTADAS " +i);

        } catch (SQLException e) {
            System.out.println("ERROR AL REALIZAR EL INSERT " + e.getMessage());

        }

    }

}
