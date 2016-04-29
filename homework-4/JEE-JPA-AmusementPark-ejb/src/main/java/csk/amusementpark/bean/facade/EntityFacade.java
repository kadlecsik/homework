package csk.amusementpark.bean.facade;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class EntityFacade {

    @PersistenceContext(unitName = "amusementParkPU")
    private EntityManager em;

    public <T> T create(T entity) {
        em.persist(entity);
        return entity;
    }

    public <T> T read(Object id, Class<T> clazz) {
        return em.find(clazz, id);
    }

    public <T> T update(T entity) {
        return em.merge(entity);
    }

    public <T> T delete(T entity) {
        em.remove(entity);
        return entity;
    }

}
