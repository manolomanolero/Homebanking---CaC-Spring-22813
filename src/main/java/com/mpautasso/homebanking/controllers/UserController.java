package com.mpautasso.homebanking.controllers;

import com.mpautasso.homebanking.models.dtos.UserRequestDto;
import com.mpautasso.homebanking.models.dtos.UserResponseDto;
import com.mpautasso.homebanking.models.dtos.UserUpdateRequestDto;
import com.mpautasso.homebanking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<UserResponseDto> find(@RequestParam(required = false) Long id,
                                                @RequestParam(required = false) String username) {
        if (id != null) {
            return ResponseEntity.ok(userService.find(id));
        }
        return ResponseEntity.ok(userService.find(username));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userRequestDto));
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserUpdateRequestDto userRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userRequestDto));
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long id) {
        userService.delete(id);
        return ResponseEntity.ok("The user has been deleted");
    }
}
