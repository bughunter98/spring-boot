package com.udemypractise.SpringBootTutorialPractise.controller;

import com.udemypractise.SpringBootTutorialPractise.model.StudentEntity;
import com.udemypractise.SpringBootTutorialPractise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<StudentEntity> createStudentwithResponseEntity(@RequestBody StudentEntity student) throws Exception {
        StudentEntity user = userService.createUser(student);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
    @PostMapping("createStudent2")
    public ResponseEntity<StudentEntity> createStudentwithRequestandResponseEntity(RequestEntity<StudentEntity> student) throws Exception {
        StudentEntity user = userService.createUserwithRequestEntity(student);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @GetMapping("getStudent/{id}")
    public ResponseEntity<StudentEntity> getStudentbyId(@PathVariable("id") int studentId) throws Exception {
        StudentEntity studentDetails = userService.getStudentbyId(studentId);
        return new ResponseEntity<>(studentDetails,HttpStatus.FOUND);
    }

}
