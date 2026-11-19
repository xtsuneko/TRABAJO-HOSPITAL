package POJO;

public class Usuario extends Persona {

    private int idUsuario;
    private String email;
    private String password;


    //----CONSTRUCTORES----
    public Usuario(String nombre, String apellido, String dni, String telefono, int idUsuario, String email, String password) {
        super(nombre, apellido, dni, telefono);
        this.idUsuario = idUsuario;
        this.email = email;
        this.password = password;
    }

    public Usuario(int idUsuario, String email, String password, String dni) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.password = password;
        this.setDni(dni);

    }

    public Usuario() {
    }

    //----GETTER Y SETTER----
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    //----METODOS----
    @Override
    public String toString() {
        return "POJO.Usuario{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
