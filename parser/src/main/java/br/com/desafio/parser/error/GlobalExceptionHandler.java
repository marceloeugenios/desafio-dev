package br.com.desafio.parser.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NaoAutenticadoException.class})
    public ResponseEntity<Object> handleNaoAutenticadoException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}