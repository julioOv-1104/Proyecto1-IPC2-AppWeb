package DAOS;

import Entidades.*;
import Paquete_Servlets.ConexionBD;
import java.util.*;
import java.sql.*;

public class SalonDAO {

    public void registrarSalon(Salon nuevoSalon) {

        String sql = "INSERT INTO salones (nombre_salon,instalacion) VALUES (?,?)";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevoSalon.getNombreSalon());
            ps.setString(2, nuevoSalon.getNombreInstalacion());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR SALON");
            e.printStackTrace();
        }

    }

    public boolean buscarSalon(Salon nuevoSalon) {

        String consulta = "SELECT 1 FROM salones WHERE nombre_salon = ?";
        Connection conn = ConexionBD.getInstancia().getConexionbd();
        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setString(1, nuevoSalon.getNombreSalon());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("ESTE SALON YA EXISTE");
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR SALON EXISTENTE");
            e.printStackTrace();
        }
        return false;
    }

    public boolean existeInstalacion(String instalacion) {

        String consulta = "SELECT 1 FROM instalacion WHERE nombre = ?";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setString(1, instalacion);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("La instalacion SI existe");
                return true;
            } else {
                System.out.println("La instalacion NO existe");
                return false;
            }

        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR INSTALACION");
            e.printStackTrace();
        }

        return false;
    }

    public List<Salon> obtenerSalones() {
        List<Salon> lista = new ArrayList<>();
        String sql = "SELECT * FROM salones";

        Connection conn = ConexionBD.getInstancia().getConexionbd();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombreSalon = rs.getString("nombre_salon");
                String instalacion = rs.getString("instalacion");
                String codigoA = rs.getString("codigo_actividad");

                Salon nuevoSalon = new Salon(nombreSalon, instalacion, codigoA);
                lista.add(nuevoSalon);

            }

        } catch (Exception e) {
            System.out.println("ERROR AL ENLISTAR LOS SALONES");
            e.printStackTrace();
        }
        return lista;
    }

    public boolean buscarSalonVacio(String nombre) {//busca que exista el salon y que no tenga una actividad asignada

        String consulta = "SELECT 1 FROM salones WHERE nombre_salon = ? AND codigo_actividad IS NULL";

        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setString(1, nombre);
            System.out.println("SQL ejecutado "+ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("El salon SI existe y está vacio");
                return true;
            } else {
                System.out.println("El salon NO existe o NO está vacio");
                return false;
            }

        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR SALON VACIO");
            e.printStackTrace();
        }
        return false;
    }

}
