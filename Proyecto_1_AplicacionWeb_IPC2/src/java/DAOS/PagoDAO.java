package DAOS;

import Paquete_Servlets.*;
import java.sql.*;

public class PagoDAO {

    public double obtenerPagos() {

        String sql = "SLECT SUM(monto) FROM pago";

        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("SUM(monto)");
            }

        } catch (Exception e) {
        }
        
        return 0.0;
    }

}
