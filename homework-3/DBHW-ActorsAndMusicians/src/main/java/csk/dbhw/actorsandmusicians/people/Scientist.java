package csk.dbhw.actorsandmusicians.people;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("S")
@NamedQuery(
        name = "Scientist.findByName",
        query = "SELECT p FROM Scientist p WHERE p.name LIKE :name"
        
)
public class Scientist extends Person {

    private String fieldOfScience;

    public Scientist() {
        //Paraméter nélküli konstruktor
    }

    public Scientist(String fieldOfScience) {
        this.fieldOfScience = fieldOfScience;
    }

    public Scientist(String fieldOfScience, Gender gender, String name) {
        super(gender, name);
        this.fieldOfScience = fieldOfScience;
    }

    public String getFieldOfScience() {
        return fieldOfScience;
    }

    public void setFieldOfScience(String fieldOfScience) {
        this.fieldOfScience = fieldOfScience;
    }

}
