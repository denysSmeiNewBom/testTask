package com.example.bootuserstest.services;

import com.example.bootuserstest.exception.CredentialAreAlreadyInUseException;
import com.example.bootuserstest.model.User;
import com.example.bootuserstest.repository.UserRepository;
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

    @Override
    public void validateUserWithPhoneNumberOrEmail(String phone, String email) {
        Optional<User> userOptional = userRepository.findByPhoneNumberOrEmail(phone, email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (userOptional.get().getEmail().equals(email)) {
                throw new CredentialAreAlreadyInUseException("Email is already in use");
            }
            if (userOptional.get().getPhoneNumber().equals(phone)) {
                throw new CredentialAreAlreadyInUseException("Phone number is already in use");
            }
        }
    }
}
