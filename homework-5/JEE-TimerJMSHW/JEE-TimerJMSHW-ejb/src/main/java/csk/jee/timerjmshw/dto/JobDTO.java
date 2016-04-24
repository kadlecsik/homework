package csk.jee.timerjmshw.dto;

import java.io.Serializable;
import java.time.LocalTime;

public class JobDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private Integer estimatedDuration;
    private LocalTime timestamp;

    public JobDTO() {
        //Paraméter nélküli konstruktor
    }

    public JobDTO(String id, LocalTime timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public JobDTO(String id, Integer estimatedDuration, LocalTime timestamp) {
        this.id = id;
        this.estimatedDuration = estimatedDuration;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

}
