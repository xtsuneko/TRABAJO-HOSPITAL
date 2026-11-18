package Service;
import DAO.PersonaDao;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Persona;

import java.sql.SQLException;


public class PersonaService {

    PersonaDao dao;

    public PersonaService() {
        this.dao = new PersonaDao();
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
        //TODO AGREGAR VALIDACION DE Q NO SE REPITA EL DNI


        dao.insertar(p);
    }

    public Boolean modificar(Persona p){
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
        //TODO AGREGAR VALIDACION DE Q NO SE REPITA EL DNI

        return dao.modificar(p);
    }


    public Boolean eliminar(Persona p) throws SQLException {

        //TODO no tiene que ser NULL, TIENE Q EXISTIR

        return dao.eliminar(p);
    }


}
