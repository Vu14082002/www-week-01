package vn.edu.iuh.fit.enties;

import com.mysql.cj.jdbc.Driver;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "account_id", columnDefinition = "VARCHAR(50)")
    private String id;

    @Column(name = "full_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String fullName;

    @Column(name = "password", columnDefinition = "VARCHAR(50)", nullable = false)
    private String password;

    @Column(name = "email", columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(name = "phone", columnDefinition = "VARCHAR(50)")
    private String phone;

    @Column(name = "status", columnDefinition = "TINYINT(4) DEFAULT 1 ", nullable = false)
    private Integer statusValue;

    @Transient
    private Status status;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<GrantAccess> grantAccesses = new HashSet<>();

    public Account() {
    }

    public Account(String id, String fullName, String password, String email, String phone, Integer statusValue) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.statusValue = statusValue;
    }

    public Account(String id, String fullName, String password, String email, String phone, Integer statusValue, Status status) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.statusValue = statusValue;
        this.status = status;
    }

    @PostLoad
    void preRead() {
        if (statusValue > -2) {
            this.status = Status.from(statusValue);
        }
    }

//    @PrePersist
//    @PreUpdate
//    void preUpdate() {
//        if (status != null) {
//            this.statusValue = status.getCode();
//        }
//    }
}
