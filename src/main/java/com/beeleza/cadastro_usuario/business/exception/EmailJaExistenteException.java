package com.beeleza.cadastro_usuario.business.exception;

public class EmailJaExistenteException extends RuntimeException {
    public EmailJaExistenteException(String message) {
        super(message);
    }
}