package co.com.ud.utiles.validators.exception;

import lombok.Getter;

public class MailConstraintException extends RuntimeException  {
    @Getter
    private String campo;

    public MailConstraintException(String message, String campo) {
        super(message);
        this.campo = campo;
    }

    public MailConstraintException(String message) {
        super(message);
    }

}
