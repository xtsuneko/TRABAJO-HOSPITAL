package DAO;
import CONEXION.ConexionDB;
import POJO.Persona;

import java.sql.*;
import java.util.ArrayList;

public class PersonaDao {

    public void insertar(Persona p) {

        String sqlInsert = "INSERT INTO persona (dni, nombre, apellido, telefono) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.connect()) {
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

        String sql = "UPDATE persona SET  activo = FALSE WHERE dni = ?";

        try (Connection con = ConexionDB.connect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getDni());

            int filasModificadas= ps.executeUpdate();
            return filasModificadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public Boolean modificar(Persona p) {

        String sql = "UPDATE persona SET  nombre = ?, apellido = ? , telefono = ? WHERE dni = ?";

        try (Connection con = ConexionDB.connect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getDni());

            int filasModificadas= ps.executeUpdate();
            return filasModificadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }


    public Persona findBYDNI(String dni) {

        String sql = "SELECT dni, nombre, apellido, telefono FROM persona WHERE dni = ? AND activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, dni);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return new Persona(

                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("dni"),
                            resultSet.getString("telefono"));

                } else {
                    return null;
                }
            }catch(SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
                e.printStackTrace();
            }
        } catch(SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Persona> listarPersonas() {

        String sql = "SELECT dni, nombre, apellido, telefono FROM persona AND activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Persona p = new Persona(

                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("dni"),
                            resultSet.getString("telefono"));

                    ArrayList<Persona> listaPersonas = new ArrayList<>();
                    listaPersonas.add(p);
                return listaPersonas;
                }
            }catch(SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
                e.printStackTrace();
            }
        } catch(SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

}
