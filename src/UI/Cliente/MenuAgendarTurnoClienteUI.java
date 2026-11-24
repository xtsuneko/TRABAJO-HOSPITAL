package UI.Cliente;
import ENUM.TipoInterfaz;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Persona;
import POJO.Personal;
import POJO.Turno;
import Service.PersonalService;
import Service.TurnoService;
import UI.MenuAbstracto;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MenuAgendarTurnoClienteUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Cliente cliente = (Cliente) this.getDatos();
        TurnoService serviceT = new TurnoService();

        Scanner scanner = new Scanner(System.in);

        try {
            PersonalService servicePl = new PersonalService();
            servicePl.listarVeterinarios();
            System.out.println("Ingrese el id del veterinario: ");
            int id;
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un ID valido");
                scanner.next();
            }
            id = scanner.nextInt();
            scanner.nextLine();

            Personal pl1 = new Personal();

            pl1=servicePl.findPersonalByID(id);
            if (pl1 == null) {
                System.out.println("No se encontro el veterinario en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_AGENDAR_TURNO;
            }

            System.out.println("Ingrese la fecha: (AAAA-MM-DD) ");
            LocalDate fecha = LocalDate.parse(scanner.nextLine());

            System.out.println("Ingrese la hora: (HH:MM)");
            LocalTime horario = LocalTime.parse(scanner.nextLine());

            System.out.println("Ingrese el motivo de la consulta: ");
            String motivoConsulta = scanner.nextLine();

            Turno t1 = new Turno(cliente.getId(), pl1.getId(), fecha, horario, motivoConsulta);
            if(serviceT.crearTurno(t1) > 0){
                System.out.println("El turno fue agendado correctamente");
            }else {
                System.out.println("No pudo agendarse el turno");
            }

            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();

            return TipoInterfaz.MENU_CLIENTE;
        } catch (ParametroNullException | ParametroInvalidoException e) {
            System.out.println(e.getMessage());
        }
        return TipoInterfaz.MENU_AGENDAR_TURNO;
    }

}
