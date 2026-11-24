package Service;
import COLECCION.RevisionColeccion;
import DAO.RevisionDao;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Revision;
import POJO.Turno;
import UTIL.Validador;

import java.sql.SQLException;
import java.util.ArrayList;

public class RevisionService {

    RevisionDao dao;
    RevisionColeccion coleccionR;

    public RevisionService() {
        this.dao = new RevisionDao();
        this.coleccionR = new RevisionColeccion();
    }

    public int crearRevision(Revision r) {

        Validador.noNulo(r,"La revision es NULA ");

        Validador.idValido(r.getIdTurno(),"El ID de la revision es invalido");

        Validador.noNulo(r.getDiagnostico(),"El diagnostico es NULO");
        if(r.getDiagnostico().trim().isEmpty()){
            throw new ParametroInvalidoException("El diagnostico no puede estar vacio");
        }

        Validador.noNulo(r.getTratamiento(),"El tratamiento es NULO");
        if(r.getTratamiento().trim().isEmpty()){
            throw new ParametroInvalidoException("El tratamiento no puede estar vacio");
        }

        //TODO fijarse si hay mas validaciones

        int idAux = dao.insertarRevision(r);
        if (idAux != -1) {
            r.setId(idAux);
            coleccionR.agregarRevisiones(r);
        }

        return idAux;
    }

    public Boolean modificarRevision(Revision r) {
        Validador.noNulo(r,"La ficha medica es NULA");
        Validador.idValido(r.getId(),"EL ID de la revision es invalido");
        Validador.idValido(r.getIdTurno(),"EL ID de la ficha medica es invalido");

        Validador.noNulo(r.getDiagnostico(),"El diagnostico es NULO");
        if(r.getDiagnostico().trim().isEmpty()){
            throw new ParametroInvalidoException("El diagnostico no puede estar vacio");
        }

        Validador.noNulo(r.getTratamiento(),"El tratamiento es NULO");
        if(r.getTratamiento().trim().isEmpty()){
            throw new ParametroInvalidoException("El tratamiento no puede estar vacio");
        }
        //TODO fijarse si hay mas validaciones

        Boolean daoBool = dao.modificar(r);
        if (daoBool == true) {
            coleccionR.actualizarRevision(r);
        }

        return daoBool;
    }

    public Boolean eliminar(Revision r) throws SQLException {

        Validador.noNulo(r,"La revision no existe");

        Validador.idValido(r.getId(),"El ID es invalido");

        Revision revisionEnBD = dao.findBYID(r.getId());
        if (revisionEnBD == null) {
            throw new ParametroInvalidoException("No existe una revision con el ID: " + r.getId());
        }
        if (!revisionEnBD.isActivo()) {
            throw new ParametroInvalidoException("La revision ya se encuentra dada de baja");
        }

        boolean daoBool = dao.eliminar(revisionEnBD);
        if (daoBool) {
            coleccionR.eliminarRevision(revisionEnBD);
        }
        return daoBool;
    }

    public Revision findRevisionByID(Integer id) {
        Validador.noNulo(id, "El ID es NULO");
        Validador.idValido(id, "El ID es invalido");

        boolean coleccionBool = coleccionR.buscarRevision(id);
        if (!coleccionBool) {
            Revision r = dao.findBYID(id);
            if (r == null) {
                throw new ParametroInvalidoException("No se encontro una revision con el ID: " + id);
            }
            return r;
        }
        // Si coleccionR.buscarRevision(id) devuelve true pero no devuelve el objeto,
        // se recupera desde el DAO para no perder el resultado.
        Revision r = dao.findBYID(id);
        if (r == null) {
            throw new ParametroInvalidoException("No se encontro una revision con el ID: " + id);
        }
        return r;
    }


    public Revision findRevisionByIDPorVeterinario(Integer id){

        Validador.noNulo(id, "El ID es NULO");
        Validador.idValido(id,"El ID es invalido");

        return dao.findBYIDPorVeterinario(id);
    }

    public void listarFichasPorCliente(int id) {

        Validador.idValido(id, "El ID "+id+" es invalido");

        ArrayList<Revision> r = dao.listarFichasPorCliente(id);

        if (r == null || r.isEmpty()) {
            System.out.println("No se encontraron fichas en el sistema");
        } else
            System.out.println(r);
    }

    public void listarRevisiones() {

        ArrayList<Revision> r = dao.listarRevisiones();
        if (r == null || r.isEmpty()) {
            System.out.println("No se encontro revisiones en el sistema");
        } else {
            System.out.println(r);
        }
    }
}
