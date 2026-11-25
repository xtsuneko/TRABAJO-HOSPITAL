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

    public void crearPersona(Persona p){
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
        for(char letra : p.getNombre().toUpperCase().toCharArray()){
            if(!(letra >= 'A' && letra <='Z')){
                throw new ParametroInvalidoException("El nombre solo puede contener letras");
            }
        }
        for(char letra : p.getApellido().toUpperCase().toCharArray()){
            if(!(letra >= 'A' && letra <='Z')){
                throw new ParametroInvalidoException("El apellido solo puede contener letras");
            }
        }

        if (dao.findBYDNI(p.getDni()) != null) {
            throw new ParametroInvalidoException("Ya existe una persona con ese DNI");
        }

        dao.insertar(p);
        coleccionP.agregarPersonas(p.getDni(), p);
    }

    public boolean modificar(Persona p){
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
        for(char letra : p.getNombre().toUpperCase().toCharArray()){
            if(!(letra >= 'A' && letra <='Z')){
                throw new ParametroInvalidoException("El nombre solo puede contener letras");
            }
        }
        for(char letra : p.getApellido().toUpperCase().toCharArray()){
            if(!(letra >= 'A' && letra <='Z')){
                throw new ParametroInvalidoException("El apellido solo puede contener letras");
            }
        }

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

        boolean coleccionBool= coleccionP.buscarPersona(dni);
        if(coleccionBool) {
            Persona p = dao.findBYDNI(dni);
            return p;
        }
        return null;
    }

    public Persona findPersonaByIDUsuario(int idUsuario){
        if(idUsuario <= 0){
            throw new ParametroInvalidoException("El id es invalido");
        }

        return dao.findBYIDUsuario(idUsuario);
    }

    public void listarPersona() {

        ArrayList<Persona> p = dao.listarPersonas();
        for (Persona pp : p) {
            coleccionP.agregarPersonas(pp.getDni(), pp);
        }

    }




}
