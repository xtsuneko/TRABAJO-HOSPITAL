package Service;
import DAO.RevisionDao;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Revision;
import java.sql.SQLException;
import java.util.ArrayList;

public class RevisionService {

    RevisionDao dao;

    public RevisionService() {
        this.dao = new RevisionDao();
    }

    public int crearRevision(Revision r){
        if (r == null) {
            throw new ParametroNullException("La revision es NULA");
        }
        if(r.getIdTurno()<= 0){
            throw new ParametroInvalidoException("El id de la revision es invalido");
        }
        if(r.getDiagnostico() == null){
            throw new ParametroNullException("El diagnostico es NULO");
        }
        if(r.getTratamiento() == null){
            throw new ParametroNullException("El tratamiento es NULO");
        }

        //TODO fijarse si hay mas validaciones

        return dao.insertarRevision(r);
    }

    public Boolean modificarRevision(Revision r) {
        if (r == null) {
            throw new ParametroNullException("La revision es NULA");
        }
        if(r.getIdTurno()<= 0){
            throw new ParametroInvalidoException("El id de la revision es invalido");
        }
        if(r.getDiagnostico() == null){
            throw new ParametroNullException("El diagnostico es NULO");
        }
        if(r.getTratamiento() == null){
            throw new ParametroNullException("El tratamiento es NULO");
        }
        //TODO fijarse si hay mas validaciones

        return dao.modificar(r);
    }

    public Boolean eliminar(Revision r) throws SQLException {

        //TODO no tiene que ser NULL, TIENE Q EXISTIR


        return dao.eliminar(r);
    }

    public Revision findRevisionByID(Integer id){

        if(id == null){
            throw new ParametroNullException("El ID es NULO");
        }

        return dao.findBYID(id);
    }

    public Revision findRevisionByIDPorVeterinario(Integer id){

        if(id == null){
            throw new ParametroNullException("El ID es NULO");
        }

        return dao.findBYIDPorVeterinario(id);
    }

    public void listarFichasPorCliente(int id) {

        ArrayList<Revision> r = dao.listarFichasPorCliente(id);
        if(r ==null || r.isEmpty()){
            System.out.println("No se encontraron fichas en el sistema");
        }

        System.out.println(r);
    }

    public void listarRevisiones() {

        ArrayList<Revision> r = dao.listarRevisiones();
        if(r ==null || r.isEmpty()){
            System.out.println("No se encontro revisiones en el sistema");
        }

        System.out.println(r);
    }

}
