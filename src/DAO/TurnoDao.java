package DAO;
import CONEXION.ConexionDB;
import POJO.Turno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TurnoDao {

    public int insertarTurno(Turno t) {

        String sqlInsert = "INSERT INTO turno (id_cliente, id_personal, fecha, horario, motivo_consulta) VALUES (?, ?, ?, ?, ?)";
        String sqlGetId = "SELECT LAST_INSERT_ID()";

        try (Connection con = ConexionDB.connect()) {
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setInt(1, t.getIdCliente());
            ps.setInt(2, t.getIdPersonal());
            ps.setDate(3, java.sql.Date.valueOf(t.getFechaTurno()));
            ps.setTime(4, java.sql.Time.valueOf(t.getHoraTurno()));
            ps.setString(5, t.getMotivoConsulta());
            ps.executeUpdate();

            PreparedStatement psID = con.prepareStatement(sqlGetId);
            ResultSet rs = psID.executeQuery();
            if (rs.next()) {
                int ultimoId = rs.getInt(1);
                t.setId(ultimoId);
                return ultimoId;
            }

        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return -1;
    }

    public boolean eliminar(Turno t) throws SQLException {

        String sql = "UPDATE turno SET activo = FALSE  WHERE id = ?";

        try (Connection con = ConexionDB .connect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t.getId());

            int filasModificadas= ps.executeUpdate();
            return filasModificadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }


    public Boolean modificar(Turno t) {

        String sql = "UPDATE turno SET id_cliente = ?, id_personal = ?, fecha = ?, horario = ? , motivo_consulta = ? WHERE id = ?";

        try (Connection con = ConexionDB .connect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t.getIdCliente());
            ps.setInt(2, t.getIdPersonal());
            ps.setDate(3, java.sql.Date.valueOf(t.getFechaTurno()));
            ps.setTime(4, java.sql.Time.valueOf(t.getHoraTurno()));
            ps.setString(5, t.getMotivoConsulta());
            ps.setInt(6, t.getId());

            int filasModificadas= ps.executeUpdate();
            return filasModificadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public Turno findBYID(int id) {

        String sql = "SELECT id_cliente, id_personal, fecha, horario, motivo_consulta FROM turno WHERE id = ? AND activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return new Turno(

                            resultSet.getInt("idCliente"),
                            resultSet.getInt("idPersonal"),
                            resultSet.getDate("fechaTurno").toLocalDate(),
                            resultSet.getTime("horaTurno").toLocalTime(),
                            resultSet.getString("motivoConsulta"));

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

    public ArrayList<Turno> listarTurnos() {

        String sql = "SELECT SELECT id_cliente, id_personal, fecha, horario, motivo_consulta FROM turno AND activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Turno t = new Turno(

                            resultSet.getInt("idCliente"),
                            resultSet.getInt("idPersonal"),
                            resultSet.getDate("fechaTurno").toLocalDate(),
                            resultSet.getTime("horaTurno").toLocalTime(),
                            resultSet.getString("motivoConsulta"));

                    ArrayList<Turno> listaTurnos = new ArrayList<>();
                    listaTurnos.add(t);
                    return listaTurnos;
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
