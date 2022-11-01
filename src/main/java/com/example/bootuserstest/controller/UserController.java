package com.example.bootuserstest.controller;

import com.example.bootuserstest.model.User;
import com.example.bootuserstest.services.UserService;
import com.example.bootuserstest.utils.UserUtils;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/add")
    public User addNewUser(@RequestBody @Valid User user) {
        UserUtils.dateRezolve(user);
        userService.addUser(user);
        return user;
    }

    @GetMapping(value = "/get/by-phone")
    public User getUserByPhone(@RequestParam @Valid String phoneNumber) {
        return userService.getByPhone(phoneNumber);
    }

    @GetMapping(value = "/get/by-email")
    public User getUserByEmail(@RequestParam @Valid String email) {
        return userService.getByEmail(email);
    }
}
