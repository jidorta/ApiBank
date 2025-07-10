package com.ibandorta.ApiBank.controller;

import com.ibandorta.ApiBank.DTO.AccountDTO;
import com.ibandorta.ApiBank.entity.Account;
import com.ibandorta.ApiBank.entity.Customer;
import com.ibandorta.ApiBank.repository.AccountRepository;
import com.ibandorta.ApiBank.repository.CustomerRepository;
import com.ibandorta.ApiBank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @PostMapping
    public AccountDTO  createAccount(@RequestBody Account account){
        Customer customer = customerRepository.findById(account.getCustomer().getId())
                .orElseThrow(()-> new RuntimeException("Customer not found"));
        account.setCustomer(customer);
        Account savedAccount = accountRepository.save(account);

        return maptToDTO(savedAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO>getAccountById(@PathVariable Long id){
        return accountRepository.findById(id)
                .map(account -> ResponseEntity.ok(maptToDTO(account)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    private AccountDTO maptToDTO(Account account){
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setBalance(account.getBalance());
        dto.setCustomerId(account.getCustomer().getId());
        dto.setCustomerName(account.getCustomer().getName());
        dto.setCustomerEmail(account.getCustomer().getEmail());
        return dto;
    }
}
