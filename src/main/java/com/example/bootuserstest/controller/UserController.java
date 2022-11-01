package com.example.bootuserstest.controller;

import com.example.bootuserstest.exception.CredentialAreAlreadyInUseException;
import com.example.bootuserstest.exception.DataProcessingException;
import com.example.bootuserstest.model.User;
import com.example.bootuserstest.services.UserService;
import com.example.bootuserstest.utils.UserUtils;
import com.example.bootuserstest.utils.UserValidator;
import javax.servlet.http.HttpServletRequest;
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
    public User addNewUser(@RequestBody @Valid User user, HttpServletRequest request) {
        UserUtils.dateRezolve(user);
        userService.addUser(user);
        return user;
    }

    @GetMapping(value = "/get/by-phone")
    public User getUserByPhone(@RequestParam @Valid String phoneNumber, HttpServletRequest request) {
        UserValidator.phoneNumberValidate(phoneNumber, request);
        return userService.getByPhone(phoneNumber).orElseThrow(
                () ->{
                    request.setAttribute("customErrorCode", 1003);
                    throw new DataProcessingException("There is no user with phone number: " + phoneNumber);
                }
        );
    }

    @GetMapping(value = "/get/by-email")
    public User getUserByEmail(@RequestParam @Valid String email, HttpServletRequest request) {
        UserValidator.emailValidate(email, request);
        return userService.getByEmail(email).orElseThrow(
                () ->{
                    request.setAttribute("customErrorCode", 1003);
                    throw new DataProcessingException("There is no user with email: " + email);
                }
        );
    }
}
