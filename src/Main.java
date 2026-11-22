import ENUM.TipoInterfaz;
import UI.MenuPrincipalUI;
import UI.RegistrarUI;
import UI.MenuAbstracto;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    TipoInterfaz interfaz = TipoInterfaz.MENU_PRINCIPAL;
    MenuAbstracto ui;
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
        }

    }






}
