package UI.Cliente;
import ENUM.TipoInterfaz;
import UI.MenuAbstracto;
import java.util.Scanner;

public class MenuClienteUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Elija una opcion: ");

        System.out.println("1.Agendar turno");
        System.out.println("2.Reagendar turno");
        System.out.println("3.Cancelar turno");
        System.out.println("4.Ver turnos");
        System.out.println("5.Ver ficha medica");

        String opcion=scanner.nextLine();

        switch (opcion) {
            case "1":
                return TipoInterfaz.MENU_AGENDAR_TURNO;
            case "2":
                return TipoInterfaz.MENU_MODIFICAR_TURNO;
            case "3":
                return TipoInterfaz.MENU_CANCELAR_TURNO;
            case "4":
                return TipoInterfaz.MENU_VER_TURNOS_CLIENTE;
            case "5":
                return TipoInterfaz.MENU_REVISION;
        }

        System.out.println("Opcion invalida");
        System.out.println("Presione cualquier tecla para continuar...");
        scanner.nextLine();

        return TipoInterfaz.MENU_CLIENTE;


    }


}

