package ENUM;

public enum Roles {

    ADMINISTRADOR(1),
    VETERINARIO(2),
    RECEPCIONISTA(3);

    private final int codigo;

    Roles(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

}
