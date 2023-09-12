package vn.edu.iuh.fit.enties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_id", columnDefinition = "VARCHAR(50)")
    private String id;
    @Column(name = "role_name", columnDefinition = "VARCHAR(50)",nullable = false)
    private String name;
    @Column(name = "description", columnDefinition = "VARCHAR(50)")
    private String description;
    @Column(name = "status", columnDefinition = "TINYINT(4)",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<GrantAccess> accesses;
    public Role() {
    }

    public Role(String id, String name, String description, Status status, Set<GrantAccess> accesses) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.accesses = accesses;
    }
}
