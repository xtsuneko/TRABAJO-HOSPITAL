package UI.Recepcionista;
import ENUM.TipoInterfaz;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Persona;
import POJO.Personal;
import POJO.Turno;
import Service.ClienteService;
import Service.PersonalService;
import Service.TurnoService;
import UI.MenuAbstracto;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MenuAgendarTurnoRecepcionistaUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Personal recepcionista = (Personal) this.getDatos();
        TurnoService serviceT = new TurnoService();

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese el id del cliente: ");
            int idCliente;
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un ID valido");
                scanner.next();
            }
            idCliente = scanner.nextInt();
            scanner.nextLine();

            Cliente c1 = new Cliente();
            ClienteService serviceC = new ClienteService();

            c1=serviceC.findClienteByID(idCliente);
            if (c1 == null) {
                System.out.println("No se encontro el cliente en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_AGENDAR_TURNO_RECEPCIONISTA;
            }

            System.out.println("Ingrese el id del veterinario: ");
            int id;
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un ID valido");
                scanner.next();
            }
            id = scanner.nextInt();
            scanner.nextLine();

            Personal pl1 = new Personal();
            PersonalService servicePl = new PersonalService();

            pl1=servicePl.findPersonalByID(id);
            if (pl1 == null) {
                System.out.println("No se encontro el veterinario en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_AGENDAR_TURNO_RECEPCIONISTA;
            }

            System.out.println("Ingrese la fecha: (AAAA-MM-DD) ");
            LocalDate fecha = LocalDate.parse(scanner.nextLine());

            System.out.println("Ingrese la hora: (HH:MM)");
            LocalTime horario = LocalTime.parse(scanner.nextLine());

            System.out.println("Ingrese el motivo de la consulta: ");
            String motivoConsulta = scanner.nextLine();

            Turno t1 = new Turno(c1.getId(), pl1.getId(), fecha, horario, motivoConsulta);
            if(serviceT.crearTurno(t1) > 0){
                System.out.println("El turno fue agendado correctamente");
            }else {
                System.out.println("No pudo agendarse el turno");
            }

            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();

            return TipoInterfaz.MENU_RECEPCIONISTA;
        } catch (ParametroNullException | ParametroInvalidoException e) {
            System.out.println(e.getMessage());
        }
        return TipoInterfaz.MENU_AGENDAR_TURNO_RECEPCIONISTA;
    }



}
