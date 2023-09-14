package vn.edu.iuh.fit.repository.impl;

import vn.edu.iuh.fit.enties.GrantAccess;
import vn.edu.iuh.fit.enties.GrantAccessPK;
import vn.edu.iuh.fit.repository.CRUDRepository;

import java.util.List;

public class GrandAccessRepositoryImpl implements CRUDRepository<GrantAccess, GrantAccessPK> {
    @Override
    public GrantAccess save(GrantAccess grantAccess) {
        return null;
    }

    @Override
    public GrantAccess findById(Class<GrantAccess> entityClass, GrantAccessPK grantAccessPK) {
        return null;
    }

    @Override
    public List<GrantAccess> findAll(Class<GrantAccess> entityClass) {
        return null;
    }

    @Override
    public Boolean update(GrantAccess T) {
        return null;
    }

    @Override
    public Boolean deleteById(Class<GrantAccess> entityClass, GrantAccessPK grantAccessPK) {
        return null;
    }
}
