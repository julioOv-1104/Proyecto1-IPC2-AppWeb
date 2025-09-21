
package DAOS;

import Entidades.*;
import Paquete_Servlets.ConexionBD;
import java.util.*;
import java.sql.*;

public class SalonDAO {
    
    public void registrarSalon(Salon nuevoSalon){
    
        String sql="INSERT INTO salones (nombre_salon,institucion) VALUES (?,?)";
        Connection conn = ConexionBD.getInstancia().getConexionbd();
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevoSalon.getNombreSalon());
            ps.setString(2, nuevoSalon.getNombreInstitucion());
            
            ps.executeUpdate();
            
            
        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR SALON");
            e.printStackTrace();
        }
        
    }
    
    public boolean buscarSalon(Salon nuevoSalon){
    
    String consulta ="SELECT 1 FROM salones WHERE nombre_salon = ?";
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
    
    
    
}
