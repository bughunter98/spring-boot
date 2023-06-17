package com.udemypractise.SpringBootTutorialPractise.controller;

import com.udemypractise.SpringBootTutorialPractise.model.StudentEntity;
import com.udemypractise.SpringBootTutorialPractise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("getAllStudents")
    public ResponseEntity<List<StudentEntity>> getAllStudents() throws Exception {
        List<StudentEntity>studentEntities = userService.   getAllStudents();
        return new ResponseEntity<>(studentEntities,HttpStatus.FOUND);
    }

    @PutMapping("updateStudent")
    //Put mapping can be used if and only if all the fields in the pojo are available in request
    //generally for put mapping we need to add all the fields of pojo in json.
    //here just for example , i tried to implement put mapping with any one field. so this would act as patch mapping as well.
    public ResponseEntity<StudentEntity> updateStudent(@RequestBody StudentEntity student) throws Exception {
        StudentEntity studentEntities = userService.updateStudents(student);
        return new ResponseEntity<>(studentEntities,HttpStatus.OK);
    }

    @DeleteMapping("deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentid) throws Exception{
        userService.deleteStudentbyId(studentid);
        return new ResponseEntity<>("Student Data Deleted",HttpStatus.OK);
    }

}
