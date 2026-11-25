package UI.Recepcionista;
import ENUM.TipoInterfaz;
import POJO.Personal;
import Service.TurnoService;
import UI.MenuAbstracto;

import java.util.Scanner;

public class MenuVerTurnosRecepcionistaUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);
        Personal recepcionista = (Personal) this.getDatos();
        TurnoService serviceT = new TurnoService();

        System.out.println("Turnos encontrados: ");
        serviceT.listarTurnos();
        System.out.println("Presione cualquier tecla para volver atras...");
        scanner.nextLine();

        return TipoInterfaz.MENU_RECEPCIONISTA;

    }

}
