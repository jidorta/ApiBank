package com.ibandorta.ApiBank.service;

import com.ibandorta.ApiBank.entity.Account;
import com.ibandorta.ApiBank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    public Optional<Account> getAccountById(Long id){
        return accountRepository.findById(id);
    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }

}
