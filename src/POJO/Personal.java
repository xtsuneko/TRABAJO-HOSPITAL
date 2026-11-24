package POJO;
import ENUM.HorarioLaboral;
import ENUM.Roles;
import GENERICS.ClaseBaseBddGenerica;

public class Personal extends ClaseBaseBddGenerica<Integer> {

    private Double sueldo;
    private HorarioLaboral horarioLaboral;
    private Usuario usuario;
    private int idUsuario;
    private Roles rol;
    private Persona persona;

    //----CONSTRUCTORES----
    public Personal(int idPersonal, Double sueldo, HorarioLaboral horarioLaboral, int idUsuario, Roles rol) {
        super(true, idPersonal);
        this.sueldo = sueldo;
        this.horarioLaboral = horarioLaboral;
        this.setId(idPersonal);
        this.usuario= new Usuario();
        this.idUsuario = idUsuario;
        this.rol = rol;
    }

    public Personal(Double sueldo, int horarioLaboral, int idPersonal, int idUsuario, int rol) {
        this.sueldo = sueldo;
        this.horarioLaboral = HorarioLaboral.values()[horarioLaboral];
        this.setId(idPersonal);
        this.idUsuario = idUsuario;
        this.rol = Roles.values()[rol];
    }

    public Personal() {
        this.usuario = new Usuario();
        this.persona = new Persona();
    }

    public Personal(Double sueldo, HorarioLaboral horario, int idUsuario, Roles rol) {
        this.sueldo = sueldo;
        this.horarioLaboral = horario;
        this.idUsuario=idUsuario;
        this.rol = rol;

    }

    //----GETTER Y SETTER----
    public int getIdPersonal() {
        return getId();
    }

    public void setIdPersonal(int idPersonal) {
        this.setId(idPersonal);
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setHorarioLaboral(HorarioLaboral horarioLaboral) {
        this.horarioLaboral = horarioLaboral;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    //----METODOS----
    //TODO arreglar los TO STRING DE TODOOOOOOS

    @Override
    public String toString() {
        String nombre=" ";
        String apellido= " ";
        String telefono= " ";
        if(persona!=null){
            nombre= persona.getNombre();
            apellido= persona.getApellido();
            telefono= persona.getTelefono();
        }
        String horario;
        switch (horarioLaboral){
            case DIURNO:
                horario="Diurno";
                break;
            case VESPERTINO:
                horario="Vespertino";
                break;
            case NOCTURNO:
                horario="Nocturno";
                break;
            default:
                horario="Sin horario";
                break;
        }

        String rol;
        switch (this.rol){
            case MEDICO:
               rol="Medico";
               break;
            case RECEPCIONISTA:
                rol="Recepcionista";
                break;
            case ADMINISTRADOR:
                rol="Administrador";
                break;
            default:
                rol="Sin rol";
                break;
        }
        return "\nPersonal: " +
                "\n\tNombre=" + nombre +
                "\n\tApellido=" + apellido +
                "\n\tTelefono=" + telefono +
                "\n\tRol=" + rol +
                "\n\tSueldo=" + sueldo +
                "\n\tHorarioLaboral=" + horario;
    }
}
