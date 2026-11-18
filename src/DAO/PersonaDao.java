package DAO;
import POJO.Persona;

import java.sql.*;

public class PersonaDao {

    private static final String url = "jdbc:mysql://localhost:3306/veterinaria";
    private static final String user = "root";
    private static final String pass = "admin";


    public void insertar(Persona p) {

        String sqlInsert = "INSERT INTO persona (dni, nombre, apellido, telefono) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setString(1, p.getDni());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellido());
            ps.setString(4, p.getTelefono());
            ps.executeUpdate();

        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public boolean eliminar(Persona p) throws SQLException {

        String sql = "DELETE FROM persona WHERE dni = ?";

        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getDni());

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
