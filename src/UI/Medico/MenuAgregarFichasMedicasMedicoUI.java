package UI.Medico;
import ENUM.TipoInterfaz;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Personal;
import POJO.Revision;
import POJO.Turno;
import Service.RevisionService;
import Service.TurnoService;
import UI.MenuAbstracto;
import java.util.Scanner;

public class MenuAgregarFichasMedicasMedicoUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Personal medico = (Personal) this.getDatos();
        RevisionService serviceR = new RevisionService();

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese el id del turno: ");
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
            if (t1 == null || t1.getIdPersonal() != medico.getId()) {
                System.out.println("No se encontro el turno en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_REVISION;
            }
            t1.setId(id);

            System.out.println("Ingrese el diagnostico: ");
            String diagnostico = scanner.nextLine();

            System.out.println("Ingrese el tratamiento: ");
            String tratamiento = scanner.nextLine();

            Revision r1 = new Revision(t1.getId(), diagnostico, tratamiento);

            if(serviceR.crearRevision(r1) > 0){
                System.out.println("La ficha medica fue creada correctamente");
            }else {
                System.out.println("No pudo crearse la ficha medica");
            }

            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();

            return TipoInterfaz.MENU_MEDICO;

        } catch (ParametroNullException | ParametroInvalidoException e) {
            System.out.println(e.getMessage());

            System.out.println("Presione cualquier tecla para regresar atras...");

            scanner.nextLine();
        }


        return TipoInterfaz.MENU_REVISION;
    }


}
