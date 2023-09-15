package vn.edu.iuh.fit.enties;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "log")
//@SequenceGenerator(name="employee_id_seq", initialValue=2, allocationSize=1)
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT(20)")
    private Long id;


    @Column(name = "account_id", columnDefinition = "VARCHAR(50)", nullable = false)
    private String accountId;

    @Column(name = "login_time", columnDefinition = "datetime DEFAULT current_timestap()", nullable = false)
    private LocalDateTime loginTime;

    @Column(name = "logout_time", columnDefinition = "datetime DEFAULT current_timestamp()", nullable = false)
    private LocalDateTime logoutTime;

    @Column(name = "note", columnDefinition = "VARCHAR(255) DEFAULT ''", nullable = false)
    private String note;

    public Log() {
    }

    public Log(String accountId, String note) {
        this.accountId = accountId;
        this.note = note;
    }

    public Log(String accountId, LocalDateTime loginTime, LocalDateTime logoutTime, String note) {
        this.accountId = accountId;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.note = note;
    }

    public Log(Long id, String accountId, LocalDateTime loginTime, LocalDateTime logoutTime, String note) {
        this.id = id;
        this.accountId = accountId;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.note = note;
    }

    @PrePersist
    public void setPreSave(){
        this.loginTime= LocalDateTime.now();
        this.logoutTime=LocalDateTime.now();
    }
    @PreUpdate
    public void setPreUpdate(){
        this.logoutTime=LocalDateTime.now();
    }
}