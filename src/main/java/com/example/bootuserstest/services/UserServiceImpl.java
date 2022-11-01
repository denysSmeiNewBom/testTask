package com.example.bootuserstest.services;

import com.example.bootuserstest.exception.CredentialAreAlreadyInUseException;
import com.example.bootuserstest.model.User;
import com.example.bootuserstest.repository.UserRepository;
import com.example.bootuserstest.utils.ExceptionUtils;
import com.example.bootuserstest.utils.UserCred;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getByPhone(String phone) {
        return userRepository.findStudentByPhoneNumber(phone);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findStudentByEmail(email);
    }
}
