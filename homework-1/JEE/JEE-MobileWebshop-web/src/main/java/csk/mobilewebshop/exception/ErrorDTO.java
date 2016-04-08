package csk.mobilewebshop.exception;

public class ErrorDTO {

    private String message;

    public ErrorDTO() {
        //Default construction
    }

    public ErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
