package csk.dbhw.actorsandmusicians;

import csk.dbhw.actorsandmusicians.arts.Album;
import csk.dbhw.actorsandmusicians.people.Artist;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class Query {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    private static final Logger LOG = Logger.getLogger(Create.class.getName());

    private Query() {
    }

    private static void getData() {

        tx.begin();

        List<Artist> maleArtists = em.createNamedQuery("Artist.getMaleArtists", Artist.class).getResultList();
        List<Artist> fEMaleArtists = em.createNamedQuery("Artist.getFemaleArtists", Artist.class).getResultList();

        LOG.log(Level.INFO, "Female artists:");
        fEMaleArtists.stream().forEach(
                a -> LOG.log(Level.INFO, a.getName())
        );
        LOG.log(Level.INFO, "Male artists:");
        maleArtists.stream().forEach(
                a -> LOG.log(Level.INFO, a.getName())
        );

        List<Album> albumsAfter2000 = em.createNamedQuery("Album.afterDate", Album.class).setParameter("date", new Date(99, 12, 31)).getResultList();
        LOG.log(Level.INFO, "Albums after 2000:");
        albumsAfter2000.stream().forEach(
                (Album a) -> LOG.log(Level.INFO, "{0} {1}", new Object[]{a.getTitle(), a.getReleaseDate().toString()})
        );

        List<Album> albumsByRhapsody = em.createNamedQuery("Album.findByBand", Album.class).setParameter("band", "Rhapsody of Fire").getResultList();
        LOG.log(Level.INFO, "Albums by Rhapsody of Fire");
        albumsByRhapsody.stream().forEach(
                a -> LOG.log(Level.INFO, a.getTitle())
        );

        List<Album> albumsWithFEMale = em.createNamedQuery("Album.findWithFemaleMusician", Album.class).getResultList();
        LOG.log(Level.INFO, "Albums with female musician:");
        albumsWithFEMale.stream().forEach(
                a -> LOG.log(Level.INFO, a.getTitle())
        );

        tx.commit();
    }

    public static void main(String[] args) {

        try {
            emf = Persistence.createEntityManagerFactory("artistReadPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            getData();
        } catch (PersistenceException e) {
            LOG.severe((Supplier<String>) e);
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }

    }

}
