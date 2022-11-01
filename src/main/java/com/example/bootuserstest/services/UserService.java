package com.example.bootuserstest.services;

import com.example.bootuserstest.model.User;

public interface UserService {
    User addUser(User user);

    User getByPhone(String phone);

    User getByEmail(String email);
}
