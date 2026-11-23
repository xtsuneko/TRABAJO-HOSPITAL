package UI.Veterinario;
import ENUM.TipoInterfaz;
import POJO.Personal;
import Service.RevisionService;
import UI.MenuAbstracto;

public class MenuVerFichasMedicasVeterinarioUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Personal veterinario = (Personal) this.getDatos();
        RevisionService serviceR = new RevisionService();

        System.out.println("Fichas medicas encontradas: ");
        serviceR.findRevisionByIDPorVeterinario(veterinario.getId());
        System.out.println("Presione cualquier tecla para volver atras...");

        return TipoInterfaz.MENU_VETERINARIO;
    }

}
