package csk.amusementpark.entity.visitor;

import csk.amusementpark.dto.VisitorDTO;
import csk.amusementpark.entity.amusementpark.AmusementPark;
import csk.amusementpark.entity.machine.Machine;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

@Entity
@NamedQueries({
        @NamedQuery(name = "getAllVisitors", query = "SELECT v FROM Visitor v"),
        @NamedQuery(name = "getVisitorById", query = "SELECT v FROM Visitor v WHERE v.id = :id")
})
public class Visitor implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private Integer money;

    private Integer age;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "ENTRY_DATE")
    private Date dateOfEntry;

    private Boolean active;

    @ManyToOne
    private AmusementPark inAmusementPark;
    
    @ManyToOne
    private Machine onMachine;

    public Visitor() {
        //Paraméter nélküli konstruktor
    }

    public Visitor(Status status, Integer money, Integer age, Date dateOfEntry, Boolean active, AmusementPark inAmusementPark) {
        this.status = status;
        this.money = money;
        this.age = age;
        this.dateOfEntry = dateOfEntry;
        this.active = active;
        this.inAmusementPark = inAmusementPark;
    }

    public Visitor(Integer id, Status status, Integer money, Integer age, Date dateOfEntry, Boolean active, AmusementPark inAmusementPark) {
        this.id = id;
        this.status = status;
        this.money = money;
        this.age = age;
        this.dateOfEntry = dateOfEntry;
        this.active = active;
        this.inAmusementPark = inAmusementPark;
    }

    public Visitor(VisitorDTO dto) {
        this.money = dto.getMoney();
        this.age = dto.getAge();
        this.dateOfEntry = dto.getDateOfEntry();
        this.active = dto.getActive() == null ? false : dto.getActive();

        if (dto.getStatus() == null) {
            this.status = Status.RESTING;
        } else {
            switch (dto.getStatus().toLowerCase()) {
                case "resting":
                    this.status = Status.RESTING;
                    break;
                case "on_machine":
                case "onmachine":
                case "on machine":
                case "on-machine":
                    this.status = Status.ON_MACHINE;
                    break;
                default:
                    throw new IllegalArgumentException("Visitor status " + dto.getStatus() + " does not exist.");
            }
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public AmusementPark getInAmusementPark() {
        return inAmusementPark;
    }

    public void setInAmusementPark(AmusementPark inAmusementPark) {
        this.inAmusementPark = inAmusementPark;
    }

    public Machine getOnMachine() {
        return onMachine;
    }

    public void setOnMachine(Machine onMachine) {
        this.onMachine = onMachine;
    }
    
    
}
