package UI.Cliente;
import ENUM.TipoInterfaz;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Persona;
import POJO.Usuario;
import Service.ClienteService;
import Service.PersonaService;
import Service.UsuarioService;
import UI.MenuAbstracto;

import java.util.Scanner;

public class MenuAgregarClienteUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese el nombre del cliente: ");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el apellido del cliente: ");
            String apellido = scanner.nextLine();

            System.out.println("Ingrese el dni del cliente: ");
            String dni = scanner.nextLine();

            System.out.println("Ingrese el numero telefonico del cliente: ");
            String numeroTelefonico = scanner.nextLine();

            Persona p1 = new Persona(nombre, apellido, dni, numeroTelefonico);

            PersonaService serviceP = new PersonaService();

            serviceP.crearPersona(p1);

            System.out.println("Ingrese el email del cliente: ");
            String email = scanner.nextLine();

            System.out.println("Ingrese la contraseña del cliente: ");
            String password = scanner.nextLine();

            Usuario u1 = new Usuario(email, password, p1.getDni());

            UsuarioService serviceU = new UsuarioService();

            int id = serviceU.crearUsuario(u1);

            System.out.println("Ingrese la direccion del cliente: ");
            String direccion = scanner.nextLine();

            Cliente c1 = new Cliente(direccion, u1.getIdUsuario());

            ClienteService serviceC = new ClienteService();

            if(serviceC.crearCliente(c1) > 0){
                System.out.println("El cliente fue creado correctamente");
            }else {
                System.out.println("No pudo crearse el cliente");
            }

            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();

            return TipoInterfaz.MENU_GESTION_PERSONAL;
        } catch (ParametroNullException | ParametroInvalidoException e) {
            System.out.println(e.getMessage());
        }


        return TipoInterfaz.INVALIDO;
    }

}
