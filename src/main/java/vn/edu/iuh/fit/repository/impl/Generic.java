package vn.edu.iuh.fit.repository.impl;

import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.repository.CRUDRepository;
import vn.edu.iuh.fit.util.Connection;

import java.util.List;

public class Generic<T, ID> implements CRUDRepository<T, ID> {

    @Override
    public T save(T t) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            return t;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public T findById(Class<T> entityClass, ID id) {
        EntityManager entityManager = getEntityManager();
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll(Class<T> entityClass) {
        EntityManager entityManager = getEntityManager();
        String jpql = "SELECT t FROM " + entityClass.getSimpleName() + " t";
        return entityManager.createQuery(jpql, entityClass).getResultList();
    }

    @Override
    public Boolean update(T t) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(t);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteById(Class<T> entityClass, ID id) {
        EntityManager entityManager = getEntityManager();
        T entity = findById(entityClass, id);
        if (entity != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(entity);
                entityManager.getTransaction().commit();
                return true;
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private EntityManager getEntityManager() {
        EntityManager entityManager = Connection.getInstance().getEntityManagerFactory().createEntityManager();
        return entityManager;
    }
}
