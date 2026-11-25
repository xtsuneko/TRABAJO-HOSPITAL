package POJO;
import GENERICS.ClaseBaseBddGenerica;

public class Cliente extends ClaseBaseBddGenerica<Integer> {

    private String direccion;
    private Usuario usuario;
    private int idUsuario;
    private Persona persona;

    //----CONSTRUCTORES----
    public Cliente(int idCliente, String direccion, int idUsuario) {
        super(true, idCliente);
        this.direccion = direccion;
        this.usuario = new Usuario();
        this.idUsuario = idUsuario;
    }

    public Cliente(String direccion, int idCliente, int idUsuario) {
        this.direccion = direccion;
        this.idUsuario = idUsuario;
        this.setId(idCliente);
    }

    public Cliente(String direccion, int idUsuario) {
        this.direccion = direccion;
        this.idUsuario = idUsuario;
    }

    public Cliente() {
        this.usuario = new Usuario();
        this.persona = new Persona();
    }

    //----GETTER Y SETTER----
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdCliente() {
        return getId();
    }

    public void setIdCliente(int idCliente) {
        this.setId(idCliente);
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    //----METODOS----
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
        return "\nCliente:" +
                "\n\tID=" + this.getId() +
                "\n\tNombre=" + nombre +
                "\n\tApellido=" + apellido +
                "\n\tTelefono=" + telefono +
                "\n\tdireccion='" + direccion;
    }
}
