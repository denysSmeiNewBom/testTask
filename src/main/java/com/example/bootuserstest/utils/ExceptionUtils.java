package com.example.bootuserstest.utils;
public class ExceptionUtils {
    public static boolean isAlreadyExistedCredentials(org.springframework.dao.DataIntegrityViolationException exception) {
        if (exception != null &&
                exception.getRootCause().getMessage().contains("Duplicate")){
            return true;
        }
        return false;
    }

    public static Exception getExceptionForAlreadyUsedCredentials(org.springframework.dao.DataIntegrityViolationException ex){
        if(isAlreadyExistedCredentials(ex)){
            String exceptionMessage = ex.getRootCause().getMessage();
            String field = "'" + exceptionMessage.substring(exceptionMessage.indexOf("users.") + 6);
            return new Exception("There is already a user with such credentials for field: " +
                    UserCred.getUserByField(field).getValueInJson());
        }
        return new Exception("Unknown exception");
    }
}
