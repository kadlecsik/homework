package csk.mobilewebshop.exception;

public class ExceptionDTO {

    private String message;

    public ExceptionDTO() {
        //Default construction
    }

    public ExceptionDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
