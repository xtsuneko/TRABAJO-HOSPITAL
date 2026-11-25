package UI.Medico;
import ENUM.TipoInterfaz;
import UI.MenuAbstracto;

import java.util.Scanner;

public class MenuRevisionMedicoUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        System.out.println("Elija una opcion: ");
        System.out.println("1.Agregar ficha medica");
        System.out.println("2.Ver fichas medicas");
        System.out.println("3.Volver atras");

        Scanner scanner = new Scanner(System.in);
        String opcion = scanner.nextLine();

        switch (opcion){
            case "1":
                return TipoInterfaz.MENU_AGREGAR_FICHAS_MEDICAS;
            case "2":
                return TipoInterfaz.MENU_VER_FICHAS_MEDICAS_MEDICO;
            case "3":
                return TipoInterfaz.MENU_MEDICO;
        }

        System.out.println("Opcion invalida");
        System.out.println("Presione cualquier tecla para volver a intentar...");
        scanner.nextLine();
        return TipoInterfaz.MENU_REVISION;
    }

}
