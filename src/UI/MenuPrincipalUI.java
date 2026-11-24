package UI;

import ENUM.TipoInterfaz;

import java.util.Scanner;

public class MenuPrincipalUI extends MenuAbstracto {


    @Override
    public TipoInterfaz mostrarInterfaz() {
        System.out.println("¡Bienvenido al Hospital Mitre!");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Elija una opcion:");
        System.out.println("1.Registrarse");
        System.out.println("2.Iniciar Sesion");
        System.out.println("3.Salir");

        String opcion= scanner.nextLine();

       switch (opcion){
           case "1":
               return TipoInterfaz.REGISTRAR;
           case "2":
               return TipoInterfaz.INICIAR_SESION;
           case "3":
               return TipoInterfaz.INVALIDO;
       }

        System.out.println("Opcion invalida");
        System.out.println("Presione cualquier tecla para continuar...");
        scanner.nextLine();

        return TipoInterfaz.MENU_PRINCIPAL;
    }
}
