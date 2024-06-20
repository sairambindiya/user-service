package com.user.userservice.controller;

import com.user.userservice.entity.Address;
import com.user.userservice.entity.UserEntity;
import com.user.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity UserEntity) {
        return userService.createUser(UserEntity);
    }

    @PutMapping("/{userId}")
    public UserEntity updateUser(@PathVariable Long userId,@RequestBody UserEntity userEntity){
        return userService.updateUser(userId, Optional.ofNullable(userEntity));
    }

    @GetMapping("/{userId}")
    public Optional<UserEntity> getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }

    @PostMapping("/{userId}/address")
    public UserEntity addAddressToUser(@PathVariable Long userId, @RequestBody Address address){
        return userService.addAddressToUser(userId,address);
    }

}


