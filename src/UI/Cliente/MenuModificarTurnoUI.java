package UI.Cliente;
import ENUM.TipoInterfaz;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Personal;
import POJO.Turno;
import Service.PersonalService;
import Service.TurnoService;
import UI.MenuAbstracto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MenuModificarTurnoUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Cliente cliente = (Cliente) this.getDatos();

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese el ID del turno que quiere modificar: ");
            int id;
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un ID valido");
                scanner.next();
            }
            id = scanner.nextInt();
            scanner.nextLine();

            Turno t1 = new Turno();

            TurnoService serviceT = new TurnoService();

            t1=serviceT.findTurnoByIDYIDCliente(id, cliente.getIdCliente());
            if (t1 == null) {
                System.out.println("No se encontro el turno en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_CLIENTE;
            }
            t1.setId(id);

            PersonalService servicePl = new PersonalService();
            servicePl.listarVeterinarios();

            System.out.println("Ingrese el id del veterinario a modificar: ");
            int idVeterinario;
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un ID valido");
                scanner.next();
            }
            idVeterinario = scanner.nextInt();
            scanner.nextLine();

            Personal pl1 = new Personal();

            pl1=servicePl.findPersonalByID(idVeterinario);
            if (pl1 == null) {
                System.out.println("No se encontro el veterinario en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_AGENDAR_TURNO;
            }
            t1.setIdPersonal(idVeterinario);

            System.out.println("Ingrese la fecha: (AAAA-MM-DD) ");
            LocalDate fecha = LocalDate.parse(scanner.nextLine());
            t1.setFechaTurno(fecha);

            System.out.println("Ingrese la hora: (HH:MM)");
            LocalTime horario = LocalTime.parse(scanner.nextLine());
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

            return TipoInterfaz.MENU_CLIENTE;
        } catch (ParametroNullException | ParametroInvalidoException e) {
            System.out.println(e.getMessage());
        }
        return TipoInterfaz.MENU_AGENDAR_TURNO;
    }


}
