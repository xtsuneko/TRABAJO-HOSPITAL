package UI.Veterinario;
import ENUM.TipoInterfaz;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Personal;
import POJO.Revision;
import POJO.Turno;
import Service.RevisionService;
import UI.MenuAbstracto;
import java.util.Scanner;

public class MenuAgregarFichasMedicasVeterinarioUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Personal veterinario = (Personal) this.getDatos();
        RevisionService serviceR = new RevisionService();
        Turno turnos = (Turno) this.getDatos();


        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese el id del turno: ");
            String idTurno = scanner.nextLine();

            System.out.println("Ingrese el diagnostico: ");
            String diagnostico = scanner.nextLine();

            System.out.println("Ingrese el tratamiento: ");
            String tratamiento = scanner.nextLine();

            Revision r1 = new Revision(turnos.getId(), diagnostico, tratamiento);

            if(serviceR.crearRevision(r1) > 0){
                System.out.println("La ficha medica fue creada correctamente");
            }else {
                System.out.println("No pudo crearse la ficha medica");
            }

            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();

            return TipoInterfaz.MENU_VETERINARIO;

        } catch (ParametroNullException | ParametroInvalidoException e) {
            System.out.println(e.getMessage());
        }


        return TipoInterfaz.MENU_AGREGAR_FICHAS_MEDICAS;
    }


}
