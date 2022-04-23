package com.kvat.googlekeepbackend.impls;

import com.kvat.googlekeepbackend.dtos.LoginDto;
import com.kvat.googlekeepbackend.dtos.UserDto;
import com.kvat.googlekeepbackend.entities.User;
import com.kvat.googlekeepbackend.exceptions.ResourceNotFoundException;
import com.kvat.googlekeepbackend.repositories.UserRepository;
import com.kvat.googlekeepbackend.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=userDtoToUser(userDto);
        User savedUser = userRepository.save(user);
        UserDto userDto1=userToUserDto(savedUser);
        return userDto1;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user does not exist with id: "+userId));
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updatedUser = userRepository.save(user);
        UserDto userDto1=userToUserDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user does not exist with id: "+userId));
        UserDto userDto=userToUserDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList=userRepository.findAll();
        List<UserDto> userDtoList=userList.stream().map(user -> userToUserDto(user)).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user does not exist with id: "+userId));
        userRepository.delete(user);
    }

    @Override
    public UserDto isValidUser(LoginDto loginDto) {
        List<User> userList=userRepository.findByUsernameAndPassword(loginDto.getUsername(),loginDto.getPassword());
        UserDto userDto=userList.size()>0?userToUserDto(userList.get(0)):null;
        return userDto;
    }

    private UserDto userToUserDto(User user){
        UserDto userDto= modelMapper.map(user,UserDto.class);
        return userDto;
    }
    private User userDtoToUser(UserDto userDto){
        User user=modelMapper.map(userDto,User.class);
        return user;
    }
}
