package com.mpautasso.homebanking.controllers;

import com.mpautasso.homebanking.models.dtos.*;
import com.mpautasso.homebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDetailsDto>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<AccountDetailsDto> find(@RequestParam(required = false) Long id,
                                                    @RequestParam(required = false) Integer cbu,
                                                    @RequestParam(required = false) Integer number ) {
        if (id != null) {
            return ResponseEntity.ok(accountService.findById(id));
        } else if (cbu != null){
            return ResponseEntity.ok(accountService.findByCbu(cbu));
        }
        return ResponseEntity.ok(accountService.findByNumber(number));
    }

    @PostMapping
    public ResponseEntity<AccountDetailsDto> createUser(@RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(accountRequestDto));
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long id) {
        accountService.delete(id);
        return ResponseEntity.ok("The account has been deleted");
    }
}
