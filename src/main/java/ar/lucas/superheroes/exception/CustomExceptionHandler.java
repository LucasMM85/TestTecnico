package ar.lucas.superheroes.exception;

import ar.lucas.superheroes.annotations.Contar;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lucas Mussi
 * date 13/4/2021
 */

@RestController
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResponseStatusException.class})
    @Contar
    protected ResponseEntity<?> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", ex.getRawStatusCode());
        response.put("mensaje", ex.getReason());
        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.UNAUTHORIZED.value());
        response.put("mensaje", "No tiene permisos para realizar esta operación");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

}
