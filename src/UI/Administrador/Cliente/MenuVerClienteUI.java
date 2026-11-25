package UI.Administrador.Cliente;
import ENUM.TipoInterfaz;
import POJO.Cliente;
import Service.ClienteService;
import UI.MenuAbstracto;
import java.util.Scanner;

public class MenuVerClienteUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);

        ClienteService serviceC = new ClienteService();

        System.out.println("Ingrese el id del cliente a buscar: ");
        int idABuscar;
        while (!scanner.hasNextInt()) {
            System.out.println("Error: debe ingresar un id valido.");
            scanner.next();
            return TipoInterfaz.MENU_VER_CLIENTE;
        }
        idABuscar= scanner.nextInt();
        scanner.nextLine();

        Cliente c1 = new Cliente();
        c1 = serviceC.findClienteByID(idABuscar);

        if(c1 == null){
            System.out.println("El cliente buscado no se encuentra en el sistema");
        }else {
            System.out.println("Cliente encontrado: ");
            System.out.println(c1);
        }
        System.out.println("Presione cualquier tecla para volver atras...");
        scanner.nextLine();

        return TipoInterfaz.MENU_GESTION_CLIENTES;


    }


}
