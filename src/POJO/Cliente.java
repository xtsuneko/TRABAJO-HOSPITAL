package POJO;

public class Cliente extends  Usuario{

    private String direccion;

    //----CONSTRUCTORES----


    public Cliente(String nombre, String apellido, String dni, String telefono, int idUsuario, String email, String password, String direccion) {
        super(nombre, apellido, dni, telefono, idUsuario, email, password);
        this.direccion = direccion;
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


    //----METODOS----
    @Override
    public String toString() {
        return "Cliente{" +
                "direccion='" + direccion + '\'' +
                '}';
    }
}
