package com.udemypractise.SpringBootTutorialPractise.Utilities;

import com.udemypractise.SpringBootTutorialPractise.DTO.StudentDto;
import com.udemypractise.SpringBootTutorialPractise.model.StudentEntity;

public class StudentUtilities {

    // to convert Student JPA Entity into Student DTO
    public static StudentDto maptoStudentDto(StudentEntity student) throws Exception {
        if (student!=null){
            StudentDto studentDto = new StudentDto(
                    student.getId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail());
            return studentDto;
        }
     else {
         throw new Exception("Student Data is Null from Database");
        }
    }
    // to convert Student DTO into Student JPA Entity
    public static StudentEntity maptoStudentEntity(StudentDto student) throws Exception{
        if(student.getFirstName()==null && student.getLastName()==null
                && student.getEmail()==null) {
            throw new Exception("Student Data is Null from User . please check");
        }
        else {
            if (studentDataCheck(student)) {
                StudentEntity studentEntity = new StudentEntity(
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail());
                return studentEntity;
            }
        }
        throw new Exception("Data cannot be inserted");
    }

    private static boolean studentDataCheck(StudentDto student) {
        return (student.getFirstName().concat(student.getLastName())!=null && !student.getFirstName().concat(student.getLastName()).isEmpty()) && !student.getEmail().equals("");
    }
}
