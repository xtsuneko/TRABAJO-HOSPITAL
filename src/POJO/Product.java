package POJO;

public abstract class Product {

    private int id;
    private String name;
    private double price;      // precio base
    private int stock;
    private boolean active = true;   // baja lógica: false = dado de baja

    //----CONSTRUCTORES----

    // Constructor para productos nuevos
    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Constructor de producto desde la base de datos
    public Product(int id, String name, double price, int stock, boolean active) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.active = active;
    }

    //----GETTER Y SETTER----
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //----METODOS----

    public abstract double calculateFinalPrice();

    // Chequeo de Ids

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product otro = (Product) o;
        return id != 0 && id == otro.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return name + " | precio final: $" + calculateFinalPrice() + " | stock: " + stock;
    }
}