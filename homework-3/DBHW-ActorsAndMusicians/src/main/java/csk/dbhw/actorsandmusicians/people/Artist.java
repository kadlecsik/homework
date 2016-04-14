package csk.dbhw.actorsandmusicians.people;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("A")
@NamedQueries({
@NamedQuery(
        name = "Artist.findByName",
        query = "SELECT p FROM Artist p WHERE p.name LIKE :name"
        
),
@NamedQuery(
        name = "Artist.getFemaleArtists",
        query = "SELECT p FROM Artist p WHERE p.gender = 1"
        
),
@NamedQuery(
        name = "Artist.getMaleArtists",
        query = "SELECT p FROM Artist p WHERE p.gender = 0"       
),   
    
})
public class Artist extends Person {

    @Enumerated(EnumType.STRING)
    private MainProfession mainProfession;

    @ManyToOne
    private Band band;

    public Artist() {
        //Paraméter nélküli konstruktor
    }

    public Artist(MainProfession mainProfession, Band band) {
        this.mainProfession = mainProfession;
        this.band = band;
    }

    public Artist(MainProfession mainProfession, Band band, Gender gender, String name) {
        super(gender, name);
        this.mainProfession = mainProfession;
        this.band = band;
    }

    public MainProfession getMainProfession() {
        return mainProfession;
    }

    public void setMainProfession(MainProfession mainProfession) {
        this.mainProfession = mainProfession;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

}
