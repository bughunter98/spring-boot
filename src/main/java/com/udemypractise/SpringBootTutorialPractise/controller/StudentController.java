package com.udemypractise.SpringBootTutorialPractise.controller;

import com.udemypractise.SpringBootTutorialPractise.model.StudentEntity;
import com.udemypractise.SpringBootTutorialPractise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    private UserService userService;

    @PostMapping("createStudent")
    public StudentEntity createStudent(@RequestBody StudentEntity student) throws Exception {
        return userService.createUser(student);
    }
    @PostMapping("createStudent1")
    public ResponseEntity<StudentEntity> createStudentwithRequestEntity(@RequestBody StudentEntity student) throws Exception {
        StudentEntity user = userService.createUser(student);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
}
