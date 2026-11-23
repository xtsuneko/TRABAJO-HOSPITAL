import ENUM.TipoInterfaz;
import GENERICS.ClaseBaseBddGenerica;
import UI.LogearUI;
import UI.MenuPrincipalUI;
import UI.RegistrarUI;
import UI.MenuAbstracto;
import UI.Veterinario.MenuVerTurnosVeterinarioUI;
import UI.Veterinario.MenuVeterinarioUI;


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
            case TipoInterfaz.MENU_VETERINARIO:
                ui = new MenuVeterinarioUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
            case TipoInterfaz.MENU_VER_TURNOS_VETERINARIO:
                ui = new MenuVerTurnosVeterinarioUI();
                ui.setDatos(datos);
                interfaz = ui.mostrarInterfaz();
                break;
        }

    }






}
