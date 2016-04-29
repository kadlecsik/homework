package csk.amusementpark.dto;

import csk.amusementpark.annotation.Validate;
import csk.amusementpark.entity.machine.Machine;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validate
public class MachineDTO {

    private Integer id;

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    @Min(1)
    private Double machineSize;

    @NotNull
    @Min(1)
    private Integer ticketPrice;

    @NotNull
    @Min(1)
    private Integer seats;

    @NotNull
    private String machineType;

    @NotNull
    @Min(1)
    private Integer minimumRequiredAge;
    
    @NotNull
    @Min(1)
    private Integer price;

    public MachineDTO() {
        //Paraméter nélküli konstruktor
    }

    public MachineDTO(String name, Double machineSize, Integer ticketPrice, Integer seats, String machineType, Integer minimumRequiredAge) {
        this.name = name;
        this.machineSize = machineSize;
        this.ticketPrice = ticketPrice;
        this.seats = seats;
        this.machineType = machineType;
        this.minimumRequiredAge = minimumRequiredAge;
    }

    public MachineDTO(Integer id, String name, Double machineSize, Integer ticketPrice, Integer seats, String machineType, Integer minimumRequiredAge) {
        this.id = id;
        this.name = name;
        this.machineSize = machineSize;
        this.ticketPrice = ticketPrice;
        this.seats = seats;
        this.machineType = machineType;
        this.minimumRequiredAge = minimumRequiredAge;
    }

    public MachineDTO(Machine machine) {
        this(machine.getId(), machine.getName(), machine.getMachineSize(), machine.getTicketPrice(), machine.getSeats(), machine.getMachineType().toString(), machine.getMinimumRequiredAge());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMachineSize() {
        return machineSize;
    }

    public void setMachineSize(Double machineSize) {
        this.machineSize = machineSize;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public Integer getMinimumRequiredAge() {
        return minimumRequiredAge;
    }

    public void setMinimumRequiredAge(Integer minimumRequiredAge) {
        this.minimumRequiredAge = minimumRequiredAge;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
