package com.mpautasso.homebanking.services.impl;

import com.mpautasso.homebanking.exceptions.custom.EmptyElementException;
import com.mpautasso.homebanking.exceptions.custom.EntityNotFoundException;
import com.mpautasso.homebanking.mappers.UserMapper;
import com.mpautasso.homebanking.models.User;
import com.mpautasso.homebanking.models.dtos.UserRequestDto;
import com.mpautasso.homebanking.models.dtos.UserResponseDto;
import com.mpautasso.homebanking.models.dtos.UserUpdateRequestDto;
import com.mpautasso.homebanking.repositories.UserRepository;
import com.mpautasso.homebanking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponseDto find(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new EntityNotFoundException("Not found user by username");
        }
        return userMapper.entityToResponseDto(user.get());
    }

    @Override
    public UserResponseDto find(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new EntityNotFoundException("Not found user by id");
        }
        return userMapper.entityToResponseDto(user.get());
    }

    @Override
    public List<UserResponseDto> findAll() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .map(userMapper::entityToResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        //TODO check if the username or email already exist and throw exception
        User user = userMapper.requestDtoToEntity(userRequestDto);
        if(validUser(user)){
            return userMapper
                    .entityToResponseDto(userRepository.save(user));
        }
        return null;
    }

    @Override
    public UserResponseDto update(UserUpdateRequestDto userRequestDto) {
        User user = userMapper.requestUpdateDtoToEntity(userRequestDto);
        if(validUser(user)){
            Optional<User> userBD = userRepository.findById(userRequestDto.getId());
            if(userBD.isPresent()) {
                user.setId(userBD.get().getId());
                return userMapper
                        .entityToResponseDto(userRepository.save(user));
            }
            throw new EntityNotFoundException("The user to be updated could not be found.");
        }
        return null;
    }

    @Override
    public boolean delete(long id) {
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundException("ID does not belong to a user");
        }
        userRepository.deleteById(id);
        return userRepository.existsById(id);
    }


    private boolean validUser(User user){
        if(user.getEmail().isBlank()){
            throw new EmptyElementException("Email is empty");
        }
        if (user.getUsername().isBlank()){
            throw new EmptyElementException("Username is empty");
        }
        if (user.getPassword().isBlank()){
            throw new EmptyElementException("Password is empty");
        }
        if (user.getAge() < 18){
            throw new EmptyElementException("User must be 18 years or older");
        }
        return Boolean.TRUE;
    }
}
