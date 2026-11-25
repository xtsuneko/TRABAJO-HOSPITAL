package UI.Recepcionista;
import ENUM.TipoInterfaz;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Personal;
import POJO.Turno;
import Service.ClienteService;
import Service.PersonalService;
import Service.TurnoService;
import UI.MenuAbstracto;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuModificarTurnoRecepcionistaUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Personal recepcionista = (Personal) this.getDatos();
        Scanner scanner = new Scanner(System.in);

        try {

            TurnoService serviceT = new TurnoService();
            serviceT.listarTurnos();

            System.out.println("Ingrese el ID del turno que quiere modificar: ");
            int id;
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un ID valido");
                scanner.next();
            }
            id = scanner.nextInt();
            scanner.nextLine();

            Turno t1 = new Turno();

            t1=serviceT.findTurnoByID(id);
            if (t1 == null) {
                System.out.println("No se encontro el turno en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_RECEPCIONISTA;
            }
            t1.setId(id);

            ClienteService serviceC = new ClienteService();
            serviceC.listarCliente();

            System.out.println("Ingrese el id del cliente: ");
            int idCliente;
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un ID valido");
                scanner.next();
            }
            idCliente = scanner.nextInt();
            scanner.nextLine();

            Cliente c1 = new Cliente();

            c1=serviceC.findClienteByID(idCliente);
            if (c1 == null) {
                System.out.println("No se encontro el cliente en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_RECEPCIONISTA;
            }
            t1.setIdCliente(idCliente);

            PersonalService servicePl = new PersonalService();
            servicePl.listarMedicos();

            System.out.println("Ingrese el id del medico a modificar: ");
            int idMedico;
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un ID valido");
                scanner.next();
            }
            idMedico = scanner.nextInt();
            scanner.nextLine();

            Personal pl1 = new Personal();

            pl1=servicePl.findMedicoByID(idMedico);
            if (pl1 == null) {
                System.out.println("No se encontro el medico en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_RECEPCIONISTA;
            }
            t1.setIdPersonal(idMedico);

            boolean fechaCapturada=false;
            LocalDate fecha=null;
            while(!fechaCapturada){
                try {
                    System.out.println("Ingrese la fecha: (AAAA-MM-DD) ");
                    fecha = LocalDate.parse(scanner.nextLine());
                    fechaCapturada = true;
                }catch (DateTimeParseException e){
                    System.out.println("La fecha ingresada no es valida, el formato deberia ser: AAAA-MM-DD");
                }
            }

            t1.setFechaTurno(fecha);

            boolean horaCapturada=false;
            LocalTime horario=null;
            while(!horaCapturada){
                try {
                    System.out.println("Ingrese la hora: (HH:MM)");
                    horario = LocalTime.parse(scanner.nextLine());
                    horaCapturada = true;
                }catch (DateTimeParseException e){
                    System.out.println("La hora ingresada no es valida, el formato deberia ser: HH:MM");
                }
            }

            t1.setHoraTurno(horario);

            System.out.println("Ingrese el motivo de la consulta: ");
            String motivoConsulta = scanner.nextLine();
            t1.setMotivoConsulta(motivoConsulta);

            if(serviceT.modificarTurno(t1)){
                System.out.println("El turno fue modificado correctamente");
            }else {
                System.out.println("No pudo modificarse el turno");
            }

            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();

            return TipoInterfaz.MENU_RECEPCIONISTA;
        } catch (ParametroNullException | ParametroInvalidoException e) {
            System.out.println(e.getMessage());
            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();
        }
        return TipoInterfaz.MENU_RECEPCIONISTA;
    }


}
