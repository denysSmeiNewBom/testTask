package com.example.bootuserstest.utils;

import com.example.bootuserstest.exception.CredentialAreAlreadyInUseException;
import com.example.bootuserstest.exception.NoSuchOperatorException;
import com.example.bootuserstest.model.User;
import com.example.bootuserstest.repository.UserRepository;
import com.example.bootuserstest.services.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class UserValidator {
    public static final String PHONE_PATTERN = ".+\\d{9}$";
    public static final String PHONE_CODE_PATTERN = "\\d{9}$";
    public static final String EMAIL_PATTERN = "^(.+)@(\\S+)$";

    public static void validate(User user, UserService userService) {
        phoneNumberValidate(user.getPhoneNumber());
        emailValidate(user.getEmail());
        uniqueUserCredentials(user, userService);
    }

    public static void uniqueUserCredentials(User user, UserService userService) {
        if (userService.isUserWithPhoneNumberOrEmail(user.getPhoneNumber(), user.getEmail())) {
            throw new CredentialAreAlreadyInUseException("Email or phone number are already in use");
        }
    }

    public static void phoneNumberValidate(String phone) {
        if (phone != null && phone.matches(PHONE_PATTERN)) {
            String operatorCode = phone.replaceFirst(PHONE_CODE_PATTERN, "");
            for (Operators e : Operators.values()) {
                if (e.getCode().equals(operatorCode)) {
                    return;
                }
            }
            throw new NoSuchOperatorException("Operator with code {" + operatorCode + "} is not supported");
        }
        throw new ValidationException("Incorrect phone number. Must be {operator code without \"+\" sign}---- --- ---");
    }

    public static void emailValidate(String email) {
        if (email != null && email.matches(EMAIL_PATTERN)) {
            return;
        }
        throw new ValidationException("Incorrect email. Must be like: " + EMAIL_PATTERN);
    }
}
