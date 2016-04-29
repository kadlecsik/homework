package csk.amusementpark.dto;

import csk.amusementpark.adapter.DateAdapter;
import csk.amusementpark.annotation.Validate;
import csk.amusementpark.entity.visitor.Visitor;
import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Validate
public class VisitorDTO {

    private Integer id;

    private String status;

    @NotNull
    @Min(1)
    private Integer money;

    @NotNull
    @Min(1)
    private Integer age;

    @NotNull
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date dateOfEntry;

    private Boolean active;

    private Integer inAmusementPark;

    private Integer onMachine;

    public VisitorDTO() {
    }

    public VisitorDTO(Integer money, Integer age, Date dateOfEntry) {
        this.money = money;
        this.age = age;
        this.dateOfEntry = dateOfEntry;
    }

    public VisitorDTO(String status, Integer money, Integer age, Date dateOfEntry, Boolean active) {
        this.status = status;
        this.money = money;
        this.age = age;
        this.dateOfEntry = dateOfEntry;
        this.active = active;
    }

    public VisitorDTO(Integer id, String status, Integer money, Integer age, Date dateOfEntry, Boolean active) {
        this.id = id;
        this.status = status;
        this.money = money;
        this.age = age;
        this.dateOfEntry = dateOfEntry;
        this.active = active;
    }

    public VisitorDTO(Visitor visitor) {

        this(visitor.getId(), visitor.getStatus().toString(), visitor.getMoney(), visitor.getAge(), visitor.getDateOfEntry(), visitor.getActive());
        this.inAmusementPark = visitor.getInAmusementPark().getId();
        this.onMachine = visitor.getOnMachine() == null ? null : visitor.getOnMachine().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public Integer getInAmusementPark() {
        return inAmusementPark;
    }

    public void setInAmusementPark(Integer inAmusementPark) {
        this.inAmusementPark = inAmusementPark;
    }

    public Integer getOnMachine() {
        return onMachine;
    }

    public void setOnMachine(Integer onMachine) {
        this.onMachine = onMachine;
    }

}
