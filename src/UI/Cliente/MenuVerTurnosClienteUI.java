package UI.Cliente;
import ENUM.TipoInterfaz;
import POJO.Cliente;
import Service.TurnoService;
import UI.MenuAbstracto;

import java.util.Scanner;

public class MenuVerTurnosClienteUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);
        Cliente cliente = (Cliente) this.getDatos();
        TurnoService serviceT = new TurnoService();

        System.out.println("Turnos encontrados: ");
        serviceT.listarTurnosPorCliente(cliente.getId());
        System.out.println("Presione cualquier tecla para volver atras...");
        scanner.nextLine();

        return TipoInterfaz.MENU_CLIENTE;

    }

}
