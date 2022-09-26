package com.example.ang.controller;

import com.example.ang.reponse.AccountResponse;
import com.example.ang.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
public class AccountResource {

    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping
    public ResponseEntity<Long> getMyBalance(Long id){
        return ResponseEntity.ok().body(accountService.checkMyBalance(id));
    }

    @PostMapping("/deposit")
    public ResponseEntity<AccountResponse> doDeposit(Long id,Long depositAmount){
        return ResponseEntity.ok().body(accountService.doDeposit(id,depositAmount));
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<AccountResponse> doWithdrawal(Long id,Long withdrawalAmount){
        return ResponseEntity.ok().body(accountService.doWithdrawal(id,withdrawalAmount));
    }
}
