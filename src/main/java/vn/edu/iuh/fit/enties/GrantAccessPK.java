package vn.edu.iuh.fit.enties;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
public class GrantAccessPK  implements Serializable {
    private String role;
    private String account;

    public GrantAccessPK() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrantAccessPK that = (GrantAccessPK) o;
        return Objects.equals(role, that.role) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, account);
    }
}
