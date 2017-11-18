package net.unibave.ecommerce.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityNotFoundException;

public abstract class AbstractRepository<T, PK> {

    @PersistenceContext
    private EntityManager em;

    public List<T> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(getEntityClass());
        criteria.from(getEntityClass());
        return em.createQuery(criteria).getResultList();
    }

    public T find(PK id) {
        T entity = em.find(getEntityClass(), id);
        if (entity == null) {
            throw new EntityNotFoundException("Entidade com id " + id + " não encontrado.");
        }
        
        return entity;
    }

    private Class<T> getEntityClass() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }

}
