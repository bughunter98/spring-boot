package com.udemypractise.SpringBootTutorialPractise.service;

import com.udemypractise.SpringBootTutorialPractise.model.StudentEntity;
import org.springframework.http.RequestEntity;

import java.util.List;

public interface UserService {
    StudentEntity createUser(StudentEntity student) throws Exception;

    StudentEntity createUserwithRequestEntity(RequestEntity<StudentEntity> student);

    StudentEntity getStudentbyId(int id) throws Exception;

    List<StudentEntity> getAllStudents() throws Exception;

    StudentEntity updateStudents(StudentEntity student) throws Exception;

    void deleteStudentbyId(int studentid) throws Exception;
}
