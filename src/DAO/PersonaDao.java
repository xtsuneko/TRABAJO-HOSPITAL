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
            System.out.println("¡Conectado!");
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





}
