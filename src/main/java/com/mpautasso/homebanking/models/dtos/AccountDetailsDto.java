package com.mpautasso.homebanking.models.dtos;

import com.mpautasso.homebanking.utils.AccountType;
import com.mpautasso.homebanking.utils.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsDto {
    private Long id;
    private UserResponseDto user;
    private Currency currency;
    private AccountType accountType;
    private Integer number;
    private Integer cbu;
    private Double balance;
}
