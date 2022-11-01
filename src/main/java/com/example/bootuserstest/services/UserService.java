package com.example.bootuserstest.services;

import com.example.bootuserstest.model.User;
import java.util.Optional;

public interface UserService {
    User addUser(User user);

    Optional<User> getByPhone(String phone);

    Optional<User>  getByEmail(String email);

    boolean isUserWithPhoneNumberOrEmail(String phone, String email);
}
