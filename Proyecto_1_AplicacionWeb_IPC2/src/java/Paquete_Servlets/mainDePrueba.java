
package Paquete_Servlets;

import com.sun.jdi.connect.spi.Connection;


public class mainDePrueba {
    
    public static void main(String[] args) {
        try {
            ConexionBD conn = ConexionBD.getInstancia();
            System.out.println("Conexion Exitosa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
