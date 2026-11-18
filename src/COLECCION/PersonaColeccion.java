package COLECCION;
import POJO.Persona;

import java.util.HashMap;
import java.util.Map;

public class PersonaColeccion {

    Map<Integer, Persona> listaPersonas;

    public PersonaColeccion() {
        this.listaPersonas = new HashMap<>();
    }

    public String agregarPersonas(Integer clave, Persona p){
        String mensaje= " ";

        if(listaPersonas==null){
            this.listaPersonas = new HashMap<>();
        }
        listaPersonas.put(clave, p);

        return mensaje="Persona agregada exitosamente al sistema";
    }

    public String eliminarPersonas(Integer clave){
        String mensaje=" ";
        if(listaPersonas.containsKey(clave)){
            listaPersonas.remove(clave);
            mensaje="Persona removida exitosamente del sistema";
        }else{
            mensaje="La persona que intenta remover no existe en el sistema";
        }

     return mensaje;
    }





}
