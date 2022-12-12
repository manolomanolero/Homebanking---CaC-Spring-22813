package com.mpautasso.homebanking.services;

import com.mpautasso.homebanking.models.dtos.AccountDetailsDto;
import com.mpautasso.homebanking.models.dtos.AccountRequestDto;

import java.util.List;

public interface AccountService {
    List<AccountDetailsDto> findAll();
    List<AccountDetailsDto> findAllByUsername(String username );
    List<AccountDetailsDto> findAllByUserId(Long userId);

    AccountDetailsDto findById(Long accountId);
    AccountDetailsDto findByCbu(Integer cbu);
    AccountDetailsDto findByNumber(Integer number);

    AccountDetailsDto create(AccountRequestDto accountRequestDto);
    AccountDetailsDto reduceBalance(AccountRequestDto accountRequestDto, Double amount);
    AccountDetailsDto incrementBalance(AccountRequestDto accountRequestDto, Double amount);

    boolean delete(Long id);

}
