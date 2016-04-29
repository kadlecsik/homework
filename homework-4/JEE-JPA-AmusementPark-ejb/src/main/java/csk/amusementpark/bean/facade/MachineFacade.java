package csk.amusementpark.bean.facade;

import csk.amusementpark.entity.machine.Machine;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class MachineFacade {

    @PersistenceContext(unitName = "amusementParkPU")
    private EntityManager em;

    public List<Machine> getAllByParkId(Integer parkId) {

        return em.createNamedQuery("getAllMachinesByParkId", Machine.class).setParameter("id", parkId).getResultList();
    }

    public Machine getById(Integer id) {

        return em.createNamedQuery("getMachineById", Machine.class).setParameter("id", id).getSingleResult();
    }
    
}
