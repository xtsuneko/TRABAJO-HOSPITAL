package UI.Medico;
import ENUM.TipoInterfaz;
import POJO.Personal;
import Service.RevisionService;
import UI.MenuAbstracto;

import java.util.Scanner;

public class MenuVerFichasMedicasMedicoUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);
        Personal medico = (Personal) this.getDatos();
        RevisionService serviceR = new RevisionService();

        System.out.println("Fichas medicas encontradas: ");
        serviceR.listarFichasPorMedico(medico.getId());
        System.out.println("Presione cualquier tecla para volver atras...");
        scanner.nextLine();

        return TipoInterfaz.MENU_REVISION;
    }

}
