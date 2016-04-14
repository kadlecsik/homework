package csk.dbhw.actorsandmusicians.people;

import csk.dbhw.actorsandmusicians.arts.Album;
import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "job",
        discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("|")
@Table(name = "people")
@NamedQuery(
        name = "Person.findByName",
        query = "SELECT p FROM Person p WHERE p.name LIKE :name"
        
)
public class Person implements Serializable {

    @Id
    @GeneratedValue
    protected Integer id;

    @Enumerated(EnumType.ORDINAL)
    protected Gender gender;

    protected String name;

    @ManyToMany
    private List<Album> albums;

    public Person() {
        //Paraméter nélküli konstruktor
    }

    public Person(Gender gender, String name) {
        this.gender = gender;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

}
