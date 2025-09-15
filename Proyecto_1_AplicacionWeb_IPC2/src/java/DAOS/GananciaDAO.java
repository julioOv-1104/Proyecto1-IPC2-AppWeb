package DAOS;

import Paquete_Servlets.*;
import java.sql.*;

public class GananciaDAO {

    public double obtenerDatosGanancia(String columna) {

        String obtener = "SELECT " + columna + " FROM configuracion_ganancia WHERE id = 1";
        //id = 1 porque es la unica fila que hay

        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement psTotal = conn.prepareStatement(obtener);
            ResultSet rs = psTotal.executeQuery();

            if (rs.next()) {
                return rs.getDouble("" + columna);
            }

        } catch (Exception e) {
        }

        return 0.0;
    }
    
    public void cambiarPorcentaje(double nuevoPorcentaje){
    
        String sql = "UPDATE configuracion_ganancia SET porcentaje_comision = ? WHERE id = 1;";
        
        Connection conn = ConexionBD.getInstancia().getConexionbd();
         try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, nuevoPorcentaje);
            
            ps.executeUpdate();
        } catch (SQLException e) {
             System.out.println("ERROR AL CAMBIAR PORCENTAJE DE COMISION");
             e.printStackTrace();
        }
        
    }
    

}
