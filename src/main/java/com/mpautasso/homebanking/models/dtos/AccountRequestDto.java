package com.mpautasso.homebanking.models.dtos;

import com.mpautasso.homebanking.utils.AccountType;
import com.mpautasso.homebanking.utils.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDto {
    private Long userId;
    private Currency currency;
    private AccountType accountType;

}
