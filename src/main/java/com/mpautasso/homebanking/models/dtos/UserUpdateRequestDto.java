package com.mpautasso.homebanking.models.dtos;

import lombok.Data;

@Data
public class UserUpdateRequestDto {
    private Long id;
    private String username;
    private String email;
    private int age;
    private String password;
}
