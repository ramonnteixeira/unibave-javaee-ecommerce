package net.unibave.ecommerce.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractService<T, PK> {

    @PersistenceContext
    private EntityManager em;

    public T save(T entity) {
        T merged = em.merge(entity);
        return merged;
    }

    public void delete(PK id) {
        T entity = em.find(getEntityClass(), id);
        em.remove(entity);
    }

    private Class<T> getEntityClass() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }

}
