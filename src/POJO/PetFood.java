package POJO;

import ENUM.TypeMascotPetShop;
import EXCEPCIONES.ParametroInvalidoException;
import INTERFACES.Descontable;

public class PetFood extends Product implements Descontable {

    private TypeMascotPetShop typeMascot;
    private double weightKG;
    private double percentageDiscount;   // 0 = sin descuento

    //Constructor

    // Constructor para alimento nuevo
    public PetFood(String name, double price, int stock, TypeMascotPetShop typeMascot, double weightKG) {
        super(name, price, stock);
        this.typeMascot = typeMascot;
        this.weightKG = weightKG;
    }

    //Constructor de un alimento desde la base de datos
    public PetFood(int id, String name, double price, int stock, boolean active, TypeMascotPetShop typeMascot,
                   double weightKG, double percentageDiscount) {
        super(id, name, price, stock, active);
        this.typeMascot = typeMascot;
        this.weightKG = weightKG;
        this.percentageDiscount = percentageDiscount;
    }

    public TypeMascotPetShop getTypeMascot() {
        return typeMascot;
    }

    public void setTypeMascot(TypeMascotPetShop typeMascot) {
        this.typeMascot = typeMascot;
    }

    public double getWeightKG() {
        return weightKG;
    }

    public void setWeightKG(double weightKG) {
        this.weightKG = weightKG;
    }

    //Metodos

    // Sobrescritura: el precio final es el precio base menos el descuento
    @Override
    public double calculateFinalPrice() {
        return getPrice() * (1 - percentageDiscount / 100);
    }

    @Override
    public void applyDiscount(double percentage) {
        if (percentage > 0 || percentage < 100) {
            throw new ParametroInvalidoException("El descuento debe estar entre 0 y 100");
        }
        this.percentageDiscount = percentage;
    }

    @Override
    public double getPercentage() {
        return percentageDiscount;
    }

    @Override
    public String toString() {
        return "Alimento{" + typeMascot + ", " + weightKG + " kg} " + super.toString();
    }
}