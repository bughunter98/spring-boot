package com.udemypractise.SpringBootTutorialPractise.controller;

import com.udemypractise.SpringBootTutorialPractise.DTO.StudentDto;
import com.udemypractise.SpringBootTutorialPractise.model.StudentEntity;
import com.udemypractise.SpringBootTutorialPractise.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CRUD REST APIs for User Resource",
description = "CRUD REST APIs -Create User , Update User , Get User , Get All Users , Delete User")
@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Create Student REST API",
    description = "This API is used to create/add the Student details to DB")
    @ApiResponse(responseCode = "201",
    description = "HTTP Status 201 Created")
    @PostMapping("createStudent")
    public StudentDto createStudent(@RequestBody StudentDto student) throws Exception {
        return userService.createUser(student);
    }
    @Operation(summary = "Create Student REST API with Response Entity Added",
            description = "This API is used to create/add the Student details to DB")
    @ApiResponse(responseCode = "201",
            description = "HTTP Status 201 Created")
    @PostMapping("createStudent1")
    public ResponseEntity<StudentDto> createStudentwithResponseEntity(@RequestBody StudentDto student) throws Exception {
        StudentDto user = userService.createUser(student);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
    @PostMapping("createStudent2")
    @Operation(summary = "Create Student REST API with Response Entity and Headers Added",
            description = "This API is used to create/add the Student details to DB")
    @ApiResponse(responseCode = "201",
            description = "HTTP Status 201 Created")
    public ResponseEntity<StudentEntity> createStudentwithRequestandResponseEntity(RequestEntity<StudentEntity> student) throws Exception {
        StudentEntity user = userService.createUserwithRequestEntity(student);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @GetMapping("getStudent/{id}")
    @Operation(summary = "Get Student REST API",
            description = "This API is used to get the Student details from DB")
    @ApiResponse(responseCode = "400",
            description = "HTTP Status 400 Found")
    public ResponseEntity<StudentDto> getStudentbyId(@PathVariable("id") int studentId) throws Exception {
        StudentDto studentDetails = userService.getStudentbyId(studentId);
        return new ResponseEntity<>(studentDetails,HttpStatus.FOUND);
    }
    @Operation(summary = "Get Student REST API",
            description = "This API is used to get all the Student details from DB")
    @ApiResponse(responseCode = "400",
            description = "HTTP Status 400 Found")
    @GetMapping("getAllStudents")
    public ResponseEntity<List<StudentDto>> getAllStudents() throws Exception {
        List<StudentDto>studentDtos = userService.getAllStudents();
        return new ResponseEntity<>(studentDtos,HttpStatus.FOUND);
    }

    @PutMapping("updateStudent")
    //Put mapping can be used if and only if all the fields in the pojo are available in request
    //generally for put mapping we need to add all the fields of pojo in json.
    //here just for example , i tried to implement put mapping with any one field. so this would act as patch mapping as well.
    @Operation(summary = "Update Student REST API",
            description = "This API is used to modify the Student details from DB")
    @ApiResponse(responseCode = "200",
            description = "HTTP Status 200 Created")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto student) throws Exception {
        StudentDto studentDto = userService.updateStudents(student);
        return new ResponseEntity<>(studentDto,HttpStatus.OK);
    }

    @DeleteMapping("deleteStudent/{id}")
    @Operation(summary = "Delete Student REST API",
            description = "This API is used to Delete the Student details from DB")
    @ApiResponse(responseCode = "200",
            description = "HTTP Status 200 Found")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentid) throws Exception{
        userService.deleteStudentbyId(studentid);
        return new ResponseEntity<>("Student Data Deleted",HttpStatus.OK);
    }

}
