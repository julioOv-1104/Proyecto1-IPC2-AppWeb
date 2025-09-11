package Paquete_Servlets;

import DAOS.UsuarioDAO;
import java.sql.*;

public class mainDePrueba {


    public void prueba() {
        try {
            ConexionBD conn = ConexionBD.getInstancia();
            System.out.println("Conexion Exitosa");

            /*String sql = "INSERT INTO usuario (id, nombre, numero_telefonico, correo, monedero, tipo_usuario, password)"
                    + " VALUES ('69', 'julio', '12345', 'julio@algo.com', 0.0, 'ADMIN_SISTEMA', 'contrasenna')";
            
            PreparedStatement ps = conn.getConexionbd().prepareStatement(sql);
            ps.executeUpdate(sql);
            
            System.out.println("SQL EJECUTADO "+sql);*/
            UsuarioDAO userDAO = new UsuarioDAO();
            userDAO.insertarUsuario();

            Connection conection = ConexionBD.getInstancia().getConexionbd();//ejemplo para usar en los servlets

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
