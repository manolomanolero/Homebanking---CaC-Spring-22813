package com.mpautasso.homebanking.models.dtos;

import com.mpautasso.homebanking.utils.TransactionType;

import java.util.Date;

public class TransactionResponse {
    private Long id;
    private int accountNumberSender;
    private int accountNumberReceiver;
    private TransactionType transactionType;
    private Double amount;
    private Date transactionDate;
}
