package COLECCION;
import POJO.Cliente;
import java.util.HashMap;
import java.util.Map;

public class ClienteColeccion {

    Map<Integer, Cliente> listaCliente;

    public ClienteColeccion() {
        this.listaCliente = new HashMap<>();
    }

    public String agregarClientes(Integer clave, Cliente c){
        String mensaje= " ";

        if(listaCliente==null){
            this.listaCliente = new HashMap<>();
        }
        listaCliente.put(clave, c);

        return mensaje="Cliente agregado exitosamente al sistema";
    }

    public String eliminarCliente(Integer clave){
        String mensaje=" ";
        if(listaCliente.containsKey(clave)){
            listaCliente.remove(clave);
            mensaje="Cliente removido exitosamente del sistema";
        }else{
            mensaje="El Cliente que intenta remover no existe en el sistema";
        }

        return mensaje;
    }

    public boolean actualizarCliente(Integer clave, Cliente c){

        if(listaCliente.containsKey(clave) && (clave == c.getIdCliente())){

            listaCliente.put(clave, c);

            return true;
        }

        return false;
    }

    public void mostrarColeccion(){

        System.out.println(listaCliente);

    }

    public boolean buscarCliente(Integer clave){

        if (listaCliente.containsKey(clave)) {
            System.out.println("Cliente encontrado: ");
            System.out.println(listaCliente.get(clave));
            return true;
        }

        return false;

    }



    
}
