package UI.Personal;
import ENUM.TipoInterfaz;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Personal;
import Service.PersonalService;
import UI.MenuAbstracto;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuDarDeBajaUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese el ID del personal que quiere dar de baja: ");
            int id;
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un ID valido.");
                scanner.next();
            }
            id = scanner.nextInt();
            scanner.nextLine();

            Personal pl1 = new Personal();
            PersonalService servicePl = new PersonalService();

            pl1 = servicePl.findPersonalByID(id);
            if (pl1 == null) {
                System.out.println("No se encontro el personal en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_GESTION_PERSONAL;
            }

            if(servicePl.eliminar(pl1)){
                System.out.println("Personal dado de baja con exito");
            }else {
                System.out.println("No se encontro al personal en el sistema");
            }

            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();
            return TipoInterfaz.MENU_GESTION_PERSONAL;

        } catch (ParametroNullException | ParametroInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return TipoInterfaz.MENU_DAR_DE_BAJA_PERSONAL;
    }
}
