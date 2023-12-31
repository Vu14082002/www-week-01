package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.enties.Account;
import vn.edu.iuh.fit.enties.GrantAccess;
import vn.edu.iuh.fit.enties.GrantAccessPK;
import vn.edu.iuh.fit.repository.impl.GrandAccessRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class GrantAccessService {
    private GrandAccessRepositoryImpl grandAccessRepository;


    public GrantAccessService() {
        grandAccessRepository = new GrandAccessRepositoryImpl();
    }

    public GrantAccess findById(String account ,String role){
        return grandAccessRepository.findById(account,role);
    }
    public List<GrantAccess> findAll( ){
        return grandAccessRepository.findAll(GrantAccess.class);
    }
    public GrantAccess save(GrantAccess grantAccess) {
        GrantAccess savedGrantAccess = grandAccessRepository.save(grantAccess);
        return savedGrantAccess;
    }

    public boolean update(GrantAccess grantAccess) {
        return grandAccessRepository.update(grantAccess);
    }
    public boolean delete (String accountId, String roleId){
       return grandAccessRepository.deleteById(accountId, roleId);
    }


}
