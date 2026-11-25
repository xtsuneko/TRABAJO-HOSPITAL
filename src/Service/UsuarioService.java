package Service;

import COLECCION.UsuarioColeccion;
import DAO.UsuarioDao;
import EXCEPCIONES.ParametroInvalidoException;
import POJO.Usuario;
import UTIL.Validador;

import java.sql.SQLException;

public class UsuarioService {

    private static final String DOMINIO_EMAIL = "@gmail.com";
    private static final int MIN_CARACTERES_EMAIL = 5;
    private static final int MIN_PASSWORD = 5;
    private static final int MAX_PASSWORD = 8;

    private final UsuarioDao dao;
    private final UsuarioColeccion coleccionU;

    public UsuarioService() {
        this.dao = new UsuarioDao();
        this.coleccionU = new UsuarioColeccion();
    }

    public int crearUsuario(Usuario u) {
        validarUsuario(u);

        int idGenerado = dao.insertar(u);
        if (idGenerado != -1) {
            coleccionU.agregarUsuarios(u);
        }

        return idGenerado;
    }

    public boolean modificar(Usuario u) {
        Validador.noNulo(u, "El usuario es NULO");
        Validador.idValido(u.getIdUsuario(), "El ID del usuario es invalido");
        validarUsuario(u);

        boolean modificado = dao.modificar(u);
        if (modificado) {
            coleccionU.actualizarUsuario(u);
        }

        return modificado;
    }

    public boolean eliminar(Usuario u) throws SQLException {
        Validador.noNulo(u, "El usuario es NULO");
        Validador.idValido(u.getIdUsuario(), "El ID del usuario es invalido");

        boolean eliminado = dao.eliminar(u);
        if (eliminado) {
            coleccionU.eliminarUsuario(u);
        }

        return eliminado;
    }

    public void validarUsuario(Usuario u) {
        Validador.noNulo(u, "El usuario es NULO");
        Validador.noNulo(u.getEmail(), "El email es NULO");
        Validador.noNulo(u.getPassword(), "La contraseña es NULA");
        Validador.noNulo(u.getDniPersona(), "El DNI es NULO");

        Validador.textoNoVacio(u.getEmail(), "El email no puede estar vacio");
        Validador.textoNoVacio(u.getPassword(), "La contraseña no puede estar vacia");
        Validador.dniValido(u.getDniPersona().trim());

        String emailNormalizado = u.getEmail().trim().toLowerCase();
        String dniNormalizado = u.getDniPersona().trim();
        String password = u.getPassword();

        u.setEmail(emailNormalizado);
        u.setDniPersona(dniNormalizado);

        // Validacion EMAIL: al menos 5 caracteres antes de @gmail.com
        if (!emailNormalizado.endsWith(DOMINIO_EMAIL)) {
            throw new ParametroInvalidoException("El email debe terminar con " + DOMINIO_EMAIL);
        }
        String parteLocal = emailNormalizado.substring(0, emailNormalizado.length() - DOMINIO_EMAIL.length());
        if (parteLocal.length() < MIN_CARACTERES_EMAIL) {
            throw new ParametroInvalidoException("El email debe tener al menos " + MIN_CARACTERES_EMAIL
                    + " caracteres antes de " + DOMINIO_EMAIL);
        }
        if (parteLocal.contains("@") || parteLocal.contains(" ")) {
            throw new ParametroInvalidoException("El email contiene caracteres no validos");
        }

        // Validacion CONTRASEÑA: entre 5 y 8 caracteres, sin espacios, al menos 1 letra y 1 numero
        if (password.length() < MIN_PASSWORD || password.length() > MAX_PASSWORD) {
            throw new ParametroInvalidoException("La contraseña debe tener entre " + MIN_PASSWORD
                    + " y " + MAX_PASSWORD + " caracteres");
        }
        if (password.contains(" ")) {
            throw new ParametroInvalidoException("La contraseña no puede contener espacios");
        }

        boolean tieneLetra = false;
        boolean tieneNumero = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                tieneLetra = true;
            }
            if (Character.isDigit(c)) {
                tieneNumero = true;
            }
        }

        if (!(tieneLetra && tieneNumero)) {
            throw new ParametroInvalidoException("La contraseña tiene que tener al menos una letra y un numero");
        }
    }

    public Usuario findUsuarioByEmailPassword(String email, String password) {
        Validador.noNulo(email, "El email es NULO");
        Validador.noNulo(password, "La contraseña es NULA");
        Validador.textoNoVacio(email, "El email no puede estar vacio");
        Validador.textoNoVacio(password, "La contraseña no puede estar vacia");

        String emailNormalizado = email.trim().toLowerCase();
        return dao.buscarPorEmailYPassword(emailNormalizado, password);
    }

    public Usuario findUsuarioByID(Integer idUsuario) {
        Validador.noNulo(idUsuario, "El ID del usuario es NULO");
        Validador.idValido(idUsuario, "El ID del usuario es invalido");

        return dao.buscarPorId(idUsuario);
    }

}