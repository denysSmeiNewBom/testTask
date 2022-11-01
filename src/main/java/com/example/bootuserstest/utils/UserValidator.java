package com.example.bootuserstest.utils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

public class UserValidator {
    public static final String PHONE_PATTERN = ".+\\d{9}$";
    public static final String PHONE_CODE_PATTERN = "\\d{9}$";
    public static final String EMAIL_PATTERN = "\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";

    public static void phoneNumberValidate(String phone, HttpServletRequest request){
        if (phone != null && phone.matches(PHONE_PATTERN)){
            String operatorCode = phone.replaceFirst(PHONE_CODE_PATTERN, "");
            for (Operators e : Operators.values()) {
                if (e.getCode().equals(operatorCode)) {
                    return;
                }
            }
            request.setAttribute("customErrorCode", 400);
            throw new ValidationException("Operator with code {" + operatorCode + "} is not supported");
        }
        throw new ValidationException("Incorrect phone number. Must be {operator code without \"+\" sign}---- --- ---");
    }

    public static void emailValidate(String email, HttpServletRequest request){
        if(email != null && email.matches(EMAIL_PATTERN)){
           return;
        }
        request.setAttribute("customErrorCode", 400);
        throw new ValidationException("Incorrect email. Must be like: " + EMAIL_PATTERN);
    }
}
