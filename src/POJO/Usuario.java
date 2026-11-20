package POJO;
import GENERICS.ClaseBaseBddGenerica;

public class Usuario extends ClaseBaseBddGenerica<Integer> {

    private String email;
    private String password;
    private Persona persona;
    private String dniPersona;


    //----CONSTRUCTORES----
    public Usuario(int idUsuario, String email, String password, String dniPersona) {
        super(true, idUsuario);
        this.email = email;
        this.password = password;
        this.dniPersona = dniPersona;
    }


    public Usuario() {
        this.persona = new Persona();
    }

    //----GETTER Y SETTER----
    public int getIdUsuario() {
        return getId();
    }

    public void setIdUsuario(int idUsuario) {
        this.setId(idUsuario);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getDniPersona() {
        return dniPersona;
    }

    public void setDniPersona(String dniPersona) {
        this.dniPersona = dniPersona;
    }

    //----METODOS----
    @Override
    public String toString() {
        return "POJO.Usuario{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
