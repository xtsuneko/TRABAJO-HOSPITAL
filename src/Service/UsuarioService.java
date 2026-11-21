package Service;
import COLECCION.UsuarioColeccion;
import DAO.UsuarioDao;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Persona;
import POJO.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioService {


    UsuarioDao dao;
    UsuarioColeccion coleccionU;

    public UsuarioService() {
        this.dao = new UsuarioDao();
        this.coleccionU = new UsuarioColeccion();
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
        if (u.getDniPersona() == null) {
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

        int idAux= dao.insertar(u);
        if(idAux!=-1){
            coleccionU.agregarUsuarios(u.getIdUsuario(), u);
        }

        return idAux;
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
        if (u.getDniPersona() == null) {
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

        Boolean daoBool= dao.modificar(u);
        if(daoBool==true){
            coleccionU.actualizarUsuario(u.getIdUsuario(), u);
        }

        return daoBool;
    }

    public Boolean eliminar(Usuario u) throws SQLException {

        //TODO no tiene que ser NULL, TIENE Q EXISTIR

        boolean daoBool= dao.eliminar(u);
        if(daoBool){
            coleccionU.eliminarUsuario(u.getIdUsuario());
        }

        return daoBool;
    }

    public Usuario findUsuarioByID(Integer id){

        if(id == null){
            throw new ParametroNullException("El ID es NULO");
        }

        boolean coleccionBool= coleccionU.buscarUsuario(id);
        if(!coleccionBool){
            Usuario u = dao.findBYID(id);
           return u;
        }

        return null;
    }


    public Usuario findUsuarioByEmailPassword(String email, String password){

        if(email == null){
            throw new ParametroNullException("El email es NULO");
        }
        if(password == null){
            throw new ParametroNullException("La password es NULA");
        }

        //TODO boolean coleccionBool= coleccionU.buscarUsuario(id);
        /* if(!coleccionBool){
            Usuario u = dao.findBYID(id);
            return u;
        }*/

        return dao.findBYEmailPassword(email, password);
    }



    public void listarUsuario() {

        ArrayList<Usuario> u = dao.listarUsuarios();
        for (Usuario uu : u) {
            coleccionU.agregarUsuarios(uu.getIdUsuario(), uu);
        }

    }



}
