package com.example.bootuserstest.handler;

import com.example.bootuserstest.exception.CredentialAreAlreadyInUseException;
import com.example.bootuserstest.exception.DataProcessingException;
import com.example.bootuserstest.exception.NoSuchFieldInTableException;
import com.example.bootuserstest.exception.NoSuchOperatorException;
import com.example.bootuserstest.utils.ExceptionUtils;
import com.example.bootuserstest.utils.UserCred;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity(parseToJson(ex,statusCode.value()), statusCode);
    }

    @ExceptionHandler(javax.validation.ValidationException.class)
    public final ResponseEntity<Object> handleAllExceptions(javax.validation.ValidationException ex) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(parseToJson(ex,statusCode.value()), statusCode);
    }

    @ExceptionHandler(DataProcessingException.class)
    public final ResponseEntity<Object> handleAllExceptions(DataProcessingException ex, WebRequest request) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        Integer customErrorCode = (Integer) request.getAttribute("customErrorCode", 0);
        return new ResponseEntity(parseToJson(ex,customErrorCode), statusCode);
    }

    @ExceptionHandler(NoSuchOperatorException.class)
    public final ResponseEntity<Object> handleAllExceptions(NoSuchOperatorException ex, WebRequest request) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(parseToJson(ex,1002), statusCode);
    }

    @ExceptionHandler(NoSuchFieldInTableException.class)
    public final ResponseEntity<Object> handleAllExceptions(NoSuchFieldInTableException ex, WebRequest request) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(parseToJson(ex,1004), statusCode);
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleAllExceptions(org.springframework.dao.DataIntegrityViolationException ex, WebRequest request) {
        Exception exception = ExceptionUtils.getExceptionForAlreadyUsedCredentials(ex);
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(parseToJson(exception,1007), statusCode);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        Exception exception = new Exception("Incorrect value of field" + ex.getFieldError().getField());
        return new ResponseEntity(parseToJson(exception,1001), statusCode);
    }

    private static String parseToJson(Exception exception, int status){
        return String.format(
                "{\n" +
                        "\t\"statusCode\": \"%s\",\n" +
                        "\t\"exceptionMessage\": \"%s\"\n" +
                        "}",
                status, exception == null ? "N/A" : exception.getMessage());
    }
}