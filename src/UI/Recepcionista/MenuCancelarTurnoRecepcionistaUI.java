package UI.Recepcionista;
import ENUM.TipoInterfaz;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Personal;
import POJO.Turno;
import Service.TurnoService;
import UI.MenuAbstracto;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuCancelarTurnoRecepcionistaUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Personal recepcionista = (Personal) this.getDatos();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese el ID del turno que quiere cancelar: ");
            int id;
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un ID valido");
                scanner.next();
            }
            id = scanner.nextInt();
            scanner.nextLine();

            Turno t1 = new Turno();
            TurnoService serviceT = new TurnoService();

            t1=serviceT.findTurnoByID(id);
            if (t1 == null) {
                System.out.println("No se encontro el turno en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_RECEPCIONISTA;
            }
            t1.setId(id);

            if(serviceT.eliminar(t1)){
                System.out.println("Turno cancelado con exito");
            }else {
                System.out.println("No se encontro el turno en el sistema");
            }

            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();

            return TipoInterfaz.MENU_RECEPCIONISTA;
        } catch (ParametroNullException | ParametroInvalidoException e) {
            System.out.println(e.getMessage());
            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();
        }


        return TipoInterfaz.MENU_RECEPCIONISTA;
    }


}
