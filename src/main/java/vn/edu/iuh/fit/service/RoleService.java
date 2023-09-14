package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.enties.Account;
import vn.edu.iuh.fit.enties.Role;
import vn.edu.iuh.fit.repository.impl.AccountRepositoryImpl;
import vn.edu.iuh.fit.repository.impl.RoleRepositoryImpl;

import java.util.List;

public class RoleService {

    private RoleRepositoryImpl roleRepository;

    public RoleService() {
        this.roleRepository = new RoleRepositoryImpl();
    }
    public List<Role> findAll(){
        return roleRepository.findAll(Role.class);
    }
    public Role findById(String id){
         return roleRepository.findById(Role.class,id);
    }
    public boolean update(Role role){
        return roleRepository.update(role);
    }
    public Role save(Role role){
        return roleRepository.save(role);
    }
    public void deleteById(String id){
        roleRepository.deleteById(Role.class,id);
    }
}
