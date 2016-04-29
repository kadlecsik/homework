
package csk.amusementpark.dto;

public class MessageDTO {
    
    private String description;
    
    private String message;

    public MessageDTO() {
        //Paraméter nélküli konstruktor
    }

    public MessageDTO(String description, String message) {
        this.description = description;
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
