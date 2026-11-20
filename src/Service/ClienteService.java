package Service;
import COLECCION.ClienteColeccion;
import DAO.ClienteDao;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Personal;
import POJO.Usuario;

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

        //TODO hacer validaciones de direccion

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

        //TODO no tiene que ser NULL, TIENE Q EXISTIR

        boolean daoBool= dao.eliminar(c);
        if(daoBool){
            coleccionC.eliminarCliente(c.getIdCliente());
        }

        return daoBool;
    }

    public String findClienteByID(Integer id){

        if(id == null){
            throw new ParametroNullException("El ID es NULO");
        }

        String mensaje=" ";
        boolean coleccionBool= coleccionC.buscarCliente(id);
        if(!coleccionBool){
            Cliente c = dao.findBYID(id);
            if(c==null){
                mensaje= "No se encontro al cliente en el sistema";
            }
            mensaje="Cliente encontrado: ";
        }

        return mensaje;
    }

    public void listarCliente() {

        ArrayList<Cliente> c = dao.listarClientes();
        for (Cliente cc : c) {
                coleccionC.agregarClientes(cc.getIdUsuario(), cc);
        }

    }


}
