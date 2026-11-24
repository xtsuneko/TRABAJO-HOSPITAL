package COLECCION;
import POJO.Usuario;
import java.util.HashSet;
import java.util.Set;

public class UsuarioColeccion {


    Set<Usuario> listaUsuario;

    public UsuarioColeccion() {
        this.listaUsuario = new HashSet<>();
    }

    public String agregarUsuarios(Usuario u){
        String mensaje= " ";

        if(listaUsuario==null){
            this.listaUsuario = new HashSet<>();
        }
        listaUsuario.add(u);

        return mensaje="Usuario agregado exitosamente al sistema";
    }

    public String eliminarUsuario(Usuario u){
        String mensaje=" ";
        if(listaUsuario.contains(u)){
            listaUsuario.remove(u);
            mensaje="Usuario removido exitosamente del sistema";
        }else{
            mensaje="El usuario que intenta remover no existe en el sistema";
        }

        return mensaje;
    }

    public boolean actualizarUsuario(Usuario u){

        Usuario uAux = new Usuario(u.getId(), u.getEmail(), u.getPassword(), u.getDniPersona());

        if(listaUsuario.contains(u)){

            listaUsuario.remove(u);
            listaUsuario.add(uAux);

            return true;
        }

        return false;
    }

    public void mostrarColeccion(){

        System.out.println(listaUsuario);

    }

    public boolean buscarUsuario(Integer id){


        for(Usuario u : listaUsuario){
            if(u.getId()==id){
                System.out.println("Usuario encontrado: ");
                System.out.println(u);
                return true;
            }
        }

        return false;

    }


}
