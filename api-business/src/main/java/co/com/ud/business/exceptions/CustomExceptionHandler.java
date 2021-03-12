package co.com.ud.business.exceptions;

import co.com.ud.utiles.dto.ExceptionValidatorDto;
import co.com.ud.utiles.validators.exception.MailConstraintException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MailConstraintException.class)
    public final ResponseEntity<Object> handlerMailConstraintException(MailConstraintException ex, WebRequest request) {
        return new ResponseEntity<>(ExceptionValidatorDto.builder()
                .mensaje("Correo Electronico con formato invalido")
                .tipoError("VALIDADOR")
                .campo(ex.getCampo())
                .build(), HttpStatus.OK);
    }
}
