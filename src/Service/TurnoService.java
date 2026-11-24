package Service;
import DAO.TurnoDao;
import EXCEPCIONES.ParametroNullException;
import POJO.Turno;
import java.sql.SQLException;
import java.util.ArrayList;

public class TurnoService {

    TurnoDao dao;

    public TurnoService() {
        this.dao = new TurnoDao();
    }

    public int crearTurno(Turno t){
        if (t == null) {
            throw new ParametroNullException("El turno es NULO");
        }
        if(t.getFechaTurno() == null){
            throw new ParametroNullException("La fecha es NULA");
        }
        if(t.getHoraTurno() == null){
            throw new ParametroNullException("El horario es NULO");
        }
        if(t.getMotivoConsulta() == null){
            throw new ParametroNullException("El motivo es NULO");
        }

        //TODO hacer validaciones con la fecha y el horario
        //No puede tener mas de un cliente asociado al turno

        return dao.insertarTurno(t);
    }

    public Boolean modificarTurno(Turno t) {
        if (t == null) {
            throw new ParametroNullException("El turno es NULO");
        }
        if (t.getFechaTurno() == null) {
            throw new ParametroNullException("La fecha es NULA");
        }
        if (t.getHoraTurno() == null) {
            throw new ParametroNullException("El horario es NULO");
        }
        if (t.getMotivoConsulta() == null) {
            throw new ParametroNullException("El motivo es NULO");
        }
        //TODO hacer validaciones con la fecha y el horario

        return dao.modificar(t);
    }

    public Boolean eliminar(Turno t) throws SQLException {

        //TODO no tiene que ser NULL, TIENE Q EXISTIR


        return dao.eliminar(t);
    }

    public Turno findTurnoByID(Integer id){

        if(id == null){
            throw new ParametroNullException("El ID es NULO");
        }


        return dao.findBYID(id);
    }

    public void findTurnoByIDVeterinario(Integer id){

        ArrayList<Turno> t = dao.findBYIDVeterinario(id);
        if(t ==null || t.isEmpty()){
            System.out.println("No se encontro turno en el sistema");
        }

        System.out.println(t);
    }

    public Turno findTurnoByIDCliente(Integer id){

        if(id == null){
            throw new ParametroNullException("El ID es NULO");
        }


        return dao.findBYIDCliente(id);
    }

    public Turno findTurnoByIDYIDCliente(Integer id, Integer idCliente){

        if(id == null){
            throw new ParametroNullException("El ID es NULO");
        }
        if(idCliente == null){
            throw new ParametroNullException("El ID del cliente es NULO");
        }


        return dao.findBYIDYIDCliente(id, idCliente);
    }

    public void listarTurnosPorCliente(Integer idCliente) {

        ArrayList<Turno> t = dao.listarTurnosPorCliente(idCliente);
        if(t ==null || t.isEmpty()){
            System.out.println("No se encontro cliente en el sistema");
        }

        System.out.println(t);
    }

    public void listarTurnos() {

        ArrayList<Turno> t = dao.listarTurnos();
        if(t ==null || t.isEmpty()){
            System.out.println("No se encontro personal en el sistema");
        }

        System.out.println(t);
    }

}
