package Service;
import COLECCION.PersonaColeccion;
import DAO.PersonaDao;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Persona;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;


public class PersonaService {

    PersonaDao dao;
    PersonaColeccion coleccionP;

    public PersonaService() {
        this.dao = new PersonaDao();
        this.coleccionP = new PersonaColeccion();
    }

    public void validarPersona(Persona p){
        if(p == null){
            throw new ParametroNullException("La persona es NULA");
        }
        if(p.getNombre() == null){
            throw new ParametroNullException("El nombre es NULO");
        }
        if(p.getApellido() == null){
            throw new ParametroNullException("El apellido es NULO");
        }
        if(p.getTelefono() == null){
            throw new ParametroNullException("El telefono es NULO");
        }
        if(p.getDni() == null){
            throw new ParametroNullException("El DNI es NULO");
        }
        if(p.getDni().length()>8 || p.getDni().length()<7){
            throw new ParametroInvalidoException("El dni tiene que tener entre siete u ocho digitos");
        }
        for(char letra : p.getDni().toCharArray()){
            if(!(letra>='0' && letra<='9')){
                throw new ParametroInvalidoException("El dni solo puede contener numeros");
            }
        }
        if(p.getTelefono().length()!=10){
            throw new ParametroInvalidoException("El numero de telefono tiene que ser valido (Diez digitos)");
        }
        for(char letra : p.getTelefono().toCharArray()){
            if(!(letra>='0' && letra<='9')){
                throw new ParametroInvalidoException("El telefono solo puede contener numeros");
            }
        }
        if(p.getNombre().trim().isEmpty()){
            throw new ParametroInvalidoException("El nombre no puede estar vacio");
        }
        for(char letra : p.getNombre().toCharArray()){
            if(!(Character.isLetter(letra) || letra == ' ')){
                throw new ParametroInvalidoException("El nombre solo puede contener letras");
            }
        }
        if(p.getApellido().trim().isEmpty()){
            throw new ParametroInvalidoException("El apellido no puede estar vacio");
        }
        for(char letra : p.getApellido().toCharArray()){
            if(!(Character.isLetter(letra) || letra == ' ')){
                throw new ParametroInvalidoException("El apellido solo puede contener letras");
            }
        }
    }

    public void validarPersonaNueva(Persona p){
        validarPersona(p);

        if (dao.findBYDNI(p.getDni()) != null) {
            throw new ParametroInvalidoException("Ya existe una persona con ese DNI");
        }
    }

    public void crearPersona(Persona p){
        validarPersonaNueva(p);

        dao.insertar(p);
        coleccionP.agregarPersonas(p.getDni(), p);
    }

    public boolean modificar(Persona p){
        validarPersona(p);

        Boolean daoBool= dao.modificar(p);
        if(daoBool==true){
            coleccionP.actualizarPersona(p.getDni(), p);
        }

        return daoBool;
    }


    public boolean eliminar(Persona p) throws SQLException {

        if (p == null) {
            throw new ParametroNullException("La persona es NULA");
        }
        if (p.getDni() == null) {
            throw new ParametroNullException("El DNI es NULO");
        }

        if (dao.findBYDNI(p.getDni()) == null) {
            throw new ParametroInvalidoException("La persona no existe");
        }

        boolean daoBool= dao.eliminar(p);
        if(daoBool){
            coleccionP.eliminarPersonas(p.getDni());
        }
        return daoBool;
    }

    public Persona findPersonaByDNI(String dni){
        if(dni == null){
            throw new ParametroNullException("El dni es NULO");
        }

        Persona p = dao.findBYDNI(dni);
        if (p != null) {
            coleccionP.agregarPersonas(p.getDni(), p);
        }
        return p;
    }

    public Persona findPersonaByIDUsuario(int idUsuario){
        if(idUsuario <= 0){
            throw new ParametroInvalidoException("El id es invalido");
        }

        return dao.findBYIDUsuario(idUsuario);
    }

    public void listarPersona() {

        ArrayList<Persona> p = dao.listarPersonas();
        if (p == null) {
            return;
        }
        for (Persona pp : p) {
            coleccionP.agregarPersonas(pp.getDni(), pp);
        }

    }




}
