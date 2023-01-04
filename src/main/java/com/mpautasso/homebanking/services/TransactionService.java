package com.mpautasso.homebanking.services;

import com.mpautasso.homebanking.models.Account;
import com.mpautasso.homebanking.models.dtos.TransactionResponse;

import java.util.List;

public interface TransactionService {

    List<TransactionResponse> listAll();

    List<TransactionResponse> findByAccountReceiver(Account account);
    List<TransactionResponse> findByAccountSender(Account account);

    TransactionResponse deposit(Integer accountNumber, Double amount);
    TransactionResponse transfer(Integer accountNumberSender, Integer accountNumberReceiver, Double amount);
    TransactionResponse payment(Integer accountNumber, Double amount);
}
