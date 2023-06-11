package com.udemypractise.SpringBootTutorialPractise.service.impl;

import com.udemypractise.SpringBootTutorialPractise.model.StudentEntity;
import com.udemypractise.SpringBootTutorialPractise.repository.StudentRepository;
import com.udemypractise.SpringBootTutorialPractise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
