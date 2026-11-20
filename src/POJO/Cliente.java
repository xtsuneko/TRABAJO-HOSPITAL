package POJO;
import GENERICS.ClaseBaseBddGenerica;

public class Cliente extends ClaseBaseBddGenerica<Integer> {

    private String direccion;
    private Usuario usuario;
    private int idUsuario;

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

    public Cliente() {
        this.usuario = new Usuario();
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

    //----METODOS----
    @Override
    public String toString() {
        return "Cliente{" +
                "direccion='" + direccion + '\'' +
                '}';
    }
}
