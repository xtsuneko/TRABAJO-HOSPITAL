package UI.Veterinario;
import ENUM.TipoInterfaz;
import POJO.Personal;
import Service.TurnoService;
import UI.MenuAbstracto;

public class MenuVerTurnosVeterinarioUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Personal veterinario = (Personal) this.getDatos();
        TurnoService serviceT = new TurnoService();

        System.out.println("Turnos encontrados: ");
        serviceT.findTurnoByIDVeterinario(veterinario.getId());
        System.out.println("Presione cualquier tecla para volver atras...");

        return TipoInterfaz.MENU_VETERINARIO;
    }

}
