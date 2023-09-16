package vn.edu.iuh.fit.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import vn.edu.iuh.fit.enties.Log;
import vn.edu.iuh.fit.enties.Role;
import vn.edu.iuh.fit.repository.CRUDRepository;
import vn.edu.iuh.fit.util.Connection;

import java.util.List;

public class LogRepositoryImpl implements CRUDRepository<Log, Long> {
    EntityManager manager;

    public LogRepositoryImpl() {
        manager = Connection.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public Log findById(Class<Log> entityClass, Long id) {
        return manager.find(Log.class, id);
    }

    @Override
    public List<Log> findAll(Class<Log> entityClass) {
       return  manager.createNativeQuery("SELECT * from Log",Log.class).getResultList();
    }

    @Override
    public Log save(Log log) {
        try{
            manager.getTransaction().begin();
            manager.persist(log);
            manager.getTransaction().commit();
            manager.clear();
            return log;
        }catch (Exception e){
            e.printStackTrace();
            manager.getTransaction().rollback();
            return null;
        }
    }
    @Override
    public Boolean update(Log T) {
        try{
            manager.getTransaction().begin();
            manager.merge(T);
            manager.getTransaction().commit();
            manager.clear();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            manager.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Boolean deleteById(Class<Log> entityClass, Long aLong) {
        return null;
    }
}
