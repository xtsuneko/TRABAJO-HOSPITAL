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

public class MenuModificarCliente extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Ingrese el ID del cliente que quiere modificar: ");
            int id;
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un ID valido.");
                scanner.next();
            }
            id = scanner.nextInt();
            scanner.nextLine();

            Cliente c1 = new Cliente();
            ClienteService serviceC = new ClienteService();

            c1 = serviceC.findClienteByID(id);
            if (c1 == null) {
                System.out.println("No se encontro el cliente en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                return TipoInterfaz.MENU_GESTION_CLIENTES;
            }

            Usuario u1 = new Usuario();
            UsuarioService serviceU = new UsuarioService();
            u1 = serviceU.findUsuarioByID(c1.getIdUsuario());

            Persona p1 = new Persona();
            PersonaService serviceP = new PersonaService();
            p1 = serviceP.findPersonaByDNI(u1.getDniPersona());

            System.out.println("Ingrese el nombre del cliente o deje en blanco para mantener " + p1.getNombre() + ": ");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                p1.setNombre(nombre);
            }

            System.out.println("Ingrese el apellido del cliente o deje en blanco para mantener " + p1.getApellido() + ": ");
            String apellido = scanner.nextLine();
            if (!apellido.isEmpty()) {
                p1.setApellido(apellido);
            }

            System.out.println("Ingrese el dni o deje en blanco para mantener " + p1.getDni() + ": ");
            String dni = scanner.nextLine();
            if (!dni.isEmpty()) {
                p1.setDni(dni);
            }

            System.out.println("Ingrese el numero telefonico o deje en blanco para mantener " + p1.getTelefono() + ": ");
            String numeroTelefonico = scanner.nextLine();
            if (!numeroTelefonico.isEmpty()) {
                p1.setTelefono(numeroTelefonico);
            }

            serviceP.modificar(p1);

            System.out.println("Ingrese el email o deje en blanco para mantener " + u1.getEmail() + ": ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                u1.setEmail(email);
            }

            System.out.println("Ingrese la contraseña o deje en blanco para mantener " + u1.getPassword() + ": ");
            String password = scanner.nextLine();
            if (!password.isEmpty()) {
                u1.setPassword(password);
            }

            serviceU.modificar(u1);

            System.out.println("Ingrese la direccion o deje en blanco para mantener " + c1.getDireccion() + ": ");
            String direccion = scanner.nextLine();
            if(!direccion.isEmpty()){
                c1.setDireccion(direccion);
            }

            if(serviceC.modificar(c1)){
                System.out.println("El cliente fue modificado correctamente");
            }else {
                System.out.println("No pudo modificarse el cliente");
            }

            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();

            return TipoInterfaz.MENU_GESTION_CLIENTES;

        }catch (ParametroNullException | ParametroInvalidoException e) {
        System.out.println(e.getMessage());
        }

        return TipoInterfaz.INVALIDO;
    }


}
