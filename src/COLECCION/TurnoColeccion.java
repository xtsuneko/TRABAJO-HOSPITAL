package COLECCION;
import POJO.Turno;
import POJO.Usuario;
import java.util.HashSet;
import java.util.Set;

public class TurnoColeccion {

    Set<Turno> listaTurno;

    public TurnoColeccion() {
        this.listaTurno = new HashSet<>();
    }

    public String agregarTurno(Turno t){
        String mensaje= " ";

        if(listaTurno==null){
            this.listaTurno = new HashSet<>();
        }
        listaTurno.add(t);

        return mensaje="Turno agregado exitosamente al sistema";
    }

    public String eliminarTurno(Turno t){
        String mensaje=" ";
        if(listaTurno.contains(t)){
            listaTurno.remove(t);
            mensaje="Turno removido exitosamente del sistema";
        }else{
            mensaje="El turno que intenta remover no existe en el sistema";
        }

        return mensaje;
    }

    public boolean actualizarTurno(Turno t){

        Turno tAux = new Turno(t.getIdCliente(), t.getIdPersonal(), t.getFechaTurno(), t.getHoraTurno(), t.getMotivoConsulta());

        if(listaTurno.contains(t)){

            listaTurno.remove(t);
            listaTurno.add(tAux);

            return true;
        }

        return false;
    }

    public void mostrarColeccion(){

        System.out.println(listaTurno);

    }

    public boolean buscarTurno(Integer id){


        for(Turno t : listaTurno){
            if(t.getId()==id){
                System.out.println("Turno encontrado: ");
                System.out.println(t);
                return true;
            }
        }

        return false;

    }

    public boolean buscarTurnoPorIDVeterinario(Integer id){

        for(Turno t : listaTurno){
            if(t.getIdPersonal()==id){
                System.out.println("Turno encontrado: ");
                System.out.println(t);
                return true;
            }
        }
        return false;
    }

    public boolean buscarTurnoPorIDCliente(Integer id){

        for(Turno t : listaTurno){
            if(t.getIdCliente()==id){
                System.out.println("Turno encontrado: ");
                System.out.println(t);
                return true;
            }
        }
        return false;
    }


}
