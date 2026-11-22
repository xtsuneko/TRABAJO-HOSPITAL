package UI.Cliente;
import ENUM.TipoInterfaz;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import Service.ClienteService;
import UI.MenuAbstracto;
import java.sql.SQLException;
import java.util.Scanner;


public class MenuDarDeBajaClienteUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Ingrese el ID del cliente que quiere dar de baja: ");
            int id;
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un ID valido.");
                scanner.next();
            }
            id = scanner.nextInt();
            scanner.nextLine();

            Cliente c1 = new Cliente();
            ClienteService serviceC = new ClienteService();

            c1 = serviceC.findClienteByID(id);
            if (c1 == null) {
                System.out.println("No se encontro el cliente en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_GESTION_CLIENTES;
            }

            if(serviceC.eliminar(c1)){
                System.out.println("Cliente dado de baja con exito");
            }else {
                System.out.println("No se encontro al cliente en el sistema");
            }

            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();
            return TipoInterfaz.MENU_GESTION_CLIENTES;


        } catch (ParametroNullException | ParametroInvalidoException e) {
        System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return TipoInterfaz.MENU_DAR_DE_BAJA_CLIENTE;
    }

}
