package csk.dbhw.actorsandmusicians.arts;

import csk.dbhw.actorsandmusicians.people.Band;
import csk.dbhw.actorsandmusicians.people.Person;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "Album.findByTitle",
            query = "SELECT a FROM Album a WHERE a.title LIKE :title"),
    @NamedQuery(
            name = "Album.afterDate",
            query = "SELECT a FROM Album a WHERE a.releaseDate > :date"),
    @NamedQuery(
            name = "Album.findByBand",
            query = "SELECT a FROM Album a WHERE a.band.name like :band"),
    @NamedQuery(
            name = "Album.findWithFemaleMusician",
            query = "SELECT DISTINCT a FROM Album a INNER JOIN a.band.artists p WHERE p.gender = 1")

})

public class Album extends ArtProduct implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @ManyToOne
    private Band band;

    @ManyToMany(mappedBy = "albums")
    private List<Person> contributors = new ArrayList<>();

    public Album() {
        //Paraméter nélküli konstruktor
    }

    public Album(Date releaseDate, Band band, String title, String description) {
        super(title, description);
        this.releaseDate = releaseDate;
        this.band = band;
    }

    public List<Person> getContributors() {
        return contributors;
    }

    public void setContributors(List<Person> contributors) {
        this.contributors = contributors;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

}
