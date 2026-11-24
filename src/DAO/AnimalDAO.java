package DAO;

import CONEXION.ConexionDB;
import POJO.Animal;
import POJO.Domestico;
import POJO.Exotico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    /// INSERT ///
    public void insertar(Animal a) throws SQLException {
        String sql = "INSERT INTO animals (name, color, age, weight, type, species, category, in_adoption) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, a.getName());
            ps.setString(2, a.getColor());
            ps.setInt(3, a.getAge());
            ps.setDouble(4, a.getWeight());

            // Según la subclase completamos las columnas específicas
            if (a instanceof Domestico d) {
                ps.setString(5, "DOMESTICO");
                ps.setString(6, d.getSpecies());
                ps.setNull(7, Types.VARCHAR);
                ps.setBoolean(8, d.isInAdoption());
            } else if (a instanceof Exotico e) {
                ps.setString(5, "EXOTICO");
                ps.setNull(6, Types.VARCHAR);
                ps.setString(7, e.getType());
                ps.setBoolean(8, false);
            }

            ps.executeUpdate();

            // Recuperamos el id generado y se lo asignamos al objeto
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    a.setId(rs.getInt(1));
                }
            }
        }
    }

    /// UPDATE ///
    public void modificar(Animal a) throws SQLException {
        String sql = "UPDATE animals SET name = ?, color = ?, age = ?, weight = ?, "
                + "species = ?, category = ?, in_adoption = ? "
                + "WHERE id = ? AND active = TRUE";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getName());
            ps.setString(2, a.getColor());
            ps.setInt(3, a.getAge());
            ps.setDouble(4, a.getWeight());

            if (a instanceof Domestico d) {
                ps.setString(5, d.getSpecies());
                ps.setNull(6, Types.VARCHAR);
                ps.setBoolean(7, d.isInAdoption());
            } else if (a instanceof Exotico e) {
                ps.setNull(5, Types.VARCHAR);
                ps.setString(6, e.getType());
                ps.setBoolean(7, false);
            }
            ps.setInt(8, a.getId());

            ps.executeUpdate();
        }
    }

    /// BAJA LÓGICA ///
    public void bajaLogica(int id) throws SQLException {
        String sql = "UPDATE animals SET active = FALSE WHERE id = ?";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /// SELECT por id ///
    public Animal buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM animals WHERE id = ? AND active = TRUE";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    /// SELECT todos los activos ///
    public List<Animal> listar() throws SQLException {
        List<Animal> lista = new ArrayList<>();
        String sql = "SELECT * FROM animals WHERE active = TRUE";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }


    /*
    private Animal mapear(ResultSet rs) throws SQLException {
        Animal a;
        String type = rs.getString("type");

        if (type.equals("DOMESTICO")) {
            Domestico d = new Domestico(rs.getString("name"), rs.getString("color"),
                    rs.getInt("age"), rs.getDouble("weight"),
                    rs.getString("species"));
            d.setInAdoption(rs.getBoolean("in_adoption"));
            a = d;
        } else {
            a = new Exotico(rs.getString("name"), rs.getString("color"),
                    rs.getInt("age"), rs.getDouble("weight"),
                    rs.getString("category"));
        }
        a.setId(rs.getInt("id"));
        return a;
    }

     */
}