package com.udemypractise.SpringBootTutorialPractise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//**********************************************************************************************************************

//inorder to create a specific exception format we need to create globalException Handler

//**********************************************************************************************************************
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Integer fieldValue;

    public ResourceNotFoundException(String resourceName,String fieldName,Integer fieldValue){
        super(String.format("%s not found with %s : '%s' ",resourceName,fieldName,fieldValue));
        this.fieldName = fieldName;
        this.fieldValue=fieldValue;
        this.resourceName = resourceName; // currently assigning the values to the global fields is of no use.

    }
    public ResourceNotFoundException(String resourceName){
        super(String.format("No %s found",resourceName));
    }

}
