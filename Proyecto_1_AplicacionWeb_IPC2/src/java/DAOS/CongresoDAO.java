package DAOS;

import Entidades.Congreso;
import java.sql.*;
import Paquete_Servlets.*;
import java.time.LocalDate;
import java.util.*;

public class CongresoDAO {

    public void registrarCongreso(Congreso nuevoCongreso) {
        String sql = "INSERT INTO congreso (codigo_congreso, fecha_inicio, precio, institucion, instalacion) VALUES(?,?,?,?,?)";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevoCongreso.getCodigoCongreso());
            ps.setDate(2, java.sql.Date.valueOf(nuevoCongreso.getFechaInicio()));
            ps.setDouble(3, nuevoCongreso.getPrecio());
            ps.setString(4, nuevoCongreso.getInstitucion());
            ps.setString(5, nuevoCongreso.getInstalacion());

            System.out.println("SQL ejecutado " + ps);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR CONGRESO");
            e.printStackTrace();
        }
    }

    public boolean buscarInstalacion(String nombreInstalacion) {

        String consulta = "SELECT 1 FROM instalacion WHERE nombre = ?";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setString(1, nombreInstalacion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("La instalacion SI existe");
                return true;
            } else {
                System.out.println("La instalacion NO existe");
            }
        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR INSTALACION");
            e.printStackTrace();
        }
        return false;
    }

    public boolean buscarCongreso(String codigoCongreso) {

        String consulta = "SELECT 1 FROM congreso WHERE codigo_congreso = ?";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setString(1, codigoCongreso);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("El congreso YA existe");
                return true;
            } else {
                System.out.println("El congreso NO existe");
            }
        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR CONGRESO");
            e.printStackTrace();
        }
        return false;
    }

    public List<Congreso> obtenerCongresos() {
        List<Congreso> lista = new ArrayList<>();
        String sql = "SELECT * FROM congreso";

        Connection conn = ConexionBD.getInstancia().getConexionbd();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString("codigo_congreso");
                String fechaInicio = rs.getString("fecha_inicio");
                String precio = rs.getString("precio");
                String institucion = rs.getString("institucion");
                String instalacion = rs.getString("instalacion");

                double datoPrecio = Double.parseDouble(precio);
                LocalDate fecha = LocalDate.parse(fechaInicio);

                Congreso nuevo = new Congreso(codigo, fecha, datoPrecio, institucion, instalacion);
                lista.add(nuevo);
            }

        } catch (Exception e) {
            System.out.println("ERROR AL ENLISTAR LOS CONGRESOS");
        }
        return lista;
    }

    public boolean validarPagoSuficiente(String codigoCongreso, double monto) {//monto es lo que el usuario está pagando

        String consulta = "SELECT precio FROM congreso WHERE codigo_congreso = ?";
        Connection conn = ConexionBD.getInstancia().getConexionbd();
        double precio = 0;

        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setString(1, codigoCongreso);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                precio = rs.getDouble("precio");
                System.out.println("El precio del congreso es " + precio);
            }

            if (monto == precio) {//si es mayor o menor no deja pagar
                System.out.println("EL MONTO ES SUFICIENTE");
                return true;
            }

        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR CONGRESO");
            e.printStackTrace();
        }
        return false;

    }

    public boolean comprobarFechacongresoPago(String codigoCongreso, LocalDate fechaIngresada) {

        String consulta = "SELECT fecha_inicio FROM congreso WHERE codigo_congreso = ?";
        Connection conn = ConexionBD.getInstancia().getConexionbd();
        LocalDate fechaCongreso = LocalDate.MIN;

        try {
            PreparedStatement ps = conn.prepareStatement(consulta);
            ps.setString(1, codigoCongreso);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                fechaCongreso = LocalDate.parse(rs.getString("fecha_inicio"));
                System.out.println("La fecha del congreso es " + fechaCongreso);

                if (!fechaIngresada.isAfter(fechaCongreso)) {//si la fecha de pago es antes que termine el congreso
                    System.out.println("LA FECHA INGRESADA ES LOGICA");
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR AL COMPROBAR FECHAS DE CONGRESO");
            e.printStackTrace();
        }
        return false;
    }

}
