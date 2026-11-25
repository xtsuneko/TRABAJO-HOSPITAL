package Service;
import COLECCION.TurnoColeccion;
import DAO.TurnoDao;
import EXCEPCIONES.ParametroInvalidoException;
import POJO.Turno;
import UTIL.Validador;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TurnoService {

    TurnoDao dao;
    TurnoColeccion coleccionT;

    public TurnoService() {
        this.dao = new TurnoDao();
        this.coleccionT = new TurnoColeccion();
    }

    public int crearTurno(Turno t) {


        Validador.noNulo(t, "El turno es NULO");
        Validador.idValido(t.getIdCliente(), "El ID del cliente es invalido");
        Validador.idValido(t.getIdPersonal(), "El ID del medico/personal es invalido");
        Validador.noNulo(t.getFechaTurno(), "La fecha es NULA");
        Validador.noNulo(t.getHoraTurno(), "No se puede crear un turno en un horario NULO");
        Validador.noNulo(t.getMotivoConsulta(), "El motivo del turno no puede ser NULO");

        if (t.getMotivoConsulta().trim().isEmpty()) {
            throw new ParametroInvalidoException("El motivo de la consulta no puede estar vacio");
        }

        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();

        if (t.getFechaTurno().isBefore(hoy)) {
            throw new ParametroInvalidoException("No se puede crear un turno en una fecha pasada");
        }
        if (t.getFechaTurno().isEqual(hoy) && t.getHoraTurno().isBefore(ahora)) {
            throw new ParametroInvalidoException("No se puede crear un turno en un horario ya pasado");
        }

        validarMedicoDisponible(t, 0);

        t.setActivo(true);

        int idAux = dao.insertarTurno(t);
        if (idAux != -1) {
            t.setId(idAux);
            coleccionT.agregarTurno(t);
        }

        return idAux;
    }

    public Boolean modificarTurno(Turno t) {

        Validador.noNulo(t, "El turno es NULO");
        Validador.idValido(t.getId(),"El ID del turno es invalido");
        Validador.idValido(t.getIdCliente(), "El ID del cliente es invalido");
        Validador.idValido(t.getIdPersonal(), "El ID del medico/personal es invalido");
        Validador.noNulo(t.getFechaTurno(), "La fecha es NULA");
        Validador.noNulo(t.getHoraTurno(), "No se puede crear un turno en un horario NULO");
        Validador.noNulo(t.getMotivoConsulta(), "El motivo del turno no puede ser NULO");

        if (t.getMotivoConsulta().trim().isEmpty()) {
            throw new ParametroInvalidoException("El motivo de consulta no puede estar vacio");
        }

        Turno existente = dao.findBYID(t.getId());
        if (existente == null) {
            throw new ParametroInvalidoException("No existe un turno activo con el ID: " + t.getId());
        }
        if (!existente.isActivo()) {
            throw new ParametroInvalidoException("No se puede modificar un turno dado de baja");
        }

        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();

        if (t.getFechaTurno().isBefore(hoy)) {
            throw new ParametroInvalidoException("No se puede mover el turno a una fecha pasada");
        }
        if (t.getFechaTurno().isEqual(hoy) && t.getHoraTurno().isBefore(ahora)) {
            throw new ParametroInvalidoException("No se puede mover el turno a un horario ya pasado");
        }

        validarMedicoDisponible(t, t.getId());

        Boolean daoBool = dao.modificar(t);
        if (daoBool == true) {
            coleccionT.actualizarTurno(t);
        }

        return daoBool;
    }

    private void validarMedicoDisponible(Turno t, int idTurnoExcluido) {

        PersonalService servicePl = new PersonalService();
        if (servicePl.findMedicoByID(t.getIdPersonal()) == null) {
            throw new ParametroInvalidoException("El ID ingresado no corresponde a un medico");
        }

        if (dao.existeTurnoMedico(t.getIdPersonal(), t.getFechaTurno(), t.getHoraTurno(), idTurnoExcluido)) {
            throw new ParametroInvalidoException("El medico ya tiene un turno asignado en esa fecha y horario");
        }
    }

    public Boolean eliminar(Turno t) throws SQLException {

        Validador.noNulo(t, "El turno es NULO");
        Validador.noNulo(t.getId(), "El ID del turno es NULO");
        Validador.idValido(t.getId(), "El ID del turno es invalido");

        // Verifica existencia y estado activo real en base de datos antes de la baja logica
        Turno turnoEnBD = dao.findBYID(t.getId());
        if (turnoEnBD == null) {
            throw new ParametroInvalidoException("No existe un turno con el ID: " + t.getId());
        }
        if (!turnoEnBD.isActivo()) {
            throw new ParametroInvalidoException("El turno ya se encuentra dado de baja");
        }

        boolean daoBool = dao.eliminar(turnoEnBD);
        if (daoBool) {
            coleccionT.eliminarTurno(turnoEnBD);
        }

        return daoBool;
    }

    public Turno findTurnoByID(Integer id) {

        Validador.noNulo(id, "El ID es NULO");
        Validador.idValido(id, "El ID del turno es invalido");

        Turno t = dao.findBYID(id);
        Validador.noNulo(t, "No se encontro un turno con el ID: " + id);
        return t;
    }


    public void findTurnoByIDMedico(Integer id) {

        Validador.noNulo(id, "El ID del medico es NULO");
        Validador.idValido(id, "El ID del medico es invalido");

        ArrayList<Turno> t = dao.findBYIDMedico(id);
        if (t == null || t.isEmpty()) {
            System.out.println("No se encontraron turnos con el medico con ID: "+id);
        } else {
            for (Turno turno : t) {
                System.out.println(turno);
            }
        }
    }

    public Turno findTurnoByIDCliente(Integer id) {

        Validador.noNulo(id, "El ID del cliente es NULO");
        Validador.idValido(id, "El ID del cliente es invalido");

        Turno t = dao.findBYIDCliente(id);
        Validador.noNulo(t, "No se encontro un turno para el cliente con ID: " + id);
        return t;
    }

    public Turno findTurnoByIDYIDCliente(Integer id, Integer idCliente) {

        Validador.noNulo(id, "El ID del turno es NULO");
        Validador.idValido(id, "El ID del turno es invalido");
        Validador.noNulo(idCliente, "El ID del cliente es NULO");
        Validador.idValido(idCliente, "El ID del cliente es invalido");

        Turno t = dao.findBYIDYIDCliente(id, idCliente);
        Validador.noNulo(
                t,
                "No se encontro el turno con ID " + id + " para el cliente con ID " + idCliente
        );
        return t;
    }

    public void listarTurnosPorCliente(Integer idCliente) {

        Validador.noNulo(idCliente, "El ID del cliente es NULO");
        Validador.idValido(idCliente, "El ID del cliente es invalido");

        ArrayList<Turno> t = dao.listarTurnosPorCliente(idCliente);
        if (t == null || t.isEmpty()) {
            System.out.println("No se encontraron turnos para el cliente");
        } else {
            for (Turno tt : t) {
                coleccionT.agregarTurno(tt);
            }
            System.out.println(t);
        }
    }

    public void listarTurnos() {

        ArrayList<Turno> t = dao.listarTurnos();
        if (t == null || t.isEmpty()) {
            System.out.println("No se encontraron turnos en el sistema");
        } else {
            for (Turno tt : t) {
                coleccionT.agregarTurno(tt);
            }
            System.out.println(t);
        }
    }
}