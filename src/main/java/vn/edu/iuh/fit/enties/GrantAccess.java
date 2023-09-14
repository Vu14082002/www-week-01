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

    @Column(name = "is_grant", nullable = false, columnDefinition = "BIT(1) DEFAULT b'1'")
    private Boolean isGrant;

    @Column(name = "note",columnDefinition = "VARCHAR(250)")
    private String note;

    public GrantAccess() {
    }

    public GrantAccess(Role role, Account account, Boolean isGrant, String note) {
        this.role = role;
        this.account = account;
        this.isGrant = isGrant;
        this.note = note;
    }
}
