package DAOS;

import Entidades.*;
import Paquete_Servlets.*;
import java.sql.*;

public class PagoDAO extends DAO {

    public void pagarInscripcion(Pago nuevopago) {

        String sql = "INSERT INTO pago (codigo_congreso, fecha, monto, id_usuario) VALUES(?,?,?,?)";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevopago.getCodigoCongreso());
            ps.setDate(2, java.sql.Date.valueOf(nuevopago.getFecha()));
            ps.setDouble(3, nuevopago.getMonto());
            ps.setString(4, nuevopago.getIdUsuario());

            System.out.println("SQL = " + ps);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("ERROR AL PAGAR");
            e.printStackTrace();
        }

    }

    public double obtenerPagos() {

        String sql = "SELECT SUM(monto) FROM pago";

        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("SUM(monto)");
            }

        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER PAGOS");
            e.printStackTrace();
        }

        return 0.0;
    }

    

}
