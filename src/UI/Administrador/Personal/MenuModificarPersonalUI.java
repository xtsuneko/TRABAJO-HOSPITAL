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

public class MenuModificarPersonalUI extends MenuAbstracto {

    @Override
    public TipoInterfaz mostrarInterfaz() {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese el ID del personal que quiere modificar: ");
            int id;
            while (!scanner.hasNextInt()) {
                System.out.println("Error: debe ingresar un ID valido.");
                scanner.next();
            }
            id = scanner.nextInt();
            scanner.nextLine();

            Personal pl1 = new Personal();
            PersonalService servicePl = new PersonalService();

            pl1 = servicePl.findPersonalByID(id);
            if (pl1 == null) {
                System.out.println("No se encontro el personal en el sistema");
                System.out.println("Presione cualquier tecla para regresar atras...");
                scanner.nextLine();
                return TipoInterfaz.MENU_GESTION_PERSONAL;
            }

            Usuario u1 = new Usuario();
            UsuarioService serviceU = new UsuarioService();
            u1 = serviceU.findUsuarioByID(pl1.getIdUsuario());

            Persona p1 = new Persona();
            PersonaService serviceP = new PersonaService();
            p1 = serviceP.findPersonaByDNI(u1.getDniPersona());

            System.out.println("Ingrese el nombre del personal o deje en blanco para mantener " + p1.getNombre() + ": ");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                p1.setNombre(nombre);
            }

            System.out.println("Ingrese el apellido del personal o deje en blanco para mantener " + p1.getApellido() + ": ");
            String apellido = scanner.nextLine();
            if (!apellido.isEmpty()) {
                p1.setApellido(apellido);
            }


            System.out.println("Ingrese el numero telefonico o deje en blanco para mantener " + p1.getTelefono() + ": ");
            String numeroTelefonico = scanner.nextLine();
            if (!numeroTelefonico.isEmpty()) {
                p1.setTelefono(numeroTelefonico);
            }

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

            System.out.println("Ingrese el sueldo del personal o deje en blanco para mantener " + pl1.getSueldo() + ": ");
            Double sueldo = null;
            while (sueldo == null) {
                String sueldoIngresado = scanner.nextLine();
                if (sueldoIngresado.isEmpty()) {
                    sueldo = pl1.getSueldo();
                } else {
                    try {
                        sueldo = Double.parseDouble(sueldoIngresado);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: debe ingresar un número valido.");
                    }
                }
            }
            pl1.setSueldo(sueldo);

            boolean horarioIngresado=false;
            HorarioLaboral horario = null;
            String opcion;
            while (!horarioIngresado){
                System.out.println("Seleccione  un horario laboral o presione Enter para mantener: ");
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
                    case "":
                        horario = pl1.getHorarioLaboral();
                        horarioIngresado=true;
                        break;
                    default:
                        System.out.println("Horario invalido");
                }
            }
            pl1.setHorarioLaboral(horario);

            boolean rolIngresado=false;
            Roles rol = null;
            while(!rolIngresado){
                System.out.println("Seleccione  un rol o presione Enter para mantener: ");
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
                    case "":
                        rol = pl1.getRol();
                        rolIngresado=true;
                        break;
                    default:
                        System.out.println("Rol invalido");
                }
            }
            pl1.setRol(rol);

            serviceP.validarPersona(p1);
            serviceU.validarUsuario(u1);
            servicePl.validarPersonal(pl1);

            serviceP.modificar(p1);
            serviceU.modificar(u1);

            if(servicePl.modificar(pl1)){
                System.out.println("El personal fue modificado correctamente");
            }else {
                System.out.println("No pudo modificarse el personal");
            }

            System.out.println("Presione cualquier tecla para regresar atras...");
            scanner.nextLine();

            return TipoInterfaz.MENU_GESTION_PERSONAL;
        } catch (ParametroNullException | ParametroInvalidoException e) {
            System.out.println(e.getMessage());
        }

        return TipoInterfaz.MENU_MODIFICAR_PERSONAL;
    }


}
