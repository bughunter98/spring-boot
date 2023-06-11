package com.udemypractise.SpringBootTutorialPractise.service;

import com.udemypractise.SpringBootTutorialPractise.model.StudentEntity;

public interface UserService {
    StudentEntity createUser(StudentEntity student) throws Exception;
}
