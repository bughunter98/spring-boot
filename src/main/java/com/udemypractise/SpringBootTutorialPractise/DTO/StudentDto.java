package com.udemypractise.SpringBootTutorialPractise.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//
public class StudentDto {

    private int id;

    private String firstName;

    private String lastName;

    private String email;
}
