package vn.edu.iuh.fit.enties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "grant_access")
@IdClass(GrantAccessPK.class)
public class GrantAccess {
    @Id
    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    @Id
    @ManyToOne
    @JoinColumn(name = "account_id",nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_grant", length = 1, nullable = false, columnDefinition = "ENUM('1', '0', '-1') DEFAULT '1'")
    private IsGrant isGrant;

    @Column(name = "note",columnDefinition = "VARCHAR(250)")
    private String note;

    public GrantAccess() {
    }

    public GrantAccess(Role role, Account account, IsGrant isGrant, String note) {
        this.role = role;
        this.account = account;
        this.isGrant = isGrant;
        this.note = note;
    }
}
