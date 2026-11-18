package DAO;
import POJO.Persona;
import POJO.Personal;
import POJO.Usuario;

import java.sql.*;

public class PersonalDao {

    private static final String url = "jdbc:mysql://localhost:3306/veterinaria";
    private static final String user = "root";
    private static final String pass = "admin";

    public int insertar(Personal p) {

        String sqlInsert = "INSERT INTO personal (sueldo, horario_laboral, id_usuario) VALUES (?, ?, ?)";
        String sqlGetId = "SELECT LAST_INSERT_ID()";

        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setDouble(1, p.getSueldo());
            ps.setInt(2, p.getHorarioLaboral().ordinal());
            ps.setInt(3, p.getIdUsuario());
            ps.executeUpdate();

            PreparedStatement psID = con.prepareStatement(sqlGetId);
            ResultSet rs = psID.executeQuery();
            if (rs.next()) {
                int ultimoId = rs.getInt(1);
                p.setIdPersonal(ultimoId);
                return ultimoId;
            }

        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return -1;
    }

    public boolean eliminar(Personal p) throws SQLException {

        String sql = "DELETE FROM personal WHERE id = ?";

        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, p.getIdPersonal());

            int filasEliminadas = ps.executeUpdate();
            return filasEliminadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public Boolean modificar(Personal p) {

        String sql = "UPDATE personal SET  sueldo = ?, horario_laboral = ?, id_usuario = ? WHERE id = ?";

        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, p.getSueldo());
            ps.setInt(2, p.getHorarioLaboral().ordinal());
            ps.setInt(3, p.getIdUsuario());
            ps.setInt(4, p.getIdPersonal());

            int filasModificadas= ps.executeUpdate();
            return filasModificadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }



}
