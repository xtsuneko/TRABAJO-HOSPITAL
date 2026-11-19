package POJO;

import INTERFACES.Adoptable;

public class Exotico extends Animal {
    private String tipo;

    public Exotico(int id, String nombre, String color, int edad, double pesoKg, String tipo) {
        super(id, nombre, color, edad, pesoKg);
        this.tipo = tipo;
    }

    public Exotico(int id, String nombre, String color, int edad, double pesoKg) {
        super(id, nombre, color, edad, pesoKg);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Exotico{" +
                "tipo='" + tipo + '\'' +
                "} " + super.toString();
    }
}
