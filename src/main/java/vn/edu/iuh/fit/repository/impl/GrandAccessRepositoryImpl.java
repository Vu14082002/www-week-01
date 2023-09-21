package vn.edu.iuh.fit.repository.impl;

import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.enties.Account;
import vn.edu.iuh.fit.enties.GrantAccess;
import vn.edu.iuh.fit.enties.GrantAccessPK;
import vn.edu.iuh.fit.enties.Role;
import vn.edu.iuh.fit.repository.CRUDRepository;
import vn.edu.iuh.fit.service.AccountService;
import vn.edu.iuh.fit.service.RoleService;
import vn.edu.iuh.fit.util.Connection;

import java.util.List;

public class GrandAccessRepositoryImpl implements CRUDRepository<GrantAccess, GrantAccessPK> {
    private EntityManager manager;

    public GrandAccessRepositoryImpl() {
        manager = Connection.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public GrantAccess save(GrantAccess grantAccess) {
        String sql ="INSERT INTO `mydb`.`grant_access` (`account_id`, `role_id`, `is_grant`, `note`) VALUES ( ?, ?, '1', ?);";
        try {
            manager.getTransaction().begin();
            int update = manager.createNativeQuery(sql,GrantAccess.class).setParameter(1, grantAccess.getAccount().getId())
                    .setParameter(2, grantAccess.getRole().getId())
                    .setParameter(3, grantAccess.getNote()).executeUpdate();
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
        return null;
    }

        public GrantAccess findById(String account ,String role){
            String sql ="select * from grant_access where account_id = ? and role_id= ?";
            Object[] object = (Object[] )manager.createNativeQuery(sql).setParameter(1, account).setParameter(2, role).getSingleResult();
            for(int index=0;index<object.length;index++){
//                get data
                String accountId= (String) object[0];
                String roleId= (String) object[1];
//                Boolean check= (Boolean) object[2] ;
                Boolean check = object[2].toString().equals("0")?Boolean.FALSE:Boolean.TRUE;
                String note = (String) object[3];
//                find data
                RoleService roleService = new RoleService();
                AccountService accountService = new AccountService();
                Account accountServiceById = accountService.findById(accountId);
                Role roleServiceById = roleService.findById(roleId);
                GrantAccess grantAccess = new GrantAccess(roleServiceById,accountServiceById,check,note);
                return grantAccess;
            }
        return new GrantAccess();
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
            manager.clear();
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean deleteById(Class<GrantAccess> entityClass, GrantAccessPK grantAccessPK) {
        return null;
    }

    public Boolean deleteById(String accountId, String roleId) {
        try {
            String sql ="UPDATE `mydb`.`grant_access` SET `is_grant` = '0' WHERE (`account_id` = ?) and (`role_id` = ?);";
            manager.getTransaction().begin();
            int update = manager.createNativeQuery(sql).setParameter(1, accountId).setParameter(2, roleId).executeUpdate();
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
