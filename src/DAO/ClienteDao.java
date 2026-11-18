package DAO;
import POJO.Cliente;
import POJO.Usuario;

import java.sql.*;

public class ClienteDao {

    private static final String url = "jdbc:mysql://localhost:3306/veterinaria";
    private static final String user = "root";
    private static final String pass = "admin";

    public int insertar(Cliente c) {

        String sqlInsert = "INSERT INTO cliente (direccion, id_usuario) VALUES (?, ?)";
        String sqlGetId = "SELECT LAST_INSERT_ID()";

        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setString(1, c.getDireccion());
            ps.setInt(2, c.getIdUsuario());
            ps.executeUpdate();

            PreparedStatement psID = con.prepareStatement(sqlGetId);
            ResultSet rs = psID.executeQuery();
            if (rs.next()) {
                int ultimoId = rs.getInt(1);
                c.setIdCliente(ultimoId);
                return ultimoId;
            }

        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return -1;
    }

    public boolean eliminar(Cliente c) throws SQLException {

        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getIdCliente());

            int filasEliminadas = ps.executeUpdate();
            return filasEliminadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }


    public Boolean modificar(Cliente c) {

        String sql = "UPDATE cliente SET direccion = ?, id_usuario = ?  WHERE id = ?";

        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getDireccion());
            ps.setInt(2, c.getIdUsuario());
            ps.setInt(3, c.getIdCliente());

            int filasModificadas= ps.executeUpdate();
            return filasModificadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

}
