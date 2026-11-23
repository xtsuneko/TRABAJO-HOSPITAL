package DAO;
import CONEXION.ConexionDB;
import POJO.Revision;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RevisionDao {

    public int insertarRevision(Revision r) {

        String sqlInsert = "INSERT INTO turno (id_turno, diagnostico, tratamiento) VALUES (?, ?, ?)";
        String sqlGetId = "SELECT LAST_INSERT_ID()";

        try (Connection con = ConexionDB.connect()) {
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setInt(1, r.getIdTurno());
            ps.setString(2, r.getDiagnostico());
            ps.setString(3, r.getTratamiento());
            ps.executeUpdate();

            PreparedStatement psID = con.prepareStatement(sqlGetId);
            ResultSet rs = psID.executeQuery();
            if (rs.next()) {
                int ultimoId = rs.getInt(1);
                r.setId(ultimoId);
                return ultimoId;
            }

        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return -1;
    }

    public boolean eliminar(Revision r) throws SQLException {

        String sql = "UPDATE revision SET activo = FALSE  WHERE id = ?";

        try (Connection con = ConexionDB .connect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, r.getId());

            int filasModificadas= ps.executeUpdate();
            return filasModificadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public Boolean modificar(Revision r) {

        String sql = "UPDATE turno SET id_turno = ?, diagnostico = ?, tratamiento = ? WHERE id = ?";

        try (Connection con = ConexionDB .connect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, r.getIdTurno());
            ps.setString(2, r.getDiagnostico());
            ps.setString(3, r.getTratamiento());
            ps.setInt(4, r.getId());

            int filasModificadas= ps.executeUpdate();
            return filasModificadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public Revision findBYID(int id) {

        String sql = "SELECT id_turno, diagnostico, tratamiento FROM revision WHERE id = ? AND activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return new Revision(

                            resultSet.getInt("id_turno"),
                            resultSet.getString("diagnostico"),
                            resultSet.getString("tratamiento"));


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

    public Revision findBYIDPorVeterinario(int id) {

        String sql = "SELECT id_turno, diagnostico, tratamiento \n" +
                "FROM revision r\n" +
                "inner join turnos t  ON r.id_turno = t.id_turno \n" +
                "WHERE id_personal = ? AND activo = TRUE";

        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return new Revision(

                            resultSet.getInt("id_turno"),
                            resultSet.getString("diagnostico"),
                            resultSet.getString("tratamiento"));


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

    public ArrayList<Revision> listarRevisiones() {

        String sql = "SELECT id_turno, diagnostico, tratamiento FROM revision AND activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Revision r = new Revision(

                            resultSet.getInt("id_turno"),
                            resultSet.getString("diagnostico"),
                            resultSet.getString("tratamiento"));

                    ArrayList<Revision> listaRevision = new ArrayList<>();
                    listaRevision.add(r);
                    return listaRevision;
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
