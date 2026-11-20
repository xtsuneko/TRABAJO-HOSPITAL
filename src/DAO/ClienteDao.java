package DAO;
import CONEXION.ConexionDB;
import POJO.Cliente;
import POJO.Personal;
import POJO.Usuario;

import java.lang.ref.Cleaner;
import java.sql.*;
import java.util.ArrayList;

public class ClienteDao {

    public int insertar(Cliente c) {

        String sqlInsert = "INSERT INTO cliente (direccion, id_usuario) VALUES (?, ?)";
        String sqlGetId = "SELECT LAST_INSERT_ID()";

        try (Connection con = ConexionDB.connect()) {
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

        String sql = "UPDATE cliente SET activo = FALSE  WHERE id = ?";

        try (Connection con = ConexionDB .connect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getIdCliente());

            int filasModificadas= ps.executeUpdate();
            return filasModificadas > 0;
        } catch (
                SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }


    public Boolean modificar(Cliente c) {

        String sql = "UPDATE cliente SET direccion = ?, id_usuario = ?  WHERE id = ?";

        try (Connection con = ConexionDB .connect()) {
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

    public Cliente findBYID(int id) {

        String sql = "SELECT id, direccion, id_usuario FROM cliente WHERE id = ?";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return new Cliente(

                            resultSet.getString("direccion"),
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

    public ArrayList<Cliente> listarClientes() {

        String sql = "SELECT id, direccion, id_usuario FROM cliente";
        try (Connection con = ConexionDB.connect()) {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Cliente c = new Cliente(

                            resultSet.getString("direccion"),
                            resultSet.getInt("id"),
                            resultSet.getInt("id_usuario"));

                    ArrayList<Cliente> listaClientes = new ArrayList<>();
                    listaClientes.add(c);
                    return listaClientes;
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
