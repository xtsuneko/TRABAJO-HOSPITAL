package POJO;
import ENUM.HorarioLaboral;

public class Personal extends Usuario {

    private Double sueldo;
    private HorarioLaboral horarioLaboral;
    private int idPersonal;

    //----CONSTRUCTORES----
    public Personal(String nombre, String apellido, String dni, String telefono, int id, String email, String password, Double sueldo, HorarioLaboral horarioLaboral) {
        super(nombre, apellido, dni, telefono, id, email, password);
        this.sueldo = sueldo;
        this.horarioLaboral = horarioLaboral;
    }

    public Personal(Double sueldo, int horarioLaboral, int idPersonal, int idUsuario) {
        this.sueldo = sueldo;
        this.horarioLaboral = HorarioLaboral.values()[horarioLaboral];
        this.idPersonal = idPersonal;
        this.setIdUsuario(idUsuario);
    }

    public Personal() {
    }

    //----GETTER Y SETTER----
    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public HorarioLaboral getHorarioLaboral() {
        return horarioLaboral;
    }

    public void setHorarioLaboral(HorarioLaboral horarioLaboral) {
        this.horarioLaboral = horarioLaboral;
    }

    //----METODOS----
    @Override
    public String toString() {
        return "POJO.Personal{" +
                "sueldo=" + sueldo +
                ", horarioLaboral=" + horarioLaboral +
                '}';
    }
}
