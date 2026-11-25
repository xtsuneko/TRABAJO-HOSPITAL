package UI;
import ENUM.TipoInterfaz;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Persona;
import POJO.Usuario;
import Service.ClienteService;
import Service.PersonaService;
import Service.UsuarioService;

import java.util.Scanner;

public class RegistrarUI extends MenuAbstracto {


    @Override
    public TipoInterfaz mostrarInterfaz() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese su nombre: ");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese su apellido: ");
            String apellido = scanner.nextLine();

            System.out.println("Ingrese su dni: ");
            String dni = scanner.nextLine();

            System.out.println("Ingrese un numero telefonico: ");
            String numeroTelefonico = scanner.nextLine();

            System.out.println("Ingrese un email: ");
            String email = scanner.nextLine();

            System.out.println("Ingrese una contraseña: ");
            String password = scanner.nextLine();

            System.out.println("Ingrese su direccion: ");
            String direccion = scanner.nextLine();

            Persona p1 = new Persona(nombre, apellido, dni, numeroTelefonico);
            Usuario u1 = new Usuario(email, password, p1.getDni());
            Cliente c1 = new Cliente(direccion, 0);

            PersonaService serviceP = new PersonaService();
            UsuarioService serviceU = new UsuarioService();
            ClienteService serviceC = new ClienteService();

            serviceP.validarPersonaNueva(p1);
            serviceU.validarUsuario(u1);
            serviceC.validarCliente(c1);

            serviceP.crearPersona(p1);

            int id = serviceU.crearUsuario(u1);
            if (id == -1) {
                System.out.println("No pudo crearse el usuario");
                System.out.println("Presione cualquier tecla para regresar al menu principal");
                scanner.nextLine();
                return TipoInterfaz.MENU_PRINCIPAL;
            }

            c1.setIdUsuario(id);

            serviceC.crearCliente(c1);

            System.out.println("El usuario fue creado correctamente");
            System.out.println("Presione cualquier tecla para regresar al menu principal");
            scanner.nextLine();

            return TipoInterfaz.MENU_PRINCIPAL;
        } catch (ParametroNullException | ParametroInvalidoException e) {
            System.out.println(e.getMessage());
            System.out.println("Presione cualquier tecla para regresar al menu principal");
            scanner.nextLine();
        }

        return TipoInterfaz.MENU_PRINCIPAL;
    }


}
