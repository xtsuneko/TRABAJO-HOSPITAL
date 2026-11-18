package Service;
import DAO.PersonalDao;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Personal;

public class PersonalService {

    PersonalDao dao;

    public PersonalService() {
        this.dao = new PersonalDao();
    }

    public int crearPersonal(Personal p) {
        if (p == null) {
            throw new ParametroNullException("El personal es NULO");
        }
        if (p.getSueldo() == null){
            throw new ParametroNullException("El sueldo es NULO");
        }
        if(p.getHorarioLaboral() == null){
            throw new ParametroNullException("El horario laboral es NULO");
        }
        if(p.getIdUsuario() <= 0){
            throw new ParametroInvalidoException("El id no puede ser menor que 0");
        }
        if(!(p.getSueldo()>0.0)){
                throw new ParametroInvalidoException("El sueldo debe ser positivo");
        }

       return dao.insertar(p);

    }

}
