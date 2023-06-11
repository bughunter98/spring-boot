package com.udemypractise.SpringBootTutorialPractise.repository;


import com.udemypractise.SpringBootTutorialPractise.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {
    //StudentEntity createStudent(StudentEntity student);
}
