package UI.Medico;
import ENUM.TipoInterfaz;
import POJO.Personal;
import UI.MenuAbstracto;
import java.util.Scanner;

public class MenuMedicoUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Personal medico = (Personal) this.getDatos();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Elija una opcion: ");

        System.out.println("1.Ver turnos");
        System.out.println("2.Llenar ficha medica");
        System.out.println("3.Cerrar sesion");

        String opcion=scanner.nextLine();

        switch (opcion) {
            case "1":
                return TipoInterfaz.MENU_VER_TURNOS_MEDICOS;
            case "2":
                return TipoInterfaz.MENU_REVISION;
            case "3":
                return TipoInterfaz.MENU_PRINCIPAL;
        }

        System.out.println("Opcion invalida");
        System.out.println("Presione cualquier tecla para continuar...");
        scanner.nextLine();


        return TipoInterfaz.MENU_MEDICO;
    }

}
