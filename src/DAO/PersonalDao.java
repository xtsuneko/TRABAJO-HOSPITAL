package DAO;
import CONEXION.ConexionDB;
import POJO.Persona;
import POJO.Personal;
import POJO.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class PersonalDao {

    public int insertar(Personal p) {

        String sqlInsert = "INSERT INTO personal (sueldo, horario_laboral, id_usuario) VALUES (?, ?, ?)";
        String sqlGetId = "SELECT LAST_INSERT_ID()";

        try (Connection con = ConexionDB.connect();) {
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

        String sql = "UPDATE personal SET  activo = FALSE WHERE id = ?";

        try (Connection con = ConexionDB.connect();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, p.getIdPersonal());

            int filasModificadas= ps.executeUpdate();
            return filasModificadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public Boolean modificar(Personal p) {

        String sql = "UPDATE personal SET  sueldo = ?, horario_laboral = ?, id_usuario = ? WHERE id = ?";

        try (Connection con = ConexionDB.connect();) {
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

    public Personal findBYID(int id) {

        String sql = "SELECT id, sueldo, horario_laboral_id, id_usuario FROM personal WHERE id = ?";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return new Personal(

                            resultSet.getDouble("sueldo"),
                            resultSet.getInt("horario_laboral_id"),
                            resultSet.getInt("id"),
                            resultSet.getInt("id_usuario"));

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


    public ArrayList<Personal> listarPersonal() {

        String sql = "SELECT id, sueldo, horario_laboral_id, id_usuario FROM personal";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Personal p = new Personal(

                            resultSet.getDouble("sueldo"),
                            resultSet.getInt("horario_laboral_id"),
                            resultSet.getInt("id"),
                            resultSet.getInt("id_usuario"));

                    ArrayList<Personal> listaPersonal = new ArrayList<>();
                    listaPersonal.add(p);
                    return listaPersonal;
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
