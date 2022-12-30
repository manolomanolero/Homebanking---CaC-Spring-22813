package com.mpautasso.homebanking.services.impl;

import com.mpautasso.homebanking.exceptions.custom.EntityNotFoundException;
import com.mpautasso.homebanking.exceptions.custom.InvalidElementException;
import com.mpautasso.homebanking.mappers.AccountMapper;
import com.mpautasso.homebanking.mappers.UserMapper;
import com.mpautasso.homebanking.models.Account;
import com.mpautasso.homebanking.models.User;
import com.mpautasso.homebanking.models.dtos.AccountDetailsDto;
import com.mpautasso.homebanking.models.dtos.AccountRequestDto;
import com.mpautasso.homebanking.repositories.AccountRepository;
import com.mpautasso.homebanking.services.AccountService;
import com.mpautasso.homebanking.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public List<AccountDetailsDto> findAll() {
        return StreamSupport
                .stream(accountRepository.findAll().spliterator(), false)
                .map(accountMapper::entityToResponseDto)
                .toList();
    }

    @Override
    public List<AccountDetailsDto> findAllByUsername(String username) {
        User user = userMapper.responseDtoToEntity(userService.find(username));
        return accountRepository.findAllByUser(user).stream()
                .map(accountMapper::entityToResponseDto)
                .toList();
    }

    @Override
    public List<AccountDetailsDto> findAllByUserId(Long userId) {
        User user = userMapper.responseDtoToEntity(userService.find(userId));
        return accountRepository.findAllByUser(user).stream()
                .map(accountMapper::entityToResponseDto)
                .toList();
    }

    @Override
    public AccountDetailsDto findById(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) {
            throw new EntityNotFoundException("Not found account by id");
        }
        return accountMapper.entityToResponseDto(account.get());
    }

    @Override
    public AccountDetailsDto findByCbu(Integer cbu) {
        Optional<Account> account = accountRepository.findByCbu(cbu);
        if (account.isEmpty()) {
            throw new EntityNotFoundException("Not found account by cbu");
        }
        return accountMapper.entityToResponseDto(account.get());
    }

    @Override
    public AccountDetailsDto findByNumber(Integer number) {
        Optional<Account> account = accountRepository.findByNumber(number);
        if (account.isEmpty()) {
            throw new EntityNotFoundException("Not found account by account number");
        }
        return accountMapper.entityToResponseDto(account.get());
    }

    @Override
    public AccountDetailsDto create(AccountRequestDto accountRequestDto) {
        Account account = accountMapper.requestDtoToEntity(accountRequestDto);
        User user = userMapper.responseDtoToEntity(userService.find(accountRequestDto.getUserId()));

        if(existAccount(user, accountRequestDto)){
            throw new InvalidElementException("The user already have an account for this currency and account type");
        }

        account.setUser(user);
        //Can be implmented in better ways
        account.setCbu(Math.toIntExact(Math.round(Math.random() * 10000000)));
        account.setNumber(Math.toIntExact(Math.round(Math.random() * 10000000)));
        account.setBalance(0d);
        return accountMapper.entityToResponseDto(accountRepository.save(account));
    }

    @Override
    public AccountDetailsDto reduceBalance(int accountNumber, Double amount) {
        Optional<Account> account = accountRepository.findByNumber(accountNumber);
        if (account.isEmpty()) {
            throw new EntityNotFoundException("Not found account by account number");
        }
        if (account.get().getBalance() < amount) {
            throw new InvalidElementException("The account does not have the necessary amount");
        }
        Account accountUpdated = account.get();
        accountUpdated.setBalance(accountUpdated.getBalance() - amount);
        return accountMapper.entityToResponseDto(accountRepository.save(accountUpdated));
    }

    @Override
    public AccountDetailsDto incrementBalance(int accountNumber, Double amount) {
        Optional<Account> account = accountRepository.findByNumber(accountNumber);
        if (account.isEmpty()) {
            throw new EntityNotFoundException("Not found account by account number");
        }
        Account accountUpdated = account.get();
        accountUpdated.setBalance(accountUpdated.getBalance() + amount);
        return accountMapper.entityToResponseDto(accountRepository.save(accountUpdated));
    }

    @Override
    public boolean delete(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new EntityNotFoundException("ID does not belong to an account");
        }
        accountRepository.deleteById(id);
        return accountRepository.existsById(id);
    }

    private boolean existAccount(User user, AccountRequestDto account){
        List<Account> userAccounts = accountRepository.findAllByUser(user);
        return userAccounts.stream()
                .anyMatch(accountBD ->
                        accountBD.getAccountType().equals(account.getAccountType()) &&
                                accountBD.getCurrency().equals(account.getCurrency()));
    }
}
