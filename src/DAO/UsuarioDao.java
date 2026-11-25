package DAO;

import CONEXION.ConexionDB;
import POJO.Usuario;
import java.sql.*;
import java.util.ArrayList;

public class UsuarioDao {


    public int insertar(Usuario u) {
        String sqlInsert = "INSERT INTO usuario (email, pass, dni_persona) VALUES (?, ?, ?)";
        String sqlGetId = "SELECT LAST_INSERT_ID()";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sqlInsert)) {

            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getDniPersona());
            ps.executeUpdate();

            try (PreparedStatement psID = con.prepareStatement(sqlGetId);
                 ResultSet rs = psID.executeQuery()) {
                if (rs.next()) {
                    int ultimoId = rs.getInt(1);
                    u.setIdUsuario(ultimoId);
                    return ultimoId;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return -1;
    }

    public boolean eliminar(Usuario u) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, u.getIdUsuario());
            int filasEliminadas = ps.executeUpdate();
            return filasEliminadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public Boolean modificar(Usuario u) {
        String sql = "UPDATE usuario SET email = ?, pass = ?, dni_persona = ? WHERE id = ?";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getDniPersona());
            ps.setInt(4, u.getIdUsuario());

            int filasModificadas = ps.executeUpdate();
            return filasModificadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public boolean existeEmail(String email) {
        String sql = "SELECT 1 FROM usuario WHERE email = ? LIMIT 1";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar email: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public boolean existeDni(String dni) {
        String sql = "SELECT 1 FROM usuario WHERE dni_persona = ? LIMIT 1";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar DNI: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public boolean existeId(int idUsuario) {
        String sql = "SELECT 1 FROM usuario WHERE id = ? LIMIT 1";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar ID de usuario: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public Usuario findBYEmailPassword(String email, String password) {

        String sql = "SELECT id, email, pass, dni_persona FROM usuario WHERE email = ? AND pass = ? AND activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return new Usuario(

                            resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("pass"),
                            resultSet.getString("dni_persona"));

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

    public Usuario findBYEmail(String email) {

        String sql = "SELECT id, email, pass, dni_persona FROM usuario WHERE email = ?";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return new Usuario(

                            resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("pass"),
                            resultSet.getString("dni_persona"));

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



    public ArrayList<Usuario> listarUsuarios() {

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT id, email, pass, dni_persona FROM usuario WHERE activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Usuario u = new Usuario(

                            resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("pass"),
                            resultSet.getString("dni_persona"));

                    listaUsuarios.add(u);

                }
                return listaUsuarios;
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
    public Usuario buscarPorEmailYPassword(String email, String pass) {
        String sql = "SELECT id, email, pass, dni_persona FROM usuario WHERE email = ? AND pass = ?";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, pass);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    u.setPassword(rs.getString("pass"));
                    u.setDniPersona(rs.getString("dni_persona"));
                    return u;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario por email y contraseña: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public Usuario buscarPorId(int idUsuario) {
        String sql = "SELECT id, email, pass, dni_persona FROM usuario WHERE id = ?";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    u.setPassword(rs.getString("pass"));
                    u.setDniPersona(rs.getString("dni_persona"));
                    return u;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
