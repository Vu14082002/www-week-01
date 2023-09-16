package vn.edu.iuh.fit.repository.impl;

import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.enties.GrantAccess;
import vn.edu.iuh.fit.enties.GrantAccessPK;
import vn.edu.iuh.fit.repository.CRUDRepository;
import vn.edu.iuh.fit.util.Connection;

import java.util.List;

public class GrandAccessRepositoryImpl implements CRUDRepository<GrantAccess, GrantAccessPK> {
    private EntityManager manager;

    public GrandAccessRepositoryImpl() {
        manager = Connection.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public GrantAccess save(GrantAccess grantAccess) {
        try {
            manager.getTransaction().begin();
            manager.persist(grantAccess);
            manager.getTransaction().commit();
            manager.clear();
            return grantAccess;
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public GrantAccess findById(Class<GrantAccess> entityClass, GrantAccessPK grantAccessPK) {
        return manager.find(GrantAccess.class,grantAccessPK);
    }

    @Override
    public List<GrantAccess> findAll(Class<GrantAccess> entityClass) {
        return manager.createNativeQuery("select * from grant_access", GrantAccess.class).getResultList();
    }

    @Override
    public Boolean update(GrantAccess t) {
        try {
            manager.getTransaction().begin();
            manager.merge(t);
            manager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public Boolean deleteById(Class<GrantAccess> entityClass, GrantAccessPK grantAccessPK) {
        try {
            String sql ="UPDATE `mydb`.`grant_access` SET `is_grant` = '0' WHERE (`account_id` = ?) and (`role_id` = ?);";
            manager.getTransaction().begin();
            manager.createNativeQuery(sql).setParameter(1,grantAccessPK.getAccount()).setParameter(2,grantAccessPK.getRole()).executeUpdate();
            manager.getTransaction().commit();
            manager.clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
            return false;
        }
    }
}
