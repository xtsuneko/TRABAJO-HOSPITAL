package UI.Veterinario;
import ENUM.TipoInterfaz;
import POJO.Cliente;
import POJO.Personal;
import Service.RevisionService;
import UI.MenuAbstracto;

public class MenuVerFichasMedicasVeterinarioUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Cliente cliente = (Cliente) this.getDatos();
        RevisionService serviceR = new RevisionService();

        System.out.println("Fichas medicas encontradas: ");
        serviceR.listarFichasPorCliente(cliente.getId());
        System.out.println("Presione cualquier tecla para volver atras...");

        return TipoInterfaz.MENU_CLIENTE;
    }

}
