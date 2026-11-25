package COLECCION;
import POJO.Revision;
import POJO.Turno;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RevisionColeccion {

    Set<Revision> listaRevision;

    public RevisionColeccion() {
        this.listaRevision = new HashSet<>();
    }

    public String agregarRevisiones(Revision r){
        String mensaje= " ";

        if(listaRevision==null){
            this.listaRevision = new HashSet<>();
        }
        listaRevision.add(r);

        return mensaje="Ficha medica agregada exitosamente al sistema";
    }

    public String eliminarRevision(Revision r){
        String mensaje=" ";
        if(listaRevision.contains(r)){
            listaRevision.remove(r);
            mensaje="Ficha medica removida exitosamente del sistema";
        }else{
            mensaje="La ficha medica que intenta remover no existe en el sistema";
        }

        return mensaje;
    }

    public boolean actualizarRevision(Revision r){

        Revision rAux = new Revision(r.getIdTurno(), r.getDiagnostico(), r.getTratamiento());

        if(listaRevision.contains(r)){

            listaRevision.remove(r);
            listaRevision.add(rAux);

            return true;
        }

        return false;
    }

    public void mostrarColeccion(){

        System.out.println(listaRevision);

    }

    public boolean buscarRevision(Integer id){


        for(Revision r : listaRevision){
            if(Objects.equals(r.getId(), id)){
                System.out.println("Ficha medica encontrada: ");
                System.out.println(r);
                return true;
            }
        }

        return false;

    }



}
