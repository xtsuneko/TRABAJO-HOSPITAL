package UI;

import ENUM.TipoInterfaz;
import Service.PersonalService;

public class MenuListarPersonalUI extends MenuAbstracto{

    @Override
    public TipoInterfaz mostrarInterfaz() {

        PersonalService servicePl = new PersonalService();

        System.out.println("Personal encontrado: ");
        servicePl.listarPersonal();
        System.out.println("Presione cualquier tecla para volver atras...");

        return TipoInterfaz.MENU_GESTION_PERSONAL;
    }


}
