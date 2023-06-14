package com.udemypractise.SpringBootTutorialPractise.service;

import com.udemypractise.SpringBootTutorialPractise.model.StudentEntity;
import org.springframework.http.RequestEntity;

public interface UserService {
    StudentEntity createUser(StudentEntity student) throws Exception;

    StudentEntity createUserwithRequestEntity(RequestEntity<StudentEntity> student);

    StudentEntity getStudentbyId(int id) throws Exception;
}
