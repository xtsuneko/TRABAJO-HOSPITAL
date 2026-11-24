package UTIL;

import EXCEPCIONES.ParametroInvalidoException;
import EXCEPCIONES.ParametroNullException;

public class Validador {

    public static void noNulo(Object o, String mensaje) {
        if (o == null) throw new ParametroNullException(mensaje);
    }

    public static void idValido(int id, String mensaje) {
        if (id <= 0) throw new ParametroInvalidoException(mensaje);
    }

    public static void textoNoVacio(String s, String mensaje) {
        if (s == null || s.isBlank()) throw new ParametroInvalidoException(mensaje);
    }

    public static void dniValido(String dni) {
        if (dni == null || !dni.matches("\\d{7,8}"))
            throw new ParametroInvalidoException("El DNI es invalido");
    }
}