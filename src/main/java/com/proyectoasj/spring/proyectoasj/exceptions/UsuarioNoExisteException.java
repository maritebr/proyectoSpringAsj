package com.proyectoasj.spring.proyectoasj.exceptions;

public class UsuarioNoExisteException extends Exception {

    public UsuarioNoExisteException() {
    }

    public UsuarioNoExisteException(String message) {
        super(message);
    }

    public UsuarioNoExisteException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsuarioNoExisteException(Throwable cause) {
        super(cause);
    }

    public UsuarioNoExisteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
