package csk.amusementpark.entity.machine;

import csk.amusementpark.dto.MachineDTO;
import csk.amusementpark.entity.amusementpark.AmusementPark;
import csk.amusementpark.entity.visitor.Visitor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "getAllMachinesByParkId", query = "SELECT m FROM Machine m WHERE m.amusementPark.id = :id"),
    @NamedQuery(name = "getMachineById", query = "SELECT m FROM Machine m WHERE m.id = :id"),})
public class Machine implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Column(name = "MACHINE_SIZE")
    private Double machineSize;

    @Column(name = "TICKET_PRICE")
    private Integer ticketPrice;

    private Integer seats;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private MachineType machineType;

    @Column(name = "MIN_AGE")
    private Integer minimumRequiredAge;

    @ManyToOne
    private AmusementPark amusementPark;

    @OneToMany
    private final List<Visitor> visitors = new ArrayList<>();

    public Machine() {
        //Paraméter nélküli konstruktor
    }
    
    public Machine(MachineDTO dto) {
        this.name = dto.getName();
        this.machineSize = dto.getMachineSize();
        this.ticketPrice = dto.getTicketPrice();
        this.seats = dto.getSeats();
        this.minimumRequiredAge = dto.getMinimumRequiredAge();

        switch (dto.getMachineType().toLowerCase()) {
            case "go_cart":
            case "gocart":
            case "go-cart":
            case "go cart":
                this.machineType = MachineType.GO_CART;
                break;
            case "dodgem":
                this.machineType = MachineType.DODGEM;
                break;
            case "carousel":
                this.machineType = MachineType.CAROUSEL;
                break;
            case "roller_coaster":
            case "rollercoaster":
            case "roller-coaster":
            case "roller coaster":
                this.machineType = MachineType.ROLLER_COASTER;
                break;
            default:
                throw new IllegalArgumentException("Machine type " + dto.getMachineType() + " does not exist.");
        }
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

    public MachineType getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }

    public Integer getMinimumRequiredAge() {
        return minimumRequiredAge;
    }

    public void setMinimumRequiredAge(Integer minimumRequiredAge) {
        this.minimumRequiredAge = minimumRequiredAge;
    }

    public AmusementPark getAmusementPark() {
        return amusementPark;
    }

    public void setAmusementPark(AmusementPark amusementPark) {
        this.amusementPark = amusementPark;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

}
