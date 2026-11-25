import ENUM.TipoInterfaz;
import GENERICS.ClaseBaseBddGenerica;
import UI.Administrador.Cliente.*;
import UI.Administrador.MenuAdministradorUI;
import UI.Administrador.Personal.*;
import UI.Cliente.*;
import UI.LogearUI;
import UI.MenuPrincipalUI;
import UI.Recepcionista.*;
import UI.RegistrarUI;
import UI.MenuAbstracto;
import UI.Medico.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    TipoInterfaz interfaz = TipoInterfaz.MENU_PRINCIPAL;
    MenuAbstracto ui;
    ClaseBaseBddGenerica datos=null;
    while (interfaz != TipoInterfaz.INVALIDO){
        switch (interfaz){
            case TipoInterfaz.MENU_PRINCIPAL:
                ui = new MenuPrincipalUI();
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.REGISTRAR:
                ui = new RegistrarUI();
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.INICIAR_SESION:
                ui = new LogearUI();
                interfaz = ui.mostrarInterfaz();
                datos= ui.getDatos();
                break;
            case TipoInterfaz.MENU_MEDICO:
                ui = new MenuMedicoUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_VER_TURNOS_MEDICOS:
                ui = new MenuVerTurnosMedicoUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_CLIENTE:
                ui = new MenuClienteUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_RECEPCIONISTA:
                ui = new MenuRecepcionistaUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_ADMINISTRADOR:
                ui = new MenuAdministradorUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_GESTION_PERSONAL:
                ui = new MenuGestionPersonalUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_GESTION_CLIENTES:
                ui = new MenuGestionClientesUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_LISTAR_PERSONAL:
                ui = new MenuListarPersonalUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_VER_PERSONAL:
                ui = new MenuVerPersonalUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_AGREGAR_PERSONAL:
                ui = new MenuAgregarPersonalUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_MODIFICAR_PERSONAL:
                ui = new MenuModificarPersonalUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_DAR_DE_BAJA_PERSONAL:
                ui = new MenuDarDeBajaUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_LISTAR_CLIENTE:
                ui = new MenuListarClienteUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_VER_CLIENTE:
                ui = new MenuVerClienteUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_AGREGAR_CLIENTE:
                ui = new MenuAgregarClienteUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_MODIFICAR_CLIENTE:
                ui = new MenuModificarCliente();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_DAR_DE_BAJA_CLIENTE:
                ui = new MenuDarDeBajaClienteUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_AGENDAR_TURNO:
                ui = new MenuAgendarTurnoClienteUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_MODIFICAR_TURNO:
                ui = new MenuModificarTurnoUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_CANCELAR_TURNO:
                ui = new MenuCancelarTurnoUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_VER_TURNOS_CLIENTE:
                ui = new MenuVerTurnosClienteUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_REVISION:
                ui = new MenuRevisionMedicoUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_AGREGAR_FICHAS_MEDICAS:
                ui = new MenuAgregarFichasMedicasMedicoUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_VER_FICHAS_MEDICAS:
                ui = new MenuVerFichasMedicasClienteUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_MODIFICAR_FICHAS_MEDICAS:
                ui = new MenuVerTurnosMedicoUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_AGENDAR_TURNO_RECEPCIONISTA:
                ui = new MenuAgendarTurnoRecepcionistaUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_MODIFICAR_TURNO_RECEPCIONISTA:
                ui = new MenuModificarTurnoRecepcionistaUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_CANCELAR_TURNO_RECEPCIONISTA:
                ui = new MenuCancelarTurnoRecepcionistaUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_VER_TURNOS_RECEPCIONISTA:
                ui = new MenuVerTurnosRecepcionistaUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
        }

    }
    System.out.println("¡Vuelva pronto!");

}
