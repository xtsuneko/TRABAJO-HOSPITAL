package COLECCION;
import POJO.Persona;

import java.util.HashMap;
import java.util.Map;

public class PersonaColeccion {

    Map<String, Persona> listaPersonas;

    public PersonaColeccion() {
        this.listaPersonas = new HashMap<>();
    }

    public String agregarPersonas(String clave, Persona p){
        String mensaje= " ";

        if(listaPersonas==null){
            this.listaPersonas = new HashMap<>();
        }
        listaPersonas.put(clave, p);

        return mensaje="Persona agregada exitosamente al sistema";
    }

    public String eliminarPersonas(String clave){
        String mensaje=" ";
        if(listaPersonas.containsKey(clave)){
            listaPersonas.remove(clave);
            mensaje="Persona removida exitosamente del sistema";
        }else{
            mensaje="La persona que intenta remover no existe en el sistema";
        }

     return mensaje;
    }

    public boolean actualizarPersona(String clave, Persona p){

        if(listaPersonas.containsKey(clave) && (clave.equals(p.getDni()))){

            listaPersonas.put(clave, p);

            return true;
        }

        return false;
    }

    public void mostrarColeccion(){

        System.out.println(listaPersonas);

    }

    public boolean buscarPersona(String clave){

        if (listaPersonas.containsKey(clave)) {
            System.out.println("Persona encontrada: ");
            System.out.println(listaPersonas.get(clave));
           return true;
        }

        return false;

    }

}
