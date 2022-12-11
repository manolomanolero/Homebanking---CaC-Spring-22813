package com.mpautasso.homebanking.services;

import com.mpautasso.homebanking.models.dtos.UserRequestDto;
import com.mpautasso.homebanking.models.dtos.UserResponseDto;
import com.mpautasso.homebanking.models.dtos.UserUpdateRequestDto;

import java.util.List;

public interface UserService {
    UserResponseDto find(String username);
    UserResponseDto find(long id);
    List<UserResponseDto> findAll();
    UserResponseDto create(UserRequestDto userRequestDto);
    UserResponseDto update(UserUpdateRequestDto userUpdateRequestDto);
    boolean delete(long id);

}
