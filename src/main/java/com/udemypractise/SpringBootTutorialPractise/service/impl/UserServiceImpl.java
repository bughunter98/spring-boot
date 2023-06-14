package com.udemypractise.SpringBootTutorialPractise.service.impl;

import com.udemypractise.SpringBootTutorialPractise.model.StudentEntity;
import com.udemypractise.SpringBootTutorialPractise.repository.StudentRepository;
import com.udemypractise.SpringBootTutorialPractise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public StudentEntity createUser(StudentEntity student) throws Exception {

                if (student.getId()!=findbyStudentId(student))
                {
                    return studentRepository.save(student);
                }
                else {
                    throw new Exception("Id already exists.. please use new id");
                }
        //    else {
                 //This condition is executed only if there is empty table else it wont be executed
        //    }
    }

    @Override
    public StudentEntity createUserwithRequestEntity(RequestEntity<StudentEntity> student) {
        Map<String,List <String>> headerMap = new LinkedHashMap<String,List <String>>();
        headerMap = student.getHeaders();
        System.out.println("Requested Headers are ......");
        for (String key : headerMap.keySet()){
            if (!key.isEmpty()){
                for (String value : headerMap.get(key)){
                    System.out.println("Key : "+key+"******"+" Value : "+value);
                }
            }
        }
        return null;
    }

    @Override
    public StudentEntity getStudentbyId(int id) throws Exception {
        Integer studentId = id;
        Optional<StudentEntity> value = studentRepository.findById(studentId);
        if (value.isPresent()){
            return value.get();
        }
        else {
            throw new Exception("Cannot fetch the Student Details by given id ");
        }
    }

    private int findbyStudentId(StudentEntity student) {
        Integer id = student.getId();
        int output = 0;
        Optional<StudentEntity> value = studentRepository.findById(id);
        if (value.isPresent()){
            output = value.get().getId();
        }
        return output;
    }

}
