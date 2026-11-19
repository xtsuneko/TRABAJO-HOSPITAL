package POJO;

public class Cliente extends  Usuario{

    private String direccion;
    private int idCliente;

    //----CONSTRUCTORES----


    public Cliente(String nombre, String apellido, String dni, String telefono, int idUsuario, String email, String password, String direccion) {
        super(nombre, apellido, dni, telefono, idUsuario, email, password);
        this.direccion = direccion;
    }

    public Cliente(String direccion, int idCliente, int idUsuario) {
        this.direccion = direccion;
        this.idCliente = idCliente;
        this.setIdUsuario(idUsuario);
    }

    public Cliente() {
    }

    //----GETTER Y SETTER----
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    //----METODOS----
    @Override
    public String toString() {
        return "Cliente{" +
                "direccion='" + direccion + '\'' +
                '}';
    }
}
