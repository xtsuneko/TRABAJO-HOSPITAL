package UI.Veterinario;
import ENUM.TipoInterfaz;
import UI.MenuAbstracto;
import java.util.Scanner;

public class MenuVeterinarioUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Elija una opcion: ");

        System.out.println("1.Ver turnos");
        System.out.println("2.Llenar ficha medica");

        String opcion=scanner.nextLine();

        switch (opcion) {
            case "1":
                return TipoInterfaz.MENU_VER_TURNOS_VETERINARIO;
            case "2":
                return TipoInterfaz.MENU_REVISION;
        }

        System.out.println("Opcion invalida");
        System.out.println("Presione cualquier tecla para continuar...");
        scanner.nextLine();


        return TipoInterfaz.MENU_VETERINARIO;
    }

}
