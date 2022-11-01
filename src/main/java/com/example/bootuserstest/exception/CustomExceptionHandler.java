package com.example.bootuserstest.exception;

import javax.validation.ValidationException;
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
        return new ResponseEntity(parseToJson(ex,statusCode), statusCode);
    }

    @ExceptionHandler(javax.validation.ValidationException.class)
    public final ResponseEntity<Object> handleAllExceptions(javax.validation.ValidationException ex) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(parseToJson(ex,statusCode), statusCode);
    }

    @ExceptionHandler(DataProcessingException.class)
    public final ResponseEntity<Object> handleAllExceptions(DataProcessingException ex, WebRequest request) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(parseToJson(ex,statusCode), statusCode);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(parseToJson(ex,statusCode), statusCode);
    }

    private static String parseToJson(Exception exception, HttpStatus status){
        return String.format(
                "{\n" +
                        "\t\"statusCode\": \"%s\",\n" +
                        "\t\"exceptionMessage\": \"%s\"\n" +
                        "}",
                status, exception == null ? "N/A" : exception.getLocalizedMessage());
    }
}