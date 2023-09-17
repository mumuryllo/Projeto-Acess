package main.controllers.exceptions;
import jakarta.servlet.http.HttpServletRequest;
import main.services.exceptions.DataViolationException;
import main.services.exceptions.ObjNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ResuorceExceptionHandler {

    @ExceptionHandler(ObjNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjNotFoundException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(), "não encontrado!", ex.getMessage(), request.getRequestURI()
                );
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataViolationException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "violaçao de dados!", ex.getMessage(), request.getRequestURI()
        );
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validarErros(MethodArgumentNotValidException ex, HttpServletRequest request){
        ValidarErros erros = new ValidarErros(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Erro na validação dos campos!", "Erro na validação dos campos!", request.getRequestURI()
        );

        for (FieldError x:ex.getBindingResult().getFieldErrors()) {
           erros.addErros(x.getField(), x.getDefaultMessage());
        }

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }


}
