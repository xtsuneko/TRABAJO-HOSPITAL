package ENUM;
public enum HorarioLaboral {

    DIURNO(1),
    VESPERTINO(2),
    NOCTURNO(3);

    private final int codigo;

    HorarioLaboral(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }


}
