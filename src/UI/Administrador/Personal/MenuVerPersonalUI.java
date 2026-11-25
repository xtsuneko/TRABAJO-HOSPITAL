package UI.Administrador.Personal;
import ENUM.TipoInterfaz;
import POJO.Personal;
import Service.PersonalService;
import UI.MenuAbstracto;

import java.util.Scanner;

public class MenuVerPersonalUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);

        PersonalService servicePl = new PersonalService();

        System.out.println("Ingrese el id del personal a buscar: ");
        int idABuscar;
        while (!scanner.hasNextInt()) {
            System.out.println("Error: debe ingresar un id valido.");
            scanner.next();
            return TipoInterfaz.MENU_VER_PERSONAL;
        }
        idABuscar= scanner.nextInt();
        scanner.nextLine();

        Personal pl1 = new Personal();
        pl1 = servicePl.findPersonalByID(idABuscar);

        if(pl1 == null){
            System.out.println("El personal buscado no se encuentra en el sistema");
        }else {
            System.out.println("Personal encontrado: ");
            System.out.println(pl1);
        }
        System.out.println("Presione cualquier tecla para volver atras...");
        scanner.nextLine();

        return TipoInterfaz.MENU_GESTION_PERSONAL;
    }


}
