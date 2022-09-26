package com.example.ang.service;

import com.example.ang.reponse.AccountResponse;

public interface AccountService {

    Long checkMyBalance(Long id);

    AccountResponse doDeposit(Long id ,Long depositAmount);

    AccountResponse doWithdrawal(Long id ,Long withdrawalAmount);
}
