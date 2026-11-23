package UI.Veterinario;
import ENUM.TipoInterfaz;
import POJO.Personal;
import Service.TurnoService;
import UI.MenuAbstracto;

import java.util.Scanner;

public class MenuRevisionVeterinarioUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Personal veterinario = (Personal) this.getDatos();
        TurnoService serviceT = new TurnoService();

        System.out.println("Elija una opcion: ");
        System.out.println("1.Agregar ficha medica");
        System.out.println("2.Ver fichas medicas");
        System.out.println("0.Volver atras");

        Scanner scanner = new Scanner(System.in);
        String opcion = scanner.nextLine();

        switch (opcion){
            case "1":
                return TipoInterfaz.MENU_AGREGAR_FICHAS_MEDICAS;
            case "2":
                return TipoInterfaz.MENU_MODIFICAR_FICHAS_MEDICAS;
            case "0":
                return TipoInterfaz.MENU_VETERINARIO;
        }

        System.out.println("Opcion invalida");
        System.out.println("Presione cualquier tecla para volver a intentar...");
        scanner.nextLine();
        return TipoInterfaz.MENU_REVISION;
    }

}
