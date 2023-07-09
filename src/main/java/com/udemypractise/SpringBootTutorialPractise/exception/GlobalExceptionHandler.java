package com.udemypractise.SpringBootTutorialPractise.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice //@ControllerAdvice is a specialized form of the spring's
// stereotype annotation which allows handling exceptions across the whole application in one global handling component

public class GlobalExceptionHandler /*extends ResponseEntityExceptionHandler*/ {

    @ExceptionHandler(ResourceNotFoundException.class) // this annotation is used to handle specific or custom exceptions
    public ResponseEntity<ErrorDetails> handlingCustomException(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                resourceNotFoundException.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handlingCustomemailException(EmailAlreadyExistsException emailAlreadyExistsException, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                emailAlreadyExistsException.getMessage(),
                webRequest.getDescription(false),
                "EMAIL_ALREADY_EXISTS"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class) // if the above 2 exceptions are not caught then this will be triggered
    public ResponseEntity<ErrorDetails> globalException(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL SERVER ERROR"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // this method can be used for throwing validation exceptions . inorder to use this method we need to
    //add bean validation annotaions to the entities .. eg: @NotNutll , @NotEmpty etc....
    /*@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        objectErrors.forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String messsage = error.getDefaultMessage();
            errors.put(fieldName,messsage);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }*/
}
