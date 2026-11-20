package UI;

import ENUM.TipoInterfaz;

import java.util.Scanner;

public class MenuPrincipalUI extends MenuAbstracto {


    @Override
    public TipoInterfaz mostrarInterfaz() {
        System.out.println("¡Bienvenido a Chorcho Veterinaria!");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Elija una opcion:");
        System.out.println("1.Registrarse");
        System.out.println("2.Iniciar Sesion");

        int opcion= scanner.nextInt();
        scanner.nextLine();

        if(opcion==1){
            return TipoInterfaz.REGISTRAR;
        }
        if(opcion==2){
            return TipoInterfaz.INICIAR_SESION;
        }

        System.out.println("Opcion invalida");
        System.out.println("Presione cualquier tecla para continuar...");
        scanner.nextLine();

        return TipoInterfaz.MENU_PRINCIPAL;
    }
}
