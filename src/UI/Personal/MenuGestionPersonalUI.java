package UI.Personal;

import ENUM.TipoInterfaz;
import UI.MenuAbstracto;

import java.util.Scanner;

public class MenuGestionPersonalUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Elija una opcion: ");

        System.out.println("1.Listar personal");
        System.out.println("2.Ver personal");
        System.out.println("3.Agregar personal");
        System.out.println("4.Modificar personal");
        System.out.println("5.Dar de baja personal");

        String opcion=scanner.nextLine();

        switch (opcion) {
            case "1":
                return TipoInterfaz.MENU_LISTAR_PERSONAL;
            case "2":
                return TipoInterfaz.MENU_VER_PERSONAL;
            case "3":
                return TipoInterfaz.MENU_AGREGAR_PERSONAL;
            case "4":
                return TipoInterfaz.MENU_MODIFICAR_PERSONAL;
            case "5":
                return TipoInterfaz.MENU_DAR_DE_BAJA_PERSONAL;
        }

        System.out.println("Opcion invalida");
        System.out.println("Presione cualquier tecla para continuar...");
        scanner.nextLine();

        return TipoInterfaz.MENU_GESTION_PERSONAL;

    }
}
