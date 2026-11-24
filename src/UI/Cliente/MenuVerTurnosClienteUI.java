package UI.Cliente;
import ENUM.TipoInterfaz;
import POJO.Cliente;
import Service.TurnoService;
import UI.MenuAbstracto;

public class MenuVerTurnosClienteUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Cliente cliente = (Cliente) this.getDatos();
        TurnoService serviceT = new TurnoService();

        System.out.println("Turnos encontrados: ");
        serviceT.listarTurnosPorCliente(cliente.getId());
        System.out.println("Presione cualquier tecla para volver atras...");

        return TipoInterfaz.MENU_CLIENTE;

    }

}
