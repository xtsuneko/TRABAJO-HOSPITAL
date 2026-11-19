package POJO;

import INTERFACES.Adoptable;

import java.util.Objects;

public class Domestico extends Animal implements Adoptable {
    private String species;
    private Cliente owner;
    private boolean inAdoption;

    public Domestico(int id, String nombre, String color, int edad, double pesoKg) {
        super(id, nombre, color, edad, pesoKg);
    }

    public Domestico(int id, String nombre, String color, int edad, double pesoKg, String species, Cliente owner, boolean inAdoption) {
        super(id, nombre, color, edad, pesoKg);
        this.species = species;
        this.owner = owner;
        this.inAdoption = inAdoption;
    }


    public boolean isInAdoption() {
        return inAdoption;
    }

    public void setInAdoption(boolean inAdoption) {
        this.inAdoption = inAdoption;
    }

    public Cliente getOwner() {
        return owner;
    }

    public void setOwner(Cliente owner) {
        this.owner = owner;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "Domestico{" +
                "species='" + species + '\'' +
                ", owner=" + owner +
                ", inAdoption=" + inAdoption +
                "} " + super.toString();
    }


    @Override
    public boolean isEnAdopcion() {
        return false;
    }

    @Override
    public void ponerEnAdopcion() {

    }

    @Override
    public void marcarAdoptado(Cliente nuevoDueño) {

    }
}
