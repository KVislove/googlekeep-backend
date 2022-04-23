package com.kvat.googlekeepbackend.controllers;

import com.kvat.googlekeepbackend.dtos.ApiResponseDto;
import com.kvat.googlekeepbackend.dtos.LoginDto;
import com.kvat.googlekeepbackend.dtos.UserDto;
import com.kvat.googlekeepbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/googlekeep/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto userDto1= userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        UserDto userDto=userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtoList=userService.getAllUsers();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
        UserDto userDto1=userService.updateUser(userDto,userId);
        return new ResponseEntity<>(userDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseDto> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponseDto("User deleted successfully",true),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@Valid @RequestBody LoginDto loginDto){
        UserDto userDto=userService.isValidUser(loginDto);
        Map<String,String> response=new HashMap<>();
        if(userDto!=null) {
            response.put("userId", Integer.toString(userDto.getId()));
            response.put("userName",userDto.getUsername());
            response.put("userEmail", userDto.getEmail());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
            response.put("message","User does not exist");
            return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }
}
