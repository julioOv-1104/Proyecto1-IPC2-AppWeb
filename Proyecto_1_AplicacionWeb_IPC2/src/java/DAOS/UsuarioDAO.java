package DAOS;

import Entidades.*;
import java.sql.*;

import Paquete_Servlets.ConexionBD;
import java.util.Base64;

public class UsuarioDAO {

    public boolean existeInstitucion(Usuario nuevoUsuario) {

        String consulta = "SELECT 1 FROM institucion WHERE nombre_institucion = ?";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setString(1, nuevoUsuario.getInstitucion());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("La institucion SI existe");
                return true;
            } else {
                System.out.println("La institucion NO existe");
                return false;
            }

        } catch (Exception e) {
        }

        return false;
    }

    public boolean existeID(Usuario nuevoUsuario) {

        String consulta = "SELECT 1 FROM usuario WHERE id = ?";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setString(1, nuevoUsuario.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("El ID YA existe");
                return true;
            } else {
                System.out.println("El ID NO existe");
                return false;
            }

        } catch (Exception e) {
        }

        return false;
    }

    public void insertarUsuario(Usuario nuevoUsuario) {

        String sql = "INSERT INTO usuario (id, institucion, nombre, numero_telefonico, correo, monedero, foto, tipo_usuario, password)"
                + " VALUES (?,?,?,?,?,?,?,?,?)";
        Connection conn = ConexionBD.getInstancia().getConexionbd();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevoUsuario.getId());
            ps.setString(2, nuevoUsuario.getInstitucion());
            ps.setString(3, nuevoUsuario.getNombre());
            ps.setString(4, nuevoUsuario.getNumero_telefonico());
            ps.setString(5, nuevoUsuario.getCorreo());
            ps.setDouble(6, nuevoUsuario.getMonedero());
            ps.setString(7, nuevoUsuario.getFoto());
            ps.setString(8, nuevoUsuario.getTipo().name());
            ps.setString(9, nuevoUsuario.getPassword());

            System.out.println("SQL EJECUTADO " + ps);
            int i = ps.executeUpdate();
            System.out.println("LINEAS AFECTADAS " + i);

        } catch (SQLException e) {
            System.out.println("ERROR AL REALIZAR EL INSERT " + e.getMessage());

        }

    }

    public boolean iniciarSesion(String id, String contraseña) {

        String sql = "SELECT 1 FROM usuario WHERE id = ? AND password = ?;";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("SI EXISTE EL USUARIO CON ESA CONTRASEÑA");
                return true;
            } else {
                System.out.println("NO EXISTE EL USUARIO CON ESA CONTRASEÑA");

            }

        } catch (Exception e) {
        }
        return false;
    }

    public String obtenerTipoUsuario(String idUsuario, String contraseña) {
        String tipoEncontrado = "";

        String consulta = "SELECT tipo_usuario FROM usuario WHERE id = ? AND password = ?";

        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(consulta);

            ps.setString(1, idUsuario);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tipoEncontrado = rs.getString("tipo_usuario");
                
            }

        } catch (SQLException e) {
            System.out.println("ERROR AL AUTENTICAR TIPO USUARIO");
            e.printStackTrace();
        }
        return tipoEncontrado;
    }

}
