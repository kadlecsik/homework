package csk.amusementpark.bean.facade;

import csk.amusementpark.entity.visitor.Visitor;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class VisitorFacade {

    @PersistenceContext(unitName = "amusementParkPU")
    private EntityManager em;

    public List<Visitor> getAll() {

        return em.createNamedQuery("getAllVisitors", Visitor.class).getResultList();
    }

    public Visitor getById(Integer id) {

        return em.createNamedQuery("getVisitorById", Visitor.class).setParameter("id", id).getSingleResult();
    }
    
    public List<Visitor> getVisitorsByMachineId(Integer machineId)
    {
        return em.createQuery("SELECT v FROM Visitor v WHERE v.onMachine.id = :id",Visitor.class).setParameter("id", machineId).getResultList();
    }
}
