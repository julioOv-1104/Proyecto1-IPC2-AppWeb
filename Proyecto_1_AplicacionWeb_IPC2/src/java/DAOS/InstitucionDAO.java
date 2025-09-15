package DAOS;

import Entidades.*;
import Paquete_Servlets.ConexionBD;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class InstitucionDAO {

    public List<Institucion> obtenerInstituciones() {

        List<Institucion> lista = new ArrayList<>();
        String sql = "SELECT codigo_congreso, id_administrador, nombre_institucion FROM institucion";
        Connection conn = ConexionBD.getInstancia().getConexionbd();
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String congreso = rs.getString("codigo_congreso");
                String admin = rs.getString("id_administrador");
                String nombre = rs.getString("nombre_institucion");
                
                Institucion nueva = new Institucion(admin, nombre);
                if (!congreso.isBlank()) {
                    nueva.setCodigo_congreso(congreso);
                }else {
                nueva.setCodigo_congreso(null);
                }
                
                lista.add(nueva);//Se va llenando la lista con las institucionesde la BD
            }
        } catch (Exception e) {
            System.out.println("Error al enlistar las instituciones");
            e.printStackTrace();
        }
        return lista;
    }

}
