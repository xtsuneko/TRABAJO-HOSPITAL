package UI.Administrador.Cliente;
import ENUM.TipoInterfaz;
import Service.ClienteService;
import UI.MenuAbstracto;

public class MenuListarClienteUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        ClienteService serviceC = new ClienteService();

        System.out.println("Clientes encontrados: ");
        serviceC.listarCliente();
        System.out.println("Presione cualquier tecla para volver atras...");


        return TipoInterfaz.MENU_GESTION_CLIENTES;
    }

}
