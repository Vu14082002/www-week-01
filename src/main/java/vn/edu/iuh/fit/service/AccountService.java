package vn.edu.iuh.fit.service;

import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;
import vn.edu.iuh.fit.enties.Account;
import vn.edu.iuh.fit.repository.CRUDRepository;
import vn.edu.iuh.fit.repository.impl.AccountRepositoryImpl;

import java.util.List;

public class AccountService {

    private AccountRepositoryImpl accountRepository;

    public AccountService() {
        this.accountRepository = new AccountRepositoryImpl();
    }
    public List<Account> findAll(){
        return accountRepository.findAll(Account.class);
    }
    public Account findById(String id){
         return accountRepository.findById(Account.class,id);
    }
    public boolean update(Account account){
        return accountRepository.update(account);
    }
    public Account save(Account account){
        return accountRepository.save(account);
    }
    public void deleteById(String id){
        accountRepository.deleteById(Account.class,id);
    }
}
