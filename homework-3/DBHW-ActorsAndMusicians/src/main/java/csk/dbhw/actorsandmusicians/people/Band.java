package csk.dbhw.actorsandmusicians.people;

import csk.dbhw.actorsandmusicians.arts.Album;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(
        name = "Band.findByName",
        query = "SELECT b FROM Band b WHERE b.name LIKE :name"
        
)
public class Band implements Serializable {

    @Id
    private String name;

    @ElementCollection(targetClass = String.class)
    private List<String> genres = new ArrayList<>();

    @OneToMany(mappedBy = "band")
    private List<Artist> artists = new ArrayList<>();

    @OneToMany(mappedBy = "band")
    private List<Album> albums = new ArrayList<>();

    public Band() {
        //Paraméter nélküli konstruktor
    }

    public Band(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

}
