package UI;
import ENUM.Roles;
import ENUM.TipoInterfaz;
import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;
import POJO.Cliente;
import POJO.Personal;
import POJO.Usuario;
import Service.ClienteService;
import Service.PersonalService;
import Service.UsuarioService;

import java.util.Scanner;

public class LogearUI extends MenuAbstracto {


    @Override
    public TipoInterfaz mostrarInterfaz() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Ingrese su email: ");
            String email= scanner.nextLine();

            System.out.println("Ingrese su contraseña: ");
            String password= scanner.nextLine();

            Usuario u1 = new Usuario(email, password);

            UsuarioService serviceU = new UsuarioService();

            u1 = serviceU.findUsuarioByEmailPassword(u1.getEmail(), u1.getPassword());

            Cliente c1 = new Cliente();

            ClienteService serviceC = new ClienteService();

            c1 = serviceC.findClienteByIDUsuario(u1.getIdUsuario());

            if(c1 !=null){
                return TipoInterfaz.MENU_CLIENTE;
            }

            Personal pl1 = new Personal();

            PersonalService servicePl = new PersonalService();

            pl1 = servicePl.findPersonalByIDUsuario(u1.getIdUsuario());

            switch (pl1.getRol()){
                case Roles.ADMINISTRADOR:
                    return TipoInterfaz.MENU_ADMINISTRADOR;
                case Roles.VETERINARIO:
                    return TipoInterfaz.MENU_VETERINARIO;
                case Roles.RECEPCIONISTA:
                    return TipoInterfaz.MENU_RECEPCIONISTA;
            }
            System.out.println("Usuario no encontrado");
            System.out.println("Presione una tecla para continuar...");
            scanner.nextLine();

            return TipoInterfaz.INICIAR_SESION;

        }catch (ParametroNullException | ParametroInvalidoException e){
            System.out.println(e.getMessage());
        }


        return TipoInterfaz.INVALIDO;
    }
}
