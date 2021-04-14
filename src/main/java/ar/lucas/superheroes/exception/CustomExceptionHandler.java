package ar.lucas.superheroes.exception;

import org.springframework.http.ResponseEntity;
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
    protected ResponseEntity<?> handleRuntimeException(ResponseStatusException ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", ex.getRawStatusCode());
        response.put("mensaje", ex.getReason());
        return ResponseEntity.status(ex.getStatus()).body(response);
    }

}
