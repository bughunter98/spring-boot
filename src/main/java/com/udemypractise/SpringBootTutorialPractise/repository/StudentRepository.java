package com.udemypractise.SpringBootTutorialPractise.repository;


import com.udemypractise.SpringBootTutorialPractise.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {
    Optional<StudentEntity> findByEmail(String email);
}
