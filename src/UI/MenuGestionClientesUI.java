package UI;

import ENUM.TipoInterfaz;

import java.util.Scanner;

public class MenuGestionClientesUI extends MenuAbstracto{
    
    @Override
    public TipoInterfaz mostrarInterfaz() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Elija una opcion: ");

        System.out.println("1.Listar clientes");
        System.out.println("2.Ver cliente");
        System.out.println("3.Agregar cliente");
        System.out.println("4.Modificar cliente");
        System.out.println("5.Dar de baja cliente");

        String opcion=scanner.nextLine();

        switch (opcion) {
            case "1":
                return TipoInterfaz.MENU_LISTAR_CLIENTE;
            case "2":
                return TipoInterfaz.MENU_VER_CLIENTE;
            case "3":
                return TipoInterfaz.MENU_AGREGAR_CLIENTE;
            case "4":
                return TipoInterfaz.MENU_MODIFICAR_CLIENTE;
            case "5":
                return TipoInterfaz.MENU_DAR_DE_BAJA_CLIENTE;
        }

        System.out.println("Opcion invalida");
        System.out.println("Presione cualquier tecla para continuar...");
        scanner.nextLine();

        return TipoInterfaz.MENU_GESTION_CLIENTES;

    }
}

