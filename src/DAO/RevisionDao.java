package DAO;
import CONEXION.ConexionDB;
import POJO.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RevisionDao {

    public int insertarRevision(Revision r) {

        String sqlInsert = "INSERT INTO revision (id_turno, diagnostico, tratamiento) VALUES (?, ?, ?)";
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

        String sql = "UPDATE revision SET id_turno = ?, diagnostico = ?, tratamiento = ? WHERE id = ?";

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

        String sql = "SELECT id, activo, id_turno, diagnostico, tratamiento FROM revision WHERE id = ? AND activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return new Revision(

                            resultSet.getBoolean("activo"),
                            resultSet.getInt("id"),
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

    public Revision findBYIDPorMedico(int id) {

        String sql = "SELECT id, activo, id_turno, diagnostico, tratamiento \n" +
                "FROM revision r\n" +
                "inner join turno t  ON r.id_turno = t.id \n" +
                "WHERE id_personal = ? AND r.activo = TRUE";

        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return new Revision(

                            resultSet.getBoolean("activo"),
                            resultSet.getInt("id"),
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

    public ArrayList<Revision> listarFichasPorCliente(int id) {

        ArrayList<Revision> listaRevision = new ArrayList<>();
        String sql = "SELECT r.id AS id_revision, r.activo, r.id_turno, r.diagnostico, r.tratamiento,\n" +
                "t.fecha, t.horario, t.motivo_consulta, t.id_personal, t.id_cliente\n" +
                "c.direccion, c.id_usuario AS id_usuario_cliente, \n" +
                "pl.sueldo, pl.horario_laboral_id, pl.id_rol, pl.id_usuario AS id_usuario_personal,\n" +
                "pc.nombre AS nombre_cliente, pc.dni AS dni_cliente, pc.apellido AS apellido_cliente, pc.telefono AS telefono_cliente,\n" +
                "ppl.nombre AS nombre_personal, ppl.dni AS dni_personal, ppl.apellido AS apellido_personal, ppl.telefono AS telefono_personal\n" +
                "FROM revision r\n" +
                "inner join turno t  ON r.id_turno = t.id\n" +
                "inner join cliente c ON t.id_cliente = c.id\n" +
                 "inner join usuario uc ON uc.id = c.id_usuario\n" +
                "inner join persona pc ON pc.dni = uc.dni_persona\n" +
                "inner join personal pl ON t.id_personal = pl.id\n" +
                "inner join usuario upl ON upl.id = pl.id_usuario\n" +
                "inner join persona ppl ON ppl.dni = upl.dni_persona\n" +
                "WHERE t.id_cliente = ? AND r.activo = TRUE;";

        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Revision revision = new Revision(

                            resultSet.getBoolean("activo"),
                            resultSet.getInt("id_revision"),
                            resultSet.getInt("id_turno"),
                            resultSet.getString("diagnostico"),
                            resultSet.getString("tratamiento"));

                    Turno turno = new Turno(
                            resultSet.getInt("id_cliente"),
                            resultSet.getInt("id_personal"),
                            resultSet.getDate("fecha").toLocalDate(),
                            resultSet.getTime("horario").toLocalTime(),
                            resultSet.getString("motivo_consulta"));

                    Personal pl = new Personal(

                            resultSet.getDouble("sueldo"),
                            resultSet.getInt("horario_laboral_id"),
                            resultSet.getInt("id"),
                            resultSet.getInt("id_usuario_personal"),
                            resultSet.getInt("id_rol"));

                    Persona p1 = new Persona(

                            resultSet.getString("nombre_personal"),
                            resultSet.getString("apellido_personal"),
                            resultSet.getString("dni_personal"),
                            resultSet.getString("telefono_personal"));

                    pl.setPersona(p1);

                    Cliente c1 = new Cliente(

                            resultSet.getString("direccion"),
                            resultSet.getInt("id"),
                            resultSet.getInt("id_usuario_cliente"));

                    Persona p2 = new Persona(

                            resultSet.getString("nombre_cliente"),
                            resultSet.getString("apellido_cliente"),
                            resultSet.getString("dni_cliente"),
                            resultSet.getString("telefono_cliente"));

                    c1.setPersona(p2);
                    turno.setCliente(c1);
                    turno.setMedico(pl);

                    revision.setTurno(turno);

                    listaRevision.add(revision);
                }
                return listaRevision;
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

        ArrayList<Revision> listaRevision = new ArrayList<>();
        String sql = "SELECT id, activo, id_turno, diagnostico, tratamiento FROM revision WHERE activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Revision r = new Revision(

                            resultSet.getBoolean("activo"),
                            resultSet.getInt("id"),
                            resultSet.getInt("id_turno"),
                            resultSet.getString("diagnostico"),
                            resultSet.getString("tratamiento"));

                    listaRevision.add(r);
                }
                return listaRevision;
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
