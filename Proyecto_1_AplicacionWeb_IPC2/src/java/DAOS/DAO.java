
package DAOS;
import Entidades.*;
import Paquete_Servlets.*;
import java.sql.*;

public class DAO {
    
    public boolean buscarPorParametro(String entidad, String columna, String datoBuscado){
    
        String busqueda ="SELECT 1 FROM "+entidad+" WHERE "+columna+" = ?";
        Connection conn = ConexionBD.getInstancia().getConexionbd();
        
        try {
            PreparedStatement ps = conn.prepareStatement(busqueda);
            ps.setString(1, datoBuscado);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR EN LA BUSUEDA DE "+ entidad +"y "+columna);
            e.printStackTrace();
        }
    
        return false;
    }
    
}
