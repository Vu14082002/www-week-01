package vn.edu.iuh.fit.repository.impl;


import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.enties.Account;
import vn.edu.iuh.fit.enties.Role;
import vn.edu.iuh.fit.repository.CRUDRepository;
import vn.edu.iuh.fit.service.RoleService;
import vn.edu.iuh.fit.util.Connection;

import java.util.List;
import java.util.stream.Collectors;

public class AccountRepositoryImpl implements CRUDRepository<Account, String> {
    private EntityManager manager;

    public AccountRepositoryImpl() {
        Connection.getInstance().getEntityManagerFactory().getCache().evictAll();
        manager = Connection.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public Account findById(Class<Account> entityClass, String s) {
        return manager.find(Account.class, s);
    }

    @Override
    public List<Account> findAll(Class<Account> entityClass) {
        return manager.createNativeQuery("SELECT  * from account", Account.class).getResultList();
    }

    public List<Account> findAllAccountNoRole(Class<Account> entityClass) {
        List<Account> accountList = findAll(entityClass);
        RoleService roleService = new RoleService();
        List<Role> roleList = roleService.findAll();
        return accountList.stream().filter(e ->
//                e.getGrantAccesses().size()<roleList.size()
                  e.getGrantAccesses().size() == 0
        ).collect(Collectors.toList());
    }

    @Override
    public Account save(Account account) {
        try {
            manager.getTransaction().begin();
            manager.persist(account);
            manager.getTransaction().commit();
            manager.clear();
            return account;
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
            return null;
        }
    }

    public Boolean update(Account account) {
        try {
            manager.getTransaction().begin();
            manager.merge(account);
            manager.getTransaction().commit();
            manager.clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Boolean deleteById(Class<Account> entityClass, String s) {
        try {
            String sql = "UPDATE  `mydb`.`account` SET `status` = '-1' WHERE (`account_id` = ?);\n";
            manager.getTransaction().begin();
            manager.createNativeQuery(sql, Account.class).setParameter(1, s).executeUpdate();
            manager.getTransaction().commit();
            manager.clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
            return false;
        }
    }

    public Account findByEmailOrId(String username, String password) {
        try {
            String sql = "SELECT * FROM `mydb`.`account`\n" +
                    "WHERE (`account_id` = ? OR `email` = ? ) AND `password` =  ?  AND `status` = '1' ;\n";
            Account account = (Account) manager.createNativeQuery(sql, Account.class).setParameter(1, username)
                    .setParameter(2, username).setParameter(3, password).getSingleResult();
            return account;
        } catch (Exception e) {
            return null;
        }
    }
}
