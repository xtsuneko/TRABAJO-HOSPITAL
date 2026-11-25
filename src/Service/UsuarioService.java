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

    public void validarUsuario(Usuario u) {
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

        Usuario existente = dao.findBYEmail(u.getEmail());
        if (existente != null && !existente.getId().equals(u.getId())) {
            throw new ParametroInvalidoException("Ya existe un usuario registrado con ese email");
        }
    }

    public int crearUsuario(Usuario u) {
        validarUsuario(u);

        int idAux= dao.insertar(u);
        if(idAux!=-1){
            coleccionU.agregarUsuarios(u);
        }

        return idAux;
    }


    public Boolean modificar(Usuario u){
        validarUsuario(u);

        Boolean daoBool= dao.modificar(u);
        if(daoBool==true){
            coleccionU.actualizarUsuario(u);
        }

        return daoBool;
    }

    public Boolean eliminar(Usuario u) throws SQLException {

        if (u == null) {
            throw new ParametroNullException("El usuario es NULO");
        }
        if (u.getId() == null || u.getId() <= 0) {
            throw new ParametroInvalidoException("El id de usuario es invalido");
        }
        if (dao.findBYID(u.getId()) == null) {
            throw new ParametroInvalidoException("El usuario no existe");
        }

        boolean daoBool= dao.eliminar(u);
        if(daoBool){
            coleccionU.eliminarUsuario(u);
        }

        return daoBool;
    }

    public Usuario findUsuarioByID(Integer id){

        if(id == null){
            throw new ParametroNullException("El ID es NULO");
        }

        Usuario u = dao.findBYID(id);
        if (u != null) {
            coleccionU.agregarUsuarios(u);
        }
        return u;
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
        if (u == null) {
            return;
        }
        for (Usuario uu : u) {
            coleccionU.agregarUsuarios(uu);
        }

    }


}
