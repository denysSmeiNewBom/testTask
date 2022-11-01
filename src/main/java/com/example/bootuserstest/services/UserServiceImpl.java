package com.example.bootuserstest.services;

import com.example.bootuserstest.exception.CredentialAreAlreadyInUseException;
import com.example.bootuserstest.exception.DataProcessingException;
import com.example.bootuserstest.model.User;
import com.example.bootuserstest.repository.UserRepository;
import com.example.bootuserstest.utils.ExceptionUtils;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        try {
           userRepository.save(user);
        }catch (org.springframework.dao.DataIntegrityViolationException exception){
            if (ExceptionUtils.isAlreadyExistedCredentials(exception)){
                throw new CredentialAreAlreadyInUseException(exception.getRootCause().getMessage());
            }
        }
        return user;
    }

    @Override
    public Optional<User> getByPhone(String phone) {
        return userRepository.findStudentByPhoneNumber(phone);
    }

    @Override
    public Optional<User>  getByEmail(String email) {
        return userRepository.findStudentByEmail(email);
    }
}
