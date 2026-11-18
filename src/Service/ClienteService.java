package Service;
import DAO.ClienteDao;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;


public class ClienteService {


    ClienteDao dao;

    public ClienteService() {
        this.dao = new ClienteDao();
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

        return dao.insertar(c);

    }
}
