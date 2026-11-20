package DAO;
import CONEXION.ConexionDB;
import POJO.Persona;
import POJO.Usuario;
import java.sql.*;
import java.util.ArrayList;

public class UsuarioDao {

    public int insertar(Usuario u) {

        String sqlInsert = "INSERT INTO usuario (email, pass, dni_persona) VALUES (?, ?, ?)";
        String sqlGetId = "SELECT LAST_INSERT_ID()";

        try (Connection con = ConexionDB.connect();) {
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getDniPersona());
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

        String sql = "UPDATE usuario SET  activo = FALSE WHERE id = ?";

        try (Connection con = ConexionDB.connect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, u.getIdUsuario());

            int filasModificadas= ps.executeUpdate();
            return filasModificadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public Boolean modificar(Usuario u) {

        String sql = "UPDATE usuario SET  email = ?, password = ?, dni_persona=? WHERE id = ?";

        try (Connection con = ConexionDB.connect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getDniPersona());
            ps.setInt(4, u.getIdUsuario());

            int filasModificadas= ps.executeUpdate();
            return filasModificadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public Usuario findBYID(int id) {

        String sql = "SELECT id, email, contraseña, dni_persona FROM persona WHERE id = ?";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return new Usuario(

                            resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("dni"));

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

        String sql = "SELECT id, email, contraseña, dni_persona FROM usuario";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Usuario u = new Usuario(

                            resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("dni"));

                    ArrayList<Usuario> listaUsuarios = new ArrayList<>();
                    listaUsuarios.add(u);
                    return listarUsuarios();
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
