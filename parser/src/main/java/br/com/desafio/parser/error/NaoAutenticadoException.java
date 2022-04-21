package br.com.desafio.parser.error;

public class NaoAutenticadoException extends RuntimeException {

    public NaoAutenticadoException(String message) {
        super(message);
    }
}
