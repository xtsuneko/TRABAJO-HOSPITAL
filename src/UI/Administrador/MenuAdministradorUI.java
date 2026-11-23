package UI.Administrador;
import ENUM.TipoInterfaz;
import UI.MenuAbstracto;

import java.util.Scanner;

public class MenuAdministradorUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Elija una opcion: ");

        System.out.println("1.Gestionar personal");
        System.out.println("2.Gestionar clientes");

        String opcion=scanner.nextLine();

        if(opcion=="1"){
            return TipoInterfaz.MENU_GESTION_PERSONAL;
        }
        if(opcion=="2"){
            return TipoInterfaz.MENU_GESTION_CLIENTES;
        }

        System.out.println("Opcion invalida");
        System.out.println("Presione cualquier tecla para continuar...");
        scanner.nextLine();

        return TipoInterfaz.MENU_ADMINISTRADOR;

    }
}
