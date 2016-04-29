package csk.amusementpark.bean.facade;

import csk.amusementpark.entity.amusementpark.GuestBookEntry;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class GuestBookEntryFacade {

    @PersistenceContext(unitName = "amusementParkPU")
    private EntityManager em;

    public List<GuestBookEntry> getByUserIdAndParkId(Integer visitorId, Integer parkId) {

        return em.createQuery("SELECT e FROM GuestBookEntry e WHERE e.visitor.id = :vid AND e.amusementPark.id = :aid", GuestBookEntry.class).setParameter("vid", visitorId).setParameter("aid", parkId).getResultList();
    }

    public List<GuestBookEntry> getByParkId(Integer parkId) {
        return em.createQuery("SELECT e FROM GuestBookEntry e WHERE e.amusementPark.id = :aid", GuestBookEntry.class).setParameter("aid", parkId).getResultList();
    }

    public GuestBookEntry getById(Integer entryId) {
        return em.createNamedQuery("getEntryById", GuestBookEntry.class).setParameter("id", entryId).getSingleResult();
    }
}
