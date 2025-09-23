package DAOS;

import Entidades.*;
import java.sql.*;

import Paquete_Servlets.ConexionBD;
import java.util.*;

public class UsuarioDAO extends DAO{

    public boolean existeInstitucion(String institucion) {

        String consulta = "SELECT 1 FROM institucion WHERE nombre_institucion = ?";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setString(1, institucion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("La institucion SI existe");
                return true;
            } else {
                System.out.println("La institucion NO existe");
                return false;
            }

        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR INSTITUCION");
            e.printStackTrace();
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
            System.out.println("ERROR AL INICIAR SECION");
            e.printStackTrace();
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

    public List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> usuarioRegistrados = new ArrayList<>();

        String sql = "SELECT * FROM usuario";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String inst = rs.getString("institucion");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                String tipoU = rs.getString("tipo_usuario");

                EnumTipoUsuarios tipoEnum = EnumTipoUsuarios.valueOf(tipoU);

                Usuario nuevo = new Usuario();
                nuevo.setId(id);
                nuevo.setInstitucion(inst);
                nuevo.setNombre(nombre);
                nuevo.setCorreo(correo);
                nuevo.setTipo(tipoEnum);
                usuarioRegistrados.add(nuevo);
            }

        } catch (Exception e) {
            System.out.println("ERROR AL CARGAR LOS USUARIOS EN PANTALLA");
            e.printStackTrace();
        }

        return usuarioRegistrados;
    }

    public boolean comprobarEncargado(String id) {

        String consulta = "SELECT 1 FROM usuario WHERE id = ? AND tipo_usuario = 'ADMIN_CONGRESO'";
        //erifica que exista un usuario con ese id y que sea admin de congresos

        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

            return false;

        } catch (Exception e) {
            System.out.println("ERROR AL VERIFICAR USUARIO Y ROL");
            e.printStackTrace();
        }
        return false;
    }

    public boolean comprobarMonederoSuficiente(String idUsuario, double monto) {

        String consulta = "SELECT monedero FROM usuario WHERE id = ?";
        Connection conn = ConexionBD.getInstancia().getConexionbd();
        double monedero = 0;

        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setString(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                monedero = rs.getDouble("monedero");
                System.out.println("El moneder del usuario tiene " + monedero);
            }
            if (monedero >= monto) {//si el usuario tiene dinero suficiente
                System.out.println("TIENE SUFICIENTE DINERO");
                return true;
            }

        } catch (Exception e) {
            System.out.println("ERROR AL COMPROBAR MONEDERO DE USUARIO");
            e.printStackTrace();
        }

        return false;
    }

    public void recargarCartera(double recarga, String id) {

        String sql = "UPDATE usuario SET monedero = monedero + ? WHERE id = ?";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, recarga);
            ps.setString(2, id);
            ps.executeUpdate();

            System.out.println("SQL = " + ps);

        } catch (Exception e) {
            System.out.println("ERROR AL RECARGAR EL MONEDERO");
            e.printStackTrace();
        }

    }

    public void cobrarPago(double monto, String id) {

        String sql = "UPDATE usuario SET monedero = monedero - ? WHERE id = ?";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, monto);
            ps.setString(2, id);
            ps.executeUpdate();

            System.out.println("SQL = " + ps);

        } catch (Exception e) {
            System.out.println("ERROR AL REALIZAR PAGO");
            e.printStackTrace();
        }

    }

    public String obtenerNombreUsuario() {

        String sql = "SELECT nombre FROM usuario";
        String nombreEncontrado = "";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nombreEncontrado = rs.getString("nombre");
            }

        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER PAGOS");
            e.printStackTrace();
        }
        return nombreEncontrado;
    }

}
