package com.udemypractise.SpringBootTutorialPractise.service.impl;

import com.udemypractise.SpringBootTutorialPractise.DTO.StudentDto;
import com.udemypractise.SpringBootTutorialPractise.Utilities.StudentUtilities;
import com.udemypractise.SpringBootTutorialPractise.model.StudentEntity;
import com.udemypractise.SpringBootTutorialPractise.repository.StudentRepository;
import com.udemypractise.SpringBootTutorialPractise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public StudentDto createUser(StudentDto studentDto) throws Exception {

        //here we are converting the DTO object to stundent entity to save the data in db
        StudentEntity  student = StudentUtilities.maptoStudentEntity(studentDto);
        System.out.println("Student Details to be created are :"+student);
            if (student.getId()!=findbyStudentId(student))
            {
                //save method returns saved studnt details of type studententity
                StudentEntity savedStudent = studentRepository.save(student);

                /*now we have to convert studententity to student dto and return the response
                 we can simply return the data from the arguments studentDto but inorder to get id of student which is generated
                 we need to get it from student entity only . so we are converting it

                savedStudentDto will have id also .where as the studentDto which is passed as arguments does not
                contain it because id is generated in save method of JPA repo.*/
                    StudentDto savedStudentDto = StudentUtilities.maptoStudentDto(savedStudent);
                return savedStudentDto;
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
    public StudentDto getStudentbyId(int id) throws Exception {
        Integer studentId = id;
        Optional<StudentEntity> value = studentRepository.findById(studentId);
        if (value.isPresent()){
            StudentEntity studentEntity =  value.get();
            return StudentUtilities.maptoStudentDto(studentEntity);
        }
        else {
            throw new Exception("Cannot fetch the Student Details by given id ");
        }
    }

    @Override
    public List<StudentDto> getAllStudents() throws Exception {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        if (studentEntities.isEmpty()){
            throw new Exception("The Student List is Empty.....");
        }
        else {
            //we can do this through adding map data to constructor also . eg :
            //List<StudentDto> studentDtos = studentEntities.stream().map(data-> new StudentDto(data.getId(),data.getFirstName(),data.getLastName() , data.getEmail())).collect(Collectors.toList());
            List<StudentDto> studentDto = studentEntities.stream().map(data-> {
                try {
                    return StudentUtilities.maptoStudentDto(data);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());

             return studentDto;
        }
    }

    @Override
    public StudentDto updateStudents(StudentDto student) throws Exception {
        Optional<StudentEntity> studentEntity = null;//studentRepository.findById(student.getId());
       // Optional<StudentEntity> find = studentRepository.findById(student.getId());
        Optional<StudentDto> studentDtoOptional = Optional.of(student);

        if (studentDtoOptional.isPresent()){
          //  we can do this like this also
            //  studentEntity.map(data-> new StudentDto(data.getId(), data.getFirstName(), data.getLastName(), data.getEmail()));
            studentEntity = studentRepository.findById(studentDtoOptional.get().getId());
        }

        if (studentEntity.isPresent()){
            studentDtoOptional.get().setId(student.getId());
            studentDtoOptional.get().setFirstName(studentDtoOptional.get().getFirstName()!=null ?
                    studentDtoOptional.get().getFirstName() : studentEntity.get().getFirstName());
            studentDtoOptional.get().setLastName(studentDtoOptional.get().getLastName()!=null ?
                    studentDtoOptional.get().getFirstName() : studentEntity.get().getLastName());
            studentDtoOptional.get().setEmail(studentDtoOptional.get().getEmail()!=null ?
                    studentDtoOptional.get().getEmail() : studentEntity.get().getEmail());
            //we can pass data by constructor also like
            //studentDtoOptional.map(data-> new StudentEntity(data.getId,data.getFirstname,data.getLastName,data.getEmailID)).get;
            StudentEntity tobeSaved = studentDtoOptional.map(data-> {
                try {
                    return StudentUtilities.maptoStudentEntity(data);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).get();

            StudentEntity updatedStudentInfo = studentRepository.save(tobeSaved);

            StudentDto updatedStudentResponse = StudentUtilities.maptoStudentDto(updatedStudentInfo);

            return updatedStudentResponse;

        }
        else {
            throw new Exception("Cant update Data ... No Student found with given id...");
        }
    }

    @Override
    public void deleteStudentbyId(int studentid) throws Exception {
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentid);
        if (studentEntity.isPresent()){
            studentRepository.delete(studentEntity.get());
        }
        else {
            throw new Exception("Data cannot be deleted ....Id mismatch");
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
