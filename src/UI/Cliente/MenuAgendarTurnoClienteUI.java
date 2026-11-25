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
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuAgendarTurnoClienteUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Cliente cliente = (Cliente) this.getDatos();
        TurnoService serviceT = new TurnoService();

        Scanner scanner = new Scanner(System.in);

        try {
            PersonalService servicePl = new PersonalService();
            servicePl.listarMedicos();
            System.out.println("Ingrese el id del medico: ");
            int id;
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un ID valido");
                scanner.next();
                return TipoInterfaz.MENU_AGENDAR_TURNO;
            }
            id = scanner.nextInt();
            scanner.nextLine();

            Personal pl1 = new Personal();

            pl1=servicePl.findMedicoByID(id);
            if (pl1 == null) {
                System.out.println("No se encontro el medico en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_CLIENTE;
            }

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
            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();
        }
        return TipoInterfaz.MENU_CLIENTE;
    }

}
