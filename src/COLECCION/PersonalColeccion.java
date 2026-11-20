package COLECCION;

import POJO.Personal;

import java.util.HashMap;
import java.util.Map;

public class PersonalColeccion {

    Map<Integer, Personal> listaPersonal;

    public PersonalColeccion() {
        this.listaPersonal = new HashMap<>();
    }

    public String agregarPersonal(Integer clave, Personal p){
        String mensaje= " ";

        if(listaPersonal==null){
            this.listaPersonal = new HashMap<>();
        }
        listaPersonal.put(clave, p);

        return mensaje="Personal agregado exitosamente al sistema";
    }

    public String eliminarPersonal(Integer clave){
        String mensaje=" ";
        if(listaPersonal.containsKey(clave)){
            listaPersonal.remove(clave);
            mensaje="Personal removido exitosamente del sistema";
        }else{
            mensaje="El Personal que intenta remover no existe en el sistema";
        }

        return mensaje;
    }

    public boolean actualizarPersonal(Integer clave, Personal u){

        if(listaPersonal.containsKey(clave) && (clave == u.getIdPersonal())){

            listaPersonal.put(clave, u);

            return true;
        }

        return false;
    }

    public void mostrarColeccion(){

        System.out.println(listaPersonal);

    }

    public boolean buscarPersonal(Integer clave){

        if (listaPersonal.containsKey(clave)) {
            System.out.println("Personal encontrado: ");
            System.out.println(listaPersonal.get(clave));
            return true;
        }

        return false;

    }
    
    
    
    
    
    
    
}
