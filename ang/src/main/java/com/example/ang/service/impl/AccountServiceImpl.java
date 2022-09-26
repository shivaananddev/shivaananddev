package com.example.ang.service.impl;

import com.example.ang.exception.AccountDemoNotFoundException;
import com.example.ang.exception.InsufficientFundsException;
import com.example.ang.model.Account;
import com.example.ang.model.repo.AccountRepo;
import com.example.ang.reponse.AccountResponse;
import com.example.ang.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public Long checkMyBalance(Long id) {
        Account account = this.accountRepo.findById(id).orElse(null);
        if(account == null){
            throw new AccountDemoNotFoundException("No account found for id:"+id);
        }
        return this.accountRepo.getBalance(id);
    }

    @Override
    public AccountResponse doDeposit(Long id , Long depositAmount) {
        Account account = this.accountRepo.findById(id).orElse(null);
        if(account == null){
            throw new AccountDemoNotFoundException("No account found for id:"+id);
        }
        account.setDeposit(depositAmount);
        account.setBalance(account.getBalance()+depositAmount);
        this.accountRepo.save(account);
        return convertToAccountResponse(account);
    }

    private AccountResponse convertToAccountResponse(Account account) {
        return AccountResponse.builder()
                .balance(account.getBalance())
                .deposit(account.getDeposit())
                .withdrawal(account.getWithdrawal())
                .build();
    }

    @Override
    public AccountResponse doWithdrawal(Long id,Long withdrawalAmount) {
        Long balance = this.accountRepo.getBalance(id);
        if(balance == null || balance == 0L || balance < withdrawalAmount){
            throw new InsufficientFundsException("Balance is less than withdrawal amount. Balance is:"+balance);
        }
        Account account = this.accountRepo.findById(id).orElse(null);
        if(account == null){
            throw new AccountDemoNotFoundException("No account found for id:"+id);
        }
        account.setWithdrawal(withdrawalAmount);
        account.setBalance(account.getBalance()-withdrawalAmount);
        this.accountRepo.save(account);
        return convertToAccountResponse(account);
    }
}
