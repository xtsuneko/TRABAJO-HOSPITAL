package UI;
import ENUM.TipoInterfaz;
import POJO.Personal;
import Service.PersonalService;

import java.util.Scanner;

public class MenuVerPersonalUI extends MenuAbstracto{

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);

        PersonalService servicePl = new PersonalService();

        System.out.println("Ingrese el id del personal a buscar: ");
        int idABuscar = scanner.nextInt();
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

        return TipoInterfaz.MENU_GESTION_PERSONAL;
    }


}
