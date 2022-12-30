package com.mpautasso.homebanking.mappers;

import com.mpautasso.homebanking.models.User;
import com.mpautasso.homebanking.models.dtos.UserRequestDto;
import com.mpautasso.homebanking.models.dtos.UserResponseDto;
import com.mpautasso.homebanking.models.dtos.UserUpdateRequestDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;

    public UserResponseDto entityToResponseDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }

    public User responseDtoToEntity(UserResponseDto userResponseDto) {
        return modelMapper.map(userResponseDto, User.class);
    }

    public User requestDtoToEntity(UserRequestDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public User requestUpdateDtoToEntity(UserUpdateRequestDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
