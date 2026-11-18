package DAO;
import POJO.Cliente;
import java.sql.*;

public class ClienteDao {

    private static final String url = "jdbc:mysql://localhost:3306/veterinaria";
    private static final String user = "root";
    private static final String pass = "admin";

    public int insertar(Cliente c) {

        String sqlInsert = "INSERT INTO cliente (direccion, id_usuario) VALUES (?, ?)";
        String sqlGetId = "SELECT LAST_INSERT_ID()";

        try (Connection con = DriverManager.getConnection(url, user, pass)) {
            System.out.println("¡Conectado!");
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setString(1, c.getDireccion());
            ps.setInt(2, c.getIdUsuario());
            ps.executeUpdate();

            PreparedStatement psID = con.prepareStatement(sqlGetId);
            ResultSet rs = psID.executeQuery();
            if (rs.next()) {
                int ultimoId = rs.getInt(1);
                c.setIdUsuario(ultimoId);
                return ultimoId;
            }

        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return -1;
    }




}
