package com.mpautasso.homebanking.models.dtos;

import com.mpautasso.homebanking.utils.AccountType;
import com.mpautasso.homebanking.utils.Currency;

public class AccountRequestDto {
    private Long userId;
    private Currency currency;
    private AccountType accountType;

}
