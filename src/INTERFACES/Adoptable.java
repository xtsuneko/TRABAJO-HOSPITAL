package INTERFACES;

import POJO.Cliente;

public interface Adoptable {
    boolean InAdoption();
    void putUpForAdoption();
    void hasBeenAdopted(Cliente newOwner);
}
