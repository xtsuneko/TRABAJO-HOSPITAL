package UI.Administrador.Personal;
import ENUM.HorarioLaboral;
import ENUM.Roles;
import ENUM.TipoInterfaz;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Persona;
import POJO.Personal;
import POJO.Usuario;
import Service.PersonaService;
import Service.PersonalService;
import Service.UsuarioService;
import UI.MenuAbstracto;

import java.util.Scanner;

public class MenuAgregarPersonalUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese el nombre del personal: ");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el apellido del personal: ");
            String apellido = scanner.nextLine();

            System.out.println("Ingrese el dni: ");
            String dni = scanner.nextLine();

            System.out.println("Ingrese el numero telefonico: ");
            String numeroTelefonico = scanner.nextLine();

            System.out.println("Ingrese el email: ");
            String email = scanner.nextLine();

            System.out.println("Ingrese la contraseña: ");
            String password = scanner.nextLine();

            System.out.println("Ingrese el sueldo del personal: ");
            Double sueldo;
            while (!scanner.hasNextDouble()) {
                System.out.println("Error: debe ingresar un número válido.");
                scanner.next();
            }
            sueldo=scanner.nextDouble();
            scanner.nextLine();

            boolean horarioIngresado=false;
            HorarioLaboral horario = null;
            String opcion;
            while (!horarioIngresado){
                System.out.println("Seleccione  un horario laboral: ");
                System.out.println("1.DIURNO");
                System.out.println("2.VESPERTINO");
                System.out.println("3.NOCTURNO");
                opcion = scanner.nextLine();
                switch (opcion) {
                    case "1":
                        horario = HorarioLaboral.DIURNO;
                        horarioIngresado=true;
                        break;
                    case "2":
                        horario = HorarioLaboral.VESPERTINO;
                        horarioIngresado=true;
                        break;
                    case "3":
                        horario = HorarioLaboral.NOCTURNO;
                        horarioIngresado=true;
                        break;
                    default:
                        System.out.println("Horario invalido");
                }
            }


            boolean rolIngresado=false;
            Roles rol = null;
            while(!rolIngresado){
                System.out.println("Seleccione  un rol: ");
                System.out.println("1.Recepcionista");
                System.out.println("2.Medico");
                opcion = scanner.nextLine();
                switch (opcion) {
                    case "1":
                        rol = Roles.RECEPCIONISTA;
                        rolIngresado=true;
                        break;
                    case "2":
                        rol = Roles.MEDICO;
                        rolIngresado=true;
                        break;
                    default:
                        System.out.println("Rol invalido");
                }
            }

            
            Persona p1 = new Persona(nombre, apellido, dni, numeroTelefonico);
            Usuario u1 = new Usuario(email, password, p1.getDni());
            Personal pl1 = new Personal(sueldo, horario, 0, rol);

            PersonaService serviceP = new PersonaService();
            UsuarioService serviceU = new UsuarioService();
            PersonalService servicePl = new PersonalService();

            serviceP.validarPersonaNueva(p1);
            serviceU.validarUsuario(u1);
            servicePl.validarPersonal(pl1);

            serviceP.crearPersona(p1);

            int id = serviceU.crearUsuario(u1);
            if (id == -1) {
                System.out.println("No pudo crearse el usuario del personal");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_GESTION_PERSONAL;
            }

            pl1.setIdUsuario(id);

            if(servicePl.crearPersonal(pl1) > 0){
                System.out.println("El personal fue creado correctamente");
            }else {
                System.out.println("No pudo crearse el personal");
            }

            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();

            return TipoInterfaz.MENU_GESTION_PERSONAL;
        } catch (ParametroNullException | ParametroInvalidoException e) {
            System.out.println(e.getMessage());
        }

        return TipoInterfaz.MENU_AGREGAR_PERSONAL;
    }

}
