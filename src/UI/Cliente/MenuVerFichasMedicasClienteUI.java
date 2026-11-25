package UI.Cliente;
import ENUM.TipoInterfaz;
import POJO.Cliente;
import Service.RevisionService;
import UI.MenuAbstracto;

import java.util.Scanner;

public class MenuVerFichasMedicasClienteUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);
        Cliente cliente = (Cliente) this.getDatos();
        RevisionService serviceR = new RevisionService();

        System.out.println("Fichas medicas encontradas: ");
        serviceR.listarFichasPorCliente(cliente.getId());
        System.out.println("Presione cualquier tecla para volver atras...");
        scanner.nextLine();

        return TipoInterfaz.MENU_CLIENTE;
    }

}
