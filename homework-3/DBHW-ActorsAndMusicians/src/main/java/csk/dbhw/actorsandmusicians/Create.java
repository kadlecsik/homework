package csk.dbhw.actorsandmusicians;

import csk.dbhw.actorsandmusicians.arts.Album;
import csk.dbhw.actorsandmusicians.people.Artist;
import csk.dbhw.actorsandmusicians.people.Band;
import csk.dbhw.actorsandmusicians.people.Gender;
import csk.dbhw.actorsandmusicians.people.MainProfession;
import csk.dbhw.actorsandmusicians.people.Scientist;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class Create {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    private static final Logger LOG = Logger.getLogger(Create.class.getName());

    private Create() {
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
    }

    private static void connectData() {
        tx.begin();
        
        final String findArtistByName = "Artist.findByName";
        final String findBandByName = "Band.findByName";
        final String findScientistByName = "Scientist.findByName";
        final String findAlbumByTitle = "Album.findByTitle";
        
        final String name = "name";
        final String title = "title";
        
        
        Artist cristopherLee = em.createNamedQuery(findArtistByName, Artist.class).setParameter(name, "Cristopher Lee").getSingleResult();
        Artist fabioLione = em.createNamedQuery(findArtistByName, Artist.class).setParameter(name, "Fabio Lione").getSingleResult();
        Artist alexStaropoli = em.createNamedQuery(findArtistByName, Artist.class).setParameter(name, "Alex Staropoli").getSingleResult();

        Band rhapsody = em.createNamedQuery(findBandByName, Band.class).setParameter(name, "Rhapsody of Fire").getSingleResult();
        Album darkSecret = em.createNamedQuery(findAlbumByTitle, Album.class).setParameter(title, "Symphony of Enchanted Lands II – The Dark Secret").getSingleResult();
        Album sol1 = em.createNamedQuery(findAlbumByTitle, Album.class).setParameter(title, "Symphony of Enchanted Lands").getSingleResult();

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

        Scientist richardDawkins = em.createNamedQuery(findScientistByName, Scientist.class).setParameter(name, "Richard Dawkins").getSingleResult();
        Artist tuomasHolopainen = em.createNamedQuery(findArtistByName, Artist.class).setParameter(name, "Tuomas Holopainen").getSingleResult();
        Artist floorJansen = em.createNamedQuery(findArtistByName, Artist.class).setParameter(name, "Floor Jansen").getSingleResult();

        Band nightwish = em.createNamedQuery(findBandByName, Band.class).setParameter(name, "Nightwish").getSingleResult();
        Album endlessForms = em.createNamedQuery(findAlbumByTitle, Album.class).setParameter(title, "Endless Forms Most Beautiful").getSingleResult();

        nightwish.getArtists().add(tuomasHolopainen);
        tuomasHolopainen.setBand(nightwish);
        nightwish.getArtists().add(floorJansen);
        floorJansen.setBand(nightwish);
        endlessForms.setBand(nightwish);
        nightwish.getAlbums().add(endlessForms);
        endlessForms.getContributors().add(richardDawkins);
        richardDawkins.getAlbums().add(endlessForms);

        tx.commit();

    }

    public static void main(String[] args) {

        try {
            emf = Persistence.createEntityManagerFactory("artistWritePU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            addData();
            em.close();
            emf.close();
            emf = Persistence.createEntityManagerFactory("artistReadPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            connectData();
        } catch (PersistenceException e) {
            LOG.severe(e.getMessage());
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
