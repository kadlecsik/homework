package csk.amusementpark.dto;

public class ErrorDTO {
    
    private String message;

    public ErrorDTO() {
        //Paraméter nélküli konstruktor
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
