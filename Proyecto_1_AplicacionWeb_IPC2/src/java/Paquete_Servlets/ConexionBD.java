package Paquete_Servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;

public class ConexionBD {

    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "gestion_congresos_codenbugs";
    private static final String USER = "universal";
    private static final String PASSWORD = "12345";
    private static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;

    private static ConexionBD instancia;
    private Connection conexionbd;

    private ConexionBD() {

        try {
            conexionbd = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Coneccion realizada con exito");
        } catch (SQLException e) {
            System.out.println("Error con conexion a la BD");
            e.printStackTrace();
        }
    }

    public Connection getConexionbd() {
        return conexionbd;
    }

    public static ConexionBD getInstancia() {

        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

}
