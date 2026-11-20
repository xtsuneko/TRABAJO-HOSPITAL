package Service;
import COLECCION.PersonalColeccion;
import DAO.PersonalDao;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Persona;
import POJO.Personal;
import POJO.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class PersonalService {

    PersonalDao dao;
    PersonalColeccion coleccionPl;

    public PersonalService() {
        this.dao = new PersonalDao();
        this.coleccionPl = new PersonalColeccion();
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

        int idAux= dao.insertar(p);
        if(idAux!=-1){
            coleccionPl.agregarPersonal(p.getIdUsuario(), p);
        }

        return idAux;
    }

    public Boolean modificar(Personal p){
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


        Boolean daoBool= dao.modificar(p);
        if(daoBool==true){
            coleccionPl.actualizarPersonal(p.getIdPersonal(), p);
        }

        return daoBool;
    }


    public Boolean eliminar(Personal p) throws SQLException {

        //TODO no tiene que ser NULL, TIENE Q EXISTIR

        boolean daoBool= dao.eliminar(p);
        if(daoBool){
            coleccionPl.eliminarPersonal(p.getIdPersonal());
        }

        return daoBool;
    }

    public String findPersonalByID(Integer id){

        if(id == null){
            throw new ParametroNullException("El ID es NULO");
        }

        String mensaje=" ";
        boolean coleccionBool= coleccionPl.buscarPersonal(id);
        if(!coleccionBool){
            Personal pl = dao.findBYID(id);
            if(pl==null){
                mensaje= "No se encontro al personal en el sistema";
            }
            mensaje="Personal encontrado: ";
        }

        return mensaje;
    }

    public void listarPersonal() {

        ArrayList<Personal> pl = dao.listarPersonal();
        for (Personal ppl : pl) {
            coleccionPl.agregarPersonal(ppl.getIdUsuario(), ppl);
        }

    }



}
