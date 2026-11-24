package DAO;

import CONEXION.ConexionDB;
import ENUM.TypeMascotPetShop;
import POJO.Accessory;
import POJO.PetFood;
import POJO.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*El DAO de producto no va a acontener reglas ni validaciones, PetShop se encargará de eso que sera la clase gestora.
  Los productos se guardan en una tabla sola con la diferenciacion entre Pet_Food y accessory para los items.
  Para eliminar productos? Se marca active = FALSE en lugar de borrar la fila
 */
public class ProductDAO {

    // Valores de la columna "type" que identifican cada subclase
    private static final String TYPE_PET_FOOD = "PET_FOOD";
    private static final String TYPE_ACCESSORY = "ACCESSORY";

    //Agrega productos
    public void insert(Product p) throws SQLException {
        String sql = "INSERT INTO products (name, price, stock, type, pet_type, weight_kg, "
                + "discount_percentage, material, size) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStock());
            ps.setString(4, typeOf(p));
            completeSpecificColumns(ps, p, 5);

            ps.executeUpdate();

            // Recuperamos el id generado por la base y se lo asignamos al objeto
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }
            }
        }
    }

    //Actualizacion de productos
    public boolean modificar(Product p) throws SQLException {
        String sql = "UPDATE products SET name = ?, price = ?, stock = ?, pet_type = ?, "
                + "weight_kg = ?, discount_percentage = ?, material = ?, size = ? "
                + "WHERE id = ? AND active = TRUE";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStock());
            completeSpecificColumns(ps, p, 4);
            ps.setInt(9, p.getId());

            return ps.executeUpdate() > 0;
        }
    }

    //Baja booleana, activar o desactivar productos
    public boolean bajaLogica(int id) throws SQLException {
        String sql = "UPDATE products SET active = FALSE WHERE id = ? AND active = TRUE";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    //Consulta por ID
    public Product buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ? AND active = TRUE";

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

    // Muestra la lista
    public List<Product> listar() throws SQLException {
        List<Product> lista = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE active = TRUE";

        try (Connection con = ConexionDB.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    //Metodos privados

    //Devuelve que tipo de productos es el producto solicitado
    private String typeOf(Product p) {
        if (p instanceof PetFood) {
            return TYPE_PET_FOOD;
        } else if (p instanceof Accessory) {
            return TYPE_ACCESSORY;
        }
        throw new IllegalArgumentException("Tipo de producto no soportado: " + p.getClass().getSimpleName());
    }

    // Completa las columnas especificas de cada subclase, si no se aplican a la subclase se cargan como NULL
    private void completeSpecificColumns(PreparedStatement ps, Product p, int inicio) throws SQLException {
        if (p instanceof PetFood f) {
            ps.setString(inicio, f.getTypeMascot().name());
            ps.setDouble(inicio + 1, f.getWeightKG());
            ps.setDouble(inicio + 2, f.getPercentage());
            ps.setNull(inicio + 3, Types.VARCHAR);
            ps.setNull(inicio + 4, Types.VARCHAR);
        } else if (p instanceof Accessory a) {
            ps.setNull(inicio, Types.VARCHAR);
            ps.setNull(inicio + 1, Types.DOUBLE);
            ps.setNull(inicio + 2, Types.DOUBLE);
            ps.setString(inicio + 3, a.getMaterial());
            ps.setString(inicio + 4, a.getSize());
        } else {
            throw new IllegalArgumentException("Tipo de producto no soportado: " + p.getClass().getSimpleName());
        }
    }

    // Convierte el resultado de ResultSet segun el tipo y contruye segun la subclase, si no exist en el Enum, Excepcion
    private Product mapear(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        int stock = rs.getInt("stock");
        boolean active = rs.getBoolean("active");
        String type = rs.getString("type");

        if (TYPE_PET_FOOD.equals(type)) {
            return new PetFood(id, name, price, stock, active,
                    TypeMascotPetShop.valueOf(rs.getString("pet_type")),
                    rs.getDouble("weight_kg"),
                    rs.getDouble("discount_percentage"));
        } else if (TYPE_ACCESSORY.equals(type)) {
            return new Accessory(id, name, price, stock, active,
                    rs.getString("material"),
                    rs.getString("size"));
        }
        throw new SQLException("Tipo de producto desconocido en la base de datos: " + type);
    }
}