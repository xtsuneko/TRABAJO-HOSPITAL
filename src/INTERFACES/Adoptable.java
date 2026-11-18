package INTERFACES;

import POJO.Cliente;

public interface Adoptable {
    boolean isEnAdopcion();
    void ponerEnAdopcion();
    void marcarAdoptado(Cliente nuevoDueño);
}
