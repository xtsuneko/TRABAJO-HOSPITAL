package Service;
import COLECCION.TurnoColeccion;
import DAO.TurnoDao;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Turno;
import POJO.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class TurnoService {

    TurnoDao dao;
    TurnoColeccion coleccionT;

    public TurnoService() {
        this.dao = new TurnoDao();
        this.coleccionT = new TurnoColeccion();
    }

    public int crearTurno(Turno t) {
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
        //No puede tener mas de un cliente asociado al turno

        int idAux = dao.insertarTurno(t);
        if (idAux != -1) {
            coleccionT.agregarTurno(t);
        }

        return idAux;
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

        Boolean daoBool = dao.modificar(t);
        if (daoBool == true) {
            coleccionT.actualizarTurno(t);
        }

        return daoBool;
    }

    public Boolean eliminar(Turno t) throws SQLException {

        //TODO no tiene que ser NULL, TIENE Q EXISTIR

        boolean daoBool = dao.eliminar(t);
        if (daoBool) {
            coleccionT.eliminarTurno(t);
        }

        return daoBool;
    }

    public Turno findTurnoByID(Integer id) {

        if (id == null) {
            throw new ParametroNullException("El ID es NULO");
        }

        boolean coleccionBool = coleccionT.buscarTurno(id);
        if (!coleccionBool) {
            Turno t = dao.findBYID(id);
            return t;
        }

        return null;
    }
        public void findTurnoByIDMedico (Integer id){

            ArrayList<Turno> t = dao.findBYIDMedico(id);
            if (t == null || t.isEmpty()) {
                System.out.println("No se encontro turno en el sistema");
            }else {
                for (Turno tt : t) {
                    coleccionT.buscarTurnoPorIDMedico(id);
                }
            }
            System.out.println(t);
        }

        public Turno findTurnoByIDCliente (Integer id){

            if (id == null) {
                throw new ParametroNullException("El ID es NULO");
            }

            boolean coleccionBool= coleccionT.buscarTurnoPorIDCliente(id);
            if(!coleccionBool){
                return dao.findBYIDCliente(id);
            }
            return null;

        }

        public Turno findTurnoByIDYIDCliente (Integer id, Integer idCliente){

            if (id == null) {
                throw new ParametroNullException("El ID es NULO");
            }
            if (idCliente == null) {
                throw new ParametroNullException("El ID del cliente es NULO");
            }

            return dao.findBYIDYIDCliente(id, idCliente);
        }

        public void listarTurnosPorCliente (Integer idCliente){

            ArrayList<Turno> t = dao.listarTurnosPorCliente(idCliente);
            if (t == null || t.isEmpty()) {
                System.out.println("No se encontro cliente en el sistema");
            }else {
                for (Turno tt : t) {
                    coleccionT.agregarTurno(tt);
                }
            }
            System.out.println(t);
        }

        public void listarTurnos () {

            ArrayList<Turno> t = dao.listarTurnos();
            if (t == null || t.isEmpty()) {
                System.out.println("No se encontraron turnos en el sistema");
            }else {
                for (Turno tt : t) {
                    coleccionT.agregarTurno(tt);
                }
            }
            System.out.println(t);
        }


}
