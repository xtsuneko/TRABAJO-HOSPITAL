package DAO;
import CONEXION.ConexionDB;
import POJO.Cliente;
import POJO.Persona;
import POJO.Personal;
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

        String sql = "SELECT t.id_cliente, t.id_personal, t.fecha, t.horario, t.motivo_consulta,  \n" +
                "c.direccion, c.id_usuario AS id_usuario_cliente, \n" +
                "pl.sueldo, pl.horario_laboral_id, pl.id_rol, pl.id_usuario AS id_usuario_personal,\n" +
                "pc.nombre AS nombre_cliente, pc.apellido AS apellido_cliente, pc.telefono AS telefono_cliente,\n" +
                "ppl.nombre AS nombre_personal, ppl.apellido AS apellido_personal, ppl.telefono AS telefono_personal\n" +
                "FROM turno t\n" +
                "inner join cliente c ON t.id_cliente = c.id\n" +
                "inner join usuario uc ON uc.id = c.id_usuario\n" +
                "inner join persona pc ON pc.dni = uc.dni_persona\n" +
                "inner join personal pl ON t.id_personal = pl.id\n" +
                "inner join usuario upl ON upl.id = pl.id_usuario\n" +
                "inner join persona ppl ON ppl.dni = upl.dni_persona\n" +
                "WHERE t.id = ? AND t.activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Turno t = new Turno(

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
                    t.setCliente(c1);
                    t.setMedico(pl);

                    return t;
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

    public Turno findBYIDYIDCliente(int id, int idCliente) {

        String sql = "SELECT t.id_cliente, t.id_personal, t.fecha, t.horario, t.motivo_consulta,  \n" +
                "c.direccion, c.id_usuario AS id_usuario_cliente, \n" +
                "pl.sueldo, pl.horario_laboral_id, pl.id_rol, pl.id_usuario AS id_usuario_personal,\n" +
                "pc.nombre AS nombre_cliente, pc.apellido AS apellido_cliente, pc.telefono AS telefono_cliente,\n" +
                "ppl.nombre AS nombre_personal, ppl.apellido AS apellido_personal, ppl.telefono AS telefono_personal\n" +
                "FROM turno t\n" +
                "inner join cliente c ON t.id_cliente = c.id\n" +
                "inner join usuario uc ON uc.id = c.id_usuario\n" +
                "inner join persona pc ON pc.dni = uc.dni_persona\n" +
                "inner join personal pl ON t.id_personal = pl.id\n" +
                "inner join usuario upl ON upl.id = pl.id_usuario\n" +
                "inner join persona ppl ON ppl.dni = upl.dni_persona\n" +
                "WHERE t.id = ? AND t.id_cliente = ? AND t.activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, idCliente);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Turno t = new Turno(

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
                    t.setCliente(c1);
                    t.setMedico(pl);

                    return t;
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

    public ArrayList<Turno> findBYIDMedico(int id) {

        ArrayList<Turno> listaTurnos=new ArrayList<>();
        String sql = "SELECT t.id_cliente, t.id_personal, t.fecha, t.horario, t.motivo_consulta,  \n" +
                "c.direccion, c.id_usuario AS id_usuario_cliente, \n" +
                "pl.sueldo, pl.horario_laboral_id, pl.id_rol, pl.id_usuario AS id_usuario_personal,\n" +
                "pc.nombre AS nombre_cliente, pc.apellido AS apellido_cliente, pc.telefono AS telefono_cliente,\n" +
                "ppl.nombre AS nombre_personal, ppl.apellido AS apellido_personal, ppl.telefono AS telefono_personal\n" +
                "FROM turno t\n" +
                "inner join cliente c ON t.id_cliente = c.id\n" +
                "inner join usuario uc ON uc.id = c.id_usuario\n" +
                "inner join persona pc ON pc.dni = uc.dni_persona\n" +
                "inner join personal pl ON t.id_personal = pl.id\n" +
                "inner join usuario upl ON upl.id = pl.id_usuario\n" +
                "inner join persona ppl ON ppl.dni = upl.dni_persona\n" +
                "WHERE t.id_personal = ? AND t.activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Turno t = new Turno(

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
                    t.setCliente(c1);
                    t.setMedico(pl);

                    listaTurnos.add(t);
                    return listaTurnos;
                }
                return listaTurnos;
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

    public Turno findBYIDCliente(int id) {

        String sql = "SELECT t.id_cliente, t.id_personal, t.fecha, t.horario, t.motivo_consulta,  \n" +
                "c.direccion, c.id_usuario AS id_usuario_cliente, \n" +
                "pl.sueldo, pl.horario_laboral_id, pl.id_rol, pl.id_usuario AS id_usuario_personal,\n" +
                "pc.nombre AS nombre_cliente, pc.apellido AS apellido_cliente, pc.telefono AS telefono_cliente,\n" +
                "ppl.nombre AS nombre_personal, ppl.apellido AS apellido_personal, ppl.telefono AS telefono_personal\n" +
                "FROM turno t\n" +
                "inner join cliente c ON t.id_cliente = c.id\n" +
                "inner join usuario uc ON uc.id = c.id_usuario\n" +
                "inner join persona pc ON pc.dni = uc.dni_persona\n" +
                "inner join personal pl ON t.id_personal = pl.id\n" +
                "inner join usuario upl ON upl.id = pl.id_usuario\n" +
                "inner join persona ppl ON ppl.dni = upl.dni_persona\n" +
                "WHERE t.id_cliente = ? AND t.activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Turno t = new Turno(

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
                    t.setCliente(c1);
                    t.setMedico(pl);

                    return t;
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

    public ArrayList<Turno> listarTurnosPorCliente(Integer idCliente) {

        ArrayList<Turno> listaTurnos = new ArrayList<>();
        String sql = "SELECT t.id_cliente, t.id_personal, t.fecha, t.horario, t.motivo_consulta,  \n" +
                "c.direccion, c.id_usuario AS id_usuario_cliente, \n" +
                "pl.sueldo, pl.horario_laboral_id, pl.id_rol, pl.id_usuario AS id_usuario_personal,\n" +
                "pc.nombre AS nombre_cliente, pc.apellido AS apellido_cliente, pc.telefono AS telefono_cliente,\n" +
                "ppl.nombre AS nombre_personal, ppl.apellido AS apellido_personal, ppl.telefono AS telefono_personal\n" +
                "FROM turno t\n" +
                "inner join cliente c ON t.id_cliente = c.id\n" +
                "inner join usuario uc ON uc.id = c.id_usuario\n" +
                "inner join persona pc ON pc.dni = uc.dni_persona\n" +
                "inner join personal pl ON t.id_personal = pl.id\n" +
                "inner join usuario upl ON upl.id = pl.id_usuario\n" +
                "inner join persona ppl ON ppl.dni = upl.dni_persona\n" +
                "WHERE t.id_cliente = ? AND t.activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, idCliente);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Turno t = new Turno(

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
                    t.setCliente(c1);
                    t.setMedico(pl);

                    listaTurnos.add(t);
                    return listaTurnos;
                }
                return listaTurnos;
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

        ArrayList<Turno> listaTurnos = new ArrayList<>();
        String sql = "SELECT t.id_cliente, t.id_personal, t.fecha, t.horario, t.motivo_consulta, \n" +
                "c.direccion, c.id_usuario AS id_usuario_cliente, \n" +
                "pl.sueldo, pl.horario_laboral_id, pl.id_rol, pl.id_usuario AS id_usuario_personal,\n" +
                "pc.nombre AS nombre_cliente, pc.apellido AS apellido_cliente, pc.telefono AS telefono_cliente,\n" +
                "ppl.nombre AS nombre_personal, ppl.apellido AS apellido_personal, ppl.telefono AS telefono_personal\n" +
                "FROM turno t\n" +
                "inner join cliente c ON t.id_cliente = c.id\n" +
                "inner join usuario uc ON uc.id = c.id_usuario\n" +
                "inner join persona pc ON pc.dni = uc.dni_persona\n" +
                "inner join personal pl ON t.id_personal = pl.id\n" +
                "inner join usuario upl ON upl.id = pl.id_usuario\n" +
                "inner join persona ppl ON ppl.dni = upl.dni_persona\n" +
                "WHERE t.activo = TRUE";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Turno t = new Turno(

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
                    t.setCliente(c1);
                    t.setMedico(pl);

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
