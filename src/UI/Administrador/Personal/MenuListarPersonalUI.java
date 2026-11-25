package UI.Administrador.Personal;

import ENUM.TipoInterfaz;
import Service.PersonalService;
import UI.MenuAbstracto;

import java.util.Scanner;

public class MenuListarPersonalUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);
        PersonalService servicePl = new PersonalService();

        System.out.println("Personal encontrado: ");
        servicePl.listarPersonal();
        System.out.println("Presione cualquier tecla para volver atras...");
        scanner.nextLine();

        return TipoInterfaz.MENU_GESTION_PERSONAL;
    }


}
