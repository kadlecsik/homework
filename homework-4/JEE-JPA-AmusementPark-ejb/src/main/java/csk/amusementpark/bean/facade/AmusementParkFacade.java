package csk.amusementpark.bean.facade;

import csk.amusementpark.entity.amusementpark.AmusementPark;
import csk.amusementpark.entity.visitor.Visitor;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class AmusementParkFacade {

    @PersistenceContext(unitName = "amusementParkPU")
    private EntityManager em;

    public List<AmusementPark> getAll() {

        return em.createNamedQuery("getAllParks", AmusementPark.class).getResultList();
    }

    public AmusementPark getById(Integer id) {

        return em.createNamedQuery("getParkById", AmusementPark.class).setParameter("id", id).getSingleResult();
    }

    public Double getAllocatedAreaById(Integer id) {
        return em.createQuery("SELECT COALESCE(SUM(m.machineSize),0) FROM Machine m WHERE m.amusementPark.id = :id", Double.class).setParameter("id", id).getSingleResult();

    }

    public Double getNumberVisitorsById(Integer id) {

        return em.createQuery("SELECT COALESCE(COUNT(v),0) FROM Visitor v WHERE v.inAmusementPark = :id AND v.active = TRUE", Double.class).setParameter("id", id).getSingleResult();

    }

    public Long getNumberOfRestingVisitorsByParkId(Integer id) {
        return em.createQuery("SELECT COALESCE(COUNT(v),0) FROM Visitor v WHERE v.inAmusementPark.id = :id AND v.active = TRUE AND v.status = csk.amusementpark.entity.visitor.Status.RESTING", Long.class).setParameter("id", id).getSingleResult();
    }

    public List<Visitor> getVisitorsWithEnoughMoneyByParkId(Integer id) {
        int minPrice = em.createQuery("SELECT COALESCE(MIN(m.ticketPrice),0) FROM Machine m WHERE m.amusementPark.id = :id", Integer.class).setParameter("id", id).getSingleResult();

        return em.createQuery("SELECT v FROM Visitor v WHERE v.inAmusementPark.id = :id AND v.active = TRUE and v.money >= :money", Visitor.class).setParameter("id", id).setParameter("money", minPrice).getResultList();
    }
}
