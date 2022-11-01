package com.example.bootuserstest.services;

import com.example.bootuserstest.exception.DataProcessingException;
import com.example.bootuserstest.model.User;
import com.example.bootuserstest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
       return userRepository.save(user);
    }

    @Override
    public User getByPhone(String phone) {
        return userRepository.findStudentByPhoneNumber(phone).orElseThrow(
                ()->new DataProcessingException("Can't get user by phone number " + phone));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findStudentByEmail(email).orElseThrow(
                ()->new DataProcessingException("Can't get user by email " + email));
    }
}
