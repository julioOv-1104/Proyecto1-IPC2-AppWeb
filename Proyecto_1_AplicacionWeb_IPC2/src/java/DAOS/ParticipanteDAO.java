package DAOS;

import Entidades.*;
import Paquete_Servlets.*;
import java.sql.*;

public class ParticipanteDAO {

    public void RegistrarParticipante(Participante nuevoParticipante) {

        String sql = "INSERT INTO participante (nombre, id_usuario, tipo_participante) VALUES(?,?,?)";
        Connection conn = ConexionBD.getInstancia().getConexionbd();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevoParticipante.getNombre());
            ps.setString(2, nuevoParticipante.getIdUsuario());
            ps.setString(3, nuevoParticipante.getTipo().name());

            System.out.println("SQL = " + ps);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("ERROR AL REGISTRAR PARTICIPANTE");
            e.printStackTrace();
        }

    }

}
