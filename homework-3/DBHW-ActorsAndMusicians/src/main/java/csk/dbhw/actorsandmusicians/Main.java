package csk.dbhw.actorsandmusicians;

import csk.dbhw.actorsandmusicians.arts.Album;
import csk.dbhw.actorsandmusicians.people.Artist;
import csk.dbhw.actorsandmusicians.people.Band;
import csk.dbhw.actorsandmusicians.people.Gender;
import csk.dbhw.actorsandmusicians.people.MainProfession;
import csk.dbhw.actorsandmusicians.people.Scientist;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    
    private Main() {
    }
 
    private static void addData() {
        /*      Külsős résztvevők */
        Artist cristopherLee = new Artist(MainProfession.ACTOR, null, Gender.MALE, "Cristopher Lee");
        Scientist richardDawkins = new Scientist("evolutionary biology", Gender.MALE, "Richard Dawkins");

        /*      Együttesek      */
        Artist fabioLione = new Artist(MainProfession.MUSICIAN, null, Gender.MALE, "Fabio Lione");
        Artist alexStaropoli = new Artist(MainProfession.MUSICIAN, null, Gender.MALE, "Alex Staropoli");

        Band rhapsody = new Band("Rhapsody of Fire");
        rhapsody.getGenres().add("Symphonic power metal");
        rhapsody.getGenres().add("Neoclassical metal");

        Artist tuomasHolopainen = new Artist(MainProfession.MUSICIAN, null, Gender.MALE, "Tuomas Holopainen");
        Artist floorJansen = new Artist(MainProfession.MUSICIAN, null, Gender.FEMALE, "Floor Jansen");

        Band nightwish = new Band("Nightwish");
        nightwish.getGenres().add("Symphonic metal");

        Album sol1 = new Album(new Date(98, 10, 5), null, "Symphony of Enchanted Lands", null);
        Album darkSecret = new Album(new Date(104, 9, 27), null, "Symphony of Enchanted Lands II – The Dark Secret", null);

        Album endlessForms = new Album(new Date(115, 3, 25), null, "Endless Forms Most Beautiful", null);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("artistPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(cristopherLee);
        em.persist(richardDawkins);

        em.persist(fabioLione);
        em.persist(alexStaropoli);
        em.persist(rhapsody);

        em.persist(tuomasHolopainen);
        em.persist(floorJansen);
        em.persist(nightwish);

        em.persist(sol1);
        em.persist(darkSecret);
        em.persist(endlessForms);

        tx.commit();

        em.close();
        emf.close();
    }

    private static void connectData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("artistPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Artist cristopherLee = em.createNamedQuery("Artist.findByName", Artist.class).setParameter("name", "Cristopher Lee").getSingleResult();
        Artist fabioLione = em.createNamedQuery("Artist.findByName", Artist.class).setParameter("name", "Fabio Lione").getSingleResult();
        Artist alexStaropoli = em.createNamedQuery("Artist.findByName", Artist.class).setParameter("name", "Alex Staropoli").getSingleResult();

        Band rhapsody = em.createNamedQuery("Band.findByName", Band.class).setParameter("name", "Rhapsody of Fire").getSingleResult();
        Album darkSecret = em.createNamedQuery("Album.findByTitle", Album.class).setParameter("title", "Symphony of Enchanted Lands II – The Dark Secret").getSingleResult();
        Album sol1 = em.createNamedQuery("Album.findByTitle", Album.class).setParameter("title", "Symphony of Enchanted Lands").getSingleResult();

        rhapsody.getArtists().add(fabioLione);
        fabioLione.setBand(rhapsody);
        rhapsody.getArtists().add(alexStaropoli);
        alexStaropoli.setBand(rhapsody);
        darkSecret.setBand(rhapsody);
        rhapsody.getAlbums().add(darkSecret);
        sol1.setBand(rhapsody);
        rhapsody.getAlbums().add(sol1);
        darkSecret.getContributors().add(cristopherLee);
        cristopherLee.getAlbums().add(darkSecret);

        Scientist richardDawkins = em.createNamedQuery("Scientist.findByName", Scientist.class).setParameter("name", "Richard Dawkins").getSingleResult();
        Artist tuomasHolopainen = em.createNamedQuery("Artist.findByName", Artist.class).setParameter("name", "Tuomas Holopainen").getSingleResult();
        Artist floorJansen = em.createNamedQuery("Artist.findByName", Artist.class).setParameter("name", "Floor Jansen").getSingleResult();

        Band nightwish = em.createNamedQuery("Band.findByName", Band.class).setParameter("name", "Nightwish").getSingleResult();
        Album endlessForms = em.createNamedQuery("Album.findByTitle", Album.class).setParameter("title", "Endless Forms Most Beautiful").getSingleResult();

        nightwish.getArtists().add(tuomasHolopainen);
        tuomasHolopainen.setBand(nightwish);
        nightwish.getArtists().add(floorJansen);
        floorJansen.setBand(nightwish);
        endlessForms.setBand(nightwish);
        nightwish.getAlbums().add(endlessForms);
        endlessForms.getContributors().add(richardDawkins);
        richardDawkins.getAlbums().add(endlessForms);

        tx.commit();

        em.close();
        emf.close();
    }

    private static void getData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("artistPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        List<Artist> maleArtists = em.createNamedQuery("Artist.getMaleArtists", Artist.class).getResultList();
        List<Artist> femaleArtists = em.createNamedQuery("Artist.getFemaleArtists", Artist.class).getResultList();

        LOG.log(Level.INFO, "Female artists:");
        femaleArtists.stream().forEach(a -> 
            LOG.log(Level.INFO, a.getName())
        );
        LOG.log(Level.INFO, "Male artists:");
        maleArtists.stream().forEach(a -> 
            LOG.log(Level.INFO, a.getName())
        );

        List<Album> albumsAfter2000 = em.createNamedQuery("Album.afterDate", Album.class).setParameter("date", new Date(99, 12, 31)).getResultList();
        LOG.log(Level.INFO, "Albums after 2000:");
        albumsAfter2000.stream().forEach(a
                -> LOG.log(Level.INFO, a.getTitle() + " " + a.getReleaseDate().toString())
        );

        List<Album> albumsByRhapsody = em.createNamedQuery("Album.findByBand", Album.class).setParameter("band", "Rhapsody of Fire").getResultList();
        LOG.log(Level.INFO, "Albums by Rhapsody of Fire");
        albumsByRhapsody.stream().forEach(a
                -> LOG.log(Level.INFO, a.getTitle())
        );

        List<Album> albumsWithFemale = em.createNamedQuery("Album.findWithFemaleMusician", Album.class).getResultList();
        LOG.log(Level.INFO, "Albums with female musician:");
        albumsWithFemale.stream().forEach(a
                -> LOG.log(Level.INFO, a.getTitle())
        );

        tx.commit();

        em.close();
        emf.close();
    }

    public static void main(String[] args) {

        //Table Generation Strategy: Drop and Create (1)
        addData();
        //Table Generation Strategy: None (2)
        connectData();
        //Table Generation Strategy: None (3d)
        getData();

    }
}
