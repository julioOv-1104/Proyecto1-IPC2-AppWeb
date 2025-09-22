package DAOS;

import Entidades.*;
import Paquete_Servlets.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActividadDAO {

    public void registrarActividades(Actividad nuevaActividad) {

        String sql = "INSERT INTO actividad (id_encargado, descripcion, hora_inicio, hora_fin, nombre_actividad, "
                + "codigo_actividad, codigo_congreso, tipo, salon) VALUES (?,?,?,?,?,?,?,?,?)";

        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevaActividad.getIdEncargado());
            ps.setString(2, nuevaActividad.getDescripcion());
            ps.setTime(3, java.sql.Time.valueOf(nuevaActividad.getHoraInicio()));
            ps.setTime(4, java.sql.Time.valueOf(nuevaActividad.getHoraFin()));
            ps.setString(5, nuevaActividad.getNombreActividad());
            ps.setString(6, nuevaActividad.getCodigoActividad());
            ps.setString(7, nuevaActividad.getCodigoCongreso());
            ps.setString(8, nuevaActividad.getTipo().name());
            ps.setString(9, nuevaActividad.getSalon());

            System.out.println("SQL ejecutado " + ps);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("ERROR AL REGISTRAR ACTIVIDAD");
            e.printStackTrace();
        }

    }

    public boolean existeActividad(String codigo) {

        String consulta = "SELECT 1 FROM actividad WHERE codigo_actividad = ?";

        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareCall(consulta);
            ps.setString(1, codigo);

            System.out.println("SQL ejecutado " +ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("La activiad YA existe");
                
                return false;
            }

            System.out.println("La actividad NO exite");
            return true;

        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR ACTIVIDAD");
            e.printStackTrace();
        }
        return true;
    }

    
    public List<Actividad> obtenerActividades() {
        List<Actividad> lista = new ArrayList<>();
        String sql = "SELECT * FROM actividad";

        Connection conn = ConexionBD.getInstancia().getConexionbd();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id_encargado");
                String nombre = rs.getString("nombre_actividad");
                String codigoA = rs.getString("codigo_actividad");
                String codigoC = rs.getString("codigo_congreso");
                String salon = rs.getString("salon");

                Actividad nuevaActividad = new Actividad();
                nuevaActividad.setIdEncargado(id);
                nuevaActividad.setNombreActividad(nombre);
                nuevaActividad.setCodigoActividad(codigoA);
                nuevaActividad.setCodigoCongreso(codigoC);
                nuevaActividad.setSalon(salon);
                lista.add(nuevaActividad);

            }

        } catch (Exception e) {
            System.out.println("ERROR AL ENLISTAR LAS ACTIVIDADES");
            e.printStackTrace();
        }
        return lista;
    }
    
}
