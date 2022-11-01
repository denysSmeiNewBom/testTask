package com.example.bootuserstest.utils;

import java.util.Objects;

public class ExceptionUtils {
    public static boolean isAlreadyExistedCredentials(org.springframework.dao.DataIntegrityViolationException exception) {
        if (exception != null &&
                exception.getRootCause().getMessage().contains("Duplicate")){
            return true;
        }
        return false;
    }
}
