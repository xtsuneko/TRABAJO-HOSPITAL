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


    public static void direccionValida(String direccion) {
        noNulo(direccion, "La direccion es NULA");
        String dir = direccion.trim();
        if (dir.isEmpty())
            throw new ParametroInvalidoException("La direccion no puede estar vacia");
        if (dir.length() < 5 || dir.length() > 100)
            throw new ParametroInvalidoException("La direccion debe tener entre 5 y 100 caracteres");
        if (!dir.matches("[\\p{L}0-9 .,'°º/#-]+"))
            throw new ParametroInvalidoException("La direccion contiene caracteres invalidos");
        if (!dir.matches(".*\\p{L}.*"))
            throw new ParametroInvalidoException("La direccion debe incluir el nombre de la calle");
        if (!dir.matches(".*\\d.*"))
            throw new ParametroInvalidoException("La direccion debe incluir la altura (numero)");
    }
}