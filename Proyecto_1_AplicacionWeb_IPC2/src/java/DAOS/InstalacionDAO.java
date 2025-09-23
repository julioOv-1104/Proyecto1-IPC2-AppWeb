package DAOS;

import Entidades.*;
import Paquete_Servlets.ConexionBD;
import java.util.*;
import java.sql.*;

public class InstalacionDAO {

    public void registrarInstalacion(Instalacion nuevaInstalacion) {

        String sql = "INSERT INTO instalacion (nombre) VALUES (?)";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevaInstalacion.getNombreInstalacion());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR INSTALACION");
            e.printStackTrace();
        }

    }
    
    public boolean buscarSalon(Instalacion nuevaInstalacion) {

        String consulta = "SELECT 1 FROM instalacion WHERE nombre = ?";
        Connection conn = ConexionBD.getInstancia().getConexionbd();
        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setString(1, nuevaInstalacion.getNombreInstalacion());

            System.out.println("SQL = "+ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("ESTA INSTITUCION YA EXISTE");
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR INSTITUCION EXISTENTE");
            e.printStackTrace();
        }
        return false;
    }
    
     public List<Instalacion> obtenerInstalaciones() {
        List<Instalacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM instalacion";

        Connection conn = ConexionBD.getInstancia().getConexionbd();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String congreso = rs.getString("codigo_congreso");

                Instalacion nuevaInsta = new Instalacion();
                nuevaInsta.setNombreInstalacion(nombre);
                nuevaInsta.setCodigoCongreso(congreso);
                lista.add(nuevaInsta);

            }

        } catch (Exception e) {
            System.out.println("ERROR AL ENLISTAR LAS INTALACIONES");
            e.printStackTrace();
        }
        return lista;
    }

}
