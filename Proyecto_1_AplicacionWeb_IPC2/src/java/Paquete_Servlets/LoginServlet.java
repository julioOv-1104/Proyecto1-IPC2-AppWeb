
package Paquete_Servlets;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.Connection;
import java.io.IOException;
import java.sql.PreparedStatement;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
       
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        String sql ="SELECT 1 FROM usuario WHERE id = ? AND password = ?;";
        
        String idUsuario = request.getParameter("userID");
        String contraseña = request.getParameter("userPassword");
        
        try (Connection conn  = ConexionBD.getInstancia().getConexionbd();){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idUsuario);
            ps.setString(2, contraseña);
            ps.executeUpdate();
        } catch (Exception e) {
        }
 
    }
    
}
