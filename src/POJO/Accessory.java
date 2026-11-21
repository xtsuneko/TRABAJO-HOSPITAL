package POJO;

public class Accessory extends Product{

    private String material;
    private String size;

    // Constructores

    //Constructor nuevo
    public Accessory(String name, double price, int stock, String material, String size){

    super(name,price,stock);
    this.material = material;
    this.size = size;
    }

    //Constructor en base de datos
    public Accessory(int id, String name, double price, int stock, boolean active, String material, String size){
        super(id, name, price, stock, active);
        this.material = material;
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    @Override
    public double calculateFinalPrice() {
        return getPrice();
    }

    @Override
    public String toString() {
        return "Accesorio{" + material + ", " + size + "} " + super.toString();
    }
}
