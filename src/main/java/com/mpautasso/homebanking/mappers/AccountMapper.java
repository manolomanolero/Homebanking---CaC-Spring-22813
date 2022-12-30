package com.mpautasso.homebanking.mappers;

import com.mpautasso.homebanking.models.Account;
import com.mpautasso.homebanking.models.dtos.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    @Autowired
    private ModelMapper modelMapper;

    public AccountDetailsDto entityToResponseDto(Account account) {
        return modelMapper.map(account, AccountDetailsDto.class);
    }

    public Account requestDtoToEntity(AccountRequestDto accountRequestDto) {
        return modelMapper.map(accountRequestDto, Account.class);
    }
}
