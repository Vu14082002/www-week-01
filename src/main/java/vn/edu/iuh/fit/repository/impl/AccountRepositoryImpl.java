package vn.edu.iuh.fit.repository.impl;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.edu.iuh.fit.enties.Account;
import vn.edu.iuh.fit.repository.CRUDRepository;
import vn.edu.iuh.fit.util.Connection;

import java.util.List;

public class AccountRepositoryImpl extends Generic<Account, String> {
    private EntityManager manager;

    public AccountRepositoryImpl() {
        manager = Connection.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public Boolean update(Account account) {
        try {
            manager.getTransaction().begin();
            String sql = "UPDATE `mydb`.`account`" +
                    " SET `email` = ? , " +
                    "`full_name` = ? , `password` = ? , `phone` = ? , " +
                    "`status` = ? WHERE (`account_id` = ?);\n";
            manager.createNativeQuery(sql, Account.class)
                    .setParameter(1, account.getEmail())
                    .setParameter(2, account.getFullName())
                    .setParameter(3, account.getPassword())
                    .setParameter(4, account.getPhone())
                    .setParameter(5, account.getStatusValue())
                    .setParameter(6, account.getId()).executeUpdate();

            manager.getTransaction().commit();
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
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
            return false;
        }
    }
}
