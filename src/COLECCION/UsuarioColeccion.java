package COLECCION;
import POJO.Usuario;
import java.util.HashMap;
import java.util.Map;

public class UsuarioColeccion {


    Map<Integer, Usuario> listaUsuario;

    public UsuarioColeccion() {
        this.listaUsuario = new HashMap<>();
    }

    public String agregarUsuarios(Integer clave, Usuario p){
        String mensaje= " ";

        if(listaUsuario==null){
            this.listaUsuario = new HashMap<>();
        }
        listaUsuario.put(clave, p);

        return mensaje="Usuario agregado exitosamente al sistema";
    }

    public String eliminarUsuario(Integer clave){
        String mensaje=" ";
        if(listaUsuario.containsKey(clave)){
            listaUsuario.remove(clave);
            mensaje="Usuario removido exitosamente del sistema";
        }else{
            mensaje="El usuario que intenta remover no existe en el sistema";
        }

        return mensaje;
    }

    public boolean actualizarUsuario(Integer clave, Usuario u){

        if(listaUsuario.containsKey(clave) && (clave == u.getIdUsuario())){

            listaUsuario.put(clave, u);

            return true;
        }

        return false;
    }

    public void mostrarColeccion(){

        System.out.println(listaUsuario);

    }

    public boolean buscarUsuario(Integer clave){

        if (listaUsuario.containsKey(clave)) {
            System.out.println("Usuario encontrado: ");
            System.out.println(listaUsuario.get(clave));
            return true;
        }

        return false;

    }






}
