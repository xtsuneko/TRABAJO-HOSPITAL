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

        if (p == null) {
            throw new ParametroNullException("El personal es NULO");
        }
        if (p.getIdPersonal() <= 0) {
            throw new ParametroInvalidoException("El id de personal es invalido");
        }

        if (dao.findBYID(p.getIdPersonal()) == null) {
            throw new ParametroInvalidoException("El personal no existe");
        }

        boolean daoBool = dao.eliminar(p);
        if (daoBool) {
            coleccionPl.eliminarPersonal(p.getIdPersonal());
        }

        return daoBool;
    }

    public Personal findPersonalByID(Integer id){

        if(id == null){
            throw new ParametroNullException("El ID es NULO");
        }

        boolean coleccionBool= coleccionPl.buscarPersonal(id);
        if(!coleccionBool){
            Personal pl = dao.findBYID(id);
            return pl;
        }

        return null;
    }

    public Personal findPersonalByIDUsuario(Integer idU) {

        if (idU == null) {
            throw new ParametroNullException("El ID es NULO");
        }
        if (idU <= 0) {
            throw new ParametroInvalidoException("El id de usuario es invalido");
        }

        return dao.findBYIDUsuario(idU);
    }

    public void listarPersonal() {

        ArrayList<Personal> pl = dao.listarPersonal();
        if (pl == null || pl.isEmpty()) {
            System.out.println("No se encontro personal en el sistema");
        }

        for (Personal ppl : pl) {
            coleccionPl.agregarPersonal(ppl.getIdPersonal(), ppl);
        }
        coleccionPl.mostrarColeccion();
    }

    public void listarMedicos() {

        ArrayList<Personal> pl = dao.listarPersonal();
        if(pl==null || pl.isEmpty()){
            System.out.println("No se encontraron medicos en el sistema");
        }
        System.out.println(pl);

        //TODO llenar la informacion de personal (persona)
    }

}
