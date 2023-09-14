package vn.edu.iuh.fit.repository.impl;

import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.enties.Role;
import vn.edu.iuh.fit.repository.CRUDRepository;
import vn.edu.iuh.fit.util.Connection;

import java.util.List;

public class RoleRepositoryImpl implements CRUDRepository<Role, String> {
    private EntityManager manager;

    public RoleRepositoryImpl() {
        manager = Connection.getInstance().getEntityManagerFactory().createEntityManager();
    }
    @Override
    public Role save(Role role) {
        try{
            manager.getTransaction().begin();
            manager.persist(role);
            manager.getTransaction().commit();
            return role;
        }catch (Exception e){
            e.printStackTrace();
            manager.getTransaction().rollback();
            return null;
        }
    }
    public Role findById(Class<Role> entityClass, String s) {
        return manager.find(entityClass, s);
    }
    public List<Role> findAll(Class<Role> entityClass) {
       return  manager.createNativeQuery("SELECT * from role", Role.class).getResultList();
    }
    @Override
    public Boolean update(Role role) {
        try{
            manager.getTransaction().begin();
            manager.merge(role);
            manager.getTransaction().commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            manager.getTransaction().rollback();
            return false;
        }
    }
    @Override
    public Boolean deleteById(Class<Role> entityClass, String s) {
        return null;
    }
}
