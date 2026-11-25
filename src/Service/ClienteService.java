package Service;
import COLECCION.ClienteColeccion;
import DAO.ClienteDao;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Personal;
import POJO.Usuario;
import UTIL.Validador;

import java.sql.SQLException;
import java.util.ArrayList;


public class ClienteService {


    ClienteDao dao;
    ClienteColeccion coleccionC;

    public ClienteService() {
        this.dao = new ClienteDao();
        this.coleccionC = new ClienteColeccion();
    }

    public int crearCliente(Cliente c) {
        if (c == null) {
            throw new ParametroNullException("El cliente es NULO");
        }
        if (c.getDireccion() == null) {
            throw new ParametroNullException("La direccion es NULA");
        }
        if(c.getIdUsuario()<=0){
            throw new ParametroInvalidoException("El id es invalido");
        }

        String dir = c.getDireccion().trim();
        if (dir.isEmpty()) {
            throw new ParametroInvalidoException("La direccion esta vacia");
        }

        Validador.direccionValida(c.getDireccion());

        int idAux= dao.insertar(c);
        if(idAux!=-1){
            coleccionC.agregarClientes(c.getIdUsuario(), c);
        }

        return idAux;

    }

    public Boolean modificar(Cliente c){
        if (c == null) {
            throw new ParametroNullException("El cliente es NULO");
        }
        if (c.getDireccion() == null) {
            throw new ParametroNullException("La direccion es NULA");
        }
        if(c.getIdUsuario()<=0){
            throw new ParametroInvalidoException("El id es invalido");
        }

        Boolean daoBool= dao.modificar(c);
        if(daoBool==true){
            coleccionC.actualizarCliente(c.getIdCliente(), c);
        }

        return daoBool;
    }

    public Boolean eliminar(Cliente c) throws SQLException {

        if (c == null) {
            throw new ParametroNullException("El cliente es NULO");
        }
        if (c.getIdCliente() <= 0) {
            throw new ParametroInvalidoException("El id de cliente es invalido");
        }

        if (dao.findBYID(c.getIdCliente()) == null) {
            throw new ParametroInvalidoException("El cliente no existe");
        }

        boolean daoBool = dao.eliminar(c);
        if (daoBool) {
            coleccionC.eliminarCliente(c.getIdCliente());
        }

        return daoBool;
    }

    public Cliente findClienteByID(Integer id){

        if(id == null){
            throw new ParametroNullException("El ID es NULO");
        }

        boolean coleccionBool= coleccionC.buscarCliente(id);
        if(!coleccionBool){
            Cliente c = dao.findBYID(id);
            return c;
        }
        return null;
    }

    public Cliente findClienteByIDUsuario(Integer idU) {

        if (idU == null) {
            throw new ParametroNullException("El ID es NULO");
        }
        if (idU <= 0) {
            throw new ParametroInvalidoException("El id de usuario es invalido");
        }

        Cliente c = dao.findBYIDUsuario(idU);
        if (c != null) {
            coleccionC.agregarClientes(c.getIdUsuario(), c);
        }
        return c;
    }

    public void listarCliente() {

        ArrayList<Cliente> c = dao.listarClientes();
        for (Cliente cc : c) {
                coleccionC.agregarClientes(cc.getIdUsuario(), cc);
        }
        System.out.println(c);

    }


}
