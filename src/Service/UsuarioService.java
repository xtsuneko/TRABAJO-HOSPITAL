package Service;
import DAO.UsuarioDao;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Usuario;

import java.sql.SQLException;

public class UsuarioService {


    UsuarioDao dao;

    public UsuarioService() {
        this.dao = new UsuarioDao();
    }

    public int crearUsuario(Usuario u) {
        if (u == null) {
            throw new ParametroNullException("El usuario es NULO");
        }
        if (u.getEmail() == null) {
            throw new ParametroNullException("El email es NULO");
        }
        if (u.getPassword() == null) {
            throw new ParametroNullException("La contraseña es NULA");
        }
        if (u.getDni() == null) {
            throw new ParametroNullException("El dni es NULO");
        }
        if (u.getPassword().length() < 5 || u.getPassword().length() > 8) {
            throw new ParametroInvalidoException("La contraseña debe tener entre 5 y 8 digitos");
        }
        Boolean tieneLetra = false;
        Boolean tieneNumero = false;

        for(char letra : u.getPassword().toUpperCase().toCharArray()) {
            if ((letra >= 'A' && letra <= 'Z')) {
                tieneLetra=true;
            }
            if((letra>='0' && letra<='9')){
                tieneNumero=true;
            }
        }
        if(!(tieneLetra && tieneNumero)){
            throw new ParametroInvalidoException("La contraseña tiene que tener al menos una letra y un numero");
        }
        if(!(u.getEmail().contains("@"))){
            throw new ParametroInvalidoException("El email debe tener un @");
        }
        if(!(u.getEmail().endsWith(".com"))){
            throw new ParametroInvalidoException("El email debe terminar con \".com\" ");
        }

        //TODO que tenga 5 digitos ademas del @gmail.com
        //TODO el mail no se puede repetir
        //TODO el dni no se puede repetir

//EMAIL:

        //tiene que tener al menos @gmail.com
        //no se puede repetir, si ya esta registrado avisar
        //
//CONTRASEÑA
        //Al menos 1 numero y 1 letra
        //entre 5 y 8 caracteres


        return dao.insertar(u);
    }


    public Boolean modificar(Usuario u){
        if (u == null) {
            throw new ParametroNullException("El usuario es NULO");
        }
        if (u.getEmail() == null) {
            throw new ParametroNullException("El email es NULO");
        }
        if (u.getPassword() == null) {
            throw new ParametroNullException("La contraseña es NULA");
        }
        if (u.getDni() == null) {
            throw new ParametroNullException("El dni es NULO");
        }
        if (u.getPassword().length() < 5 || u.getPassword().length() > 8) {
            throw new ParametroInvalidoException("La contraseña debe tener entre 5 y 8 digitos");
        }
        Boolean tieneLetra = false;
        Boolean tieneNumero = false;

        for(char letra : u.getPassword().toUpperCase().toCharArray()) {
            if ((letra >= 'A' && letra <= 'Z')) {
                tieneLetra=true;
            }
            if((letra>='0' && letra<='9')){
                tieneNumero=true;
            }
        }
        if(!(tieneLetra && tieneNumero)){
            throw new ParametroInvalidoException("La contraseña tiene que tener al menos una letra y un numero");
        }
        if(!(u.getEmail().contains("@"))){
            throw new ParametroInvalidoException("El email debe tener un @");
        }
        if(!(u.getEmail().endsWith(".com"))){
            throw new ParametroInvalidoException("El email debe terminar con \".com\" ");
        }

        //TODO que tenga 5 digitos ademas del @gmail.com
        //TODO el mail no se puede repetir
        //TODO el dni no se puede repetir

        return dao.modificar(u);
    }

    public Boolean eliminar(Usuario u) throws SQLException {

        //TODO no tiene que ser NULL, TIENE Q EXISTIR

        return dao.eliminar(u);
    }


}
