package POJO;

public abstract class Animal {
    private int id;
    private String nombre;
    private String color;
    private int edad;
    private double pesoKg;
    private boolean activo = true;   // baja lógica

    public Animal(int id, String nombre, String color, int edad, double pesoKg) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.edad = edad;
        this.pesoKg = pesoKg;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        return id == ((Animal) o).id;
    }

    @Override
    public int hashCode() { return Integer.hashCode(id); }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años, " + pesoKg + " kg)";
    }
}