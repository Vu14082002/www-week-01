package vn.edu.iuh.fit.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class Connection {
    private static Connection instance;
    private EntityManagerFactory entityManagerFactory;

    public Connection() {
        entityManagerFactory = Persistence.createEntityManagerFactory("week01_lab_NguyenVanVu_20002865");
    }

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }


}
