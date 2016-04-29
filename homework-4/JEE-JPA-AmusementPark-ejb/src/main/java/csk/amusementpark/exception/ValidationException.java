package csk.amusementpark.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class ValidationException extends RuntimeException {

    public ValidationException() {
        super();
    }

    public ValidationException(String string) {
        super(string);
    }
}
