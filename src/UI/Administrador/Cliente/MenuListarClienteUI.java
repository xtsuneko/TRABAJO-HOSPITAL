package UI.Administrador.Cliente;
import ENUM.TipoInterfaz;
import Service.ClienteService;
import UI.MenuAbstracto;

import java.util.Scanner;

public class MenuListarClienteUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);
        ClienteService serviceC = new ClienteService();

        System.out.println("Clientes encontrados: ");
        serviceC.listarCliente();
        System.out.println("Presione cualquier tecla para volver atras...");
        scanner.nextLine();


        return TipoInterfaz.MENU_GESTION_CLIENTES;
    }

}
