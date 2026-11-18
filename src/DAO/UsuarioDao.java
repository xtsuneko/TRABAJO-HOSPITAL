package DAO;
import POJO.Persona;
import POJO.Usuario;
import java.sql.*;

public class UsuarioDao {

    private static final String url = "jdbc:mysql://localhost:3306/veterinaria";
    private static final String user = "root";
    private static final String pass = "admin";


    public int insertar(Usuario u) {

        String sqlInsert = "INSERT INTO usuario (email, password, dni_persona) VALUES (?, ?, ?)";
        String sqlGetId = "SELECT LAST_INSERT_ID()";

        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getDni());
            ps.executeUpdate();

            PreparedStatement psID = con.prepareStatement(sqlGetId);
            ResultSet rs = psID.executeQuery();
            if (rs.next()) {
                int ultimoId = rs.getInt(1);
                u.setIdUsuario(ultimoId);
                return ultimoId;
            }

        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

    return -1;
    }


    public boolean eliminar(Usuario u) throws SQLException {

        String sql = "DELETE FROM usuario WHERE id_usuario = ?";

        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, u.getIdUsuario());

            int filasEliminadas = ps.executeUpdate();
            return filasEliminadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }



}
