package com.udemypractise.SpringBootTutorialPractise.controller;

import com.udemypractise.SpringBootTutorialPractise.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;



// BASIC understanding of the REST Api's wihout connecting to DataBases
@RestController
@RequestMapping("students")
public class DemoControllerwithExamples {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @GetMapping
    public Student getStudent(){
        Student student = new Student(1,"Sai","Teja");
        return student;
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    @PutMapping("{id}/update")
    public Student updateStudent(@RequestBody Student student,@PathVariable("id") int studentId){
        student.setId(studentId);
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    @DeleteMapping("{id}/delete")
    //deleting a Student with id. using path variable
    public List<Student> deleteStudent(@PathVariable int id){
       Student student = new Student(1,"Sai","Teja");
       Student student1 = new Student(2,"Nobita","Shizuka");
       List<Student> studentList = Arrays.asList(student1,student);
       //studentList.add(student);
        // studentList.add(student1);
       List<Student> deletedStudent = studentList.stream().filter(val->val.getId()==id).collect(Collectors.toList());
       return deletedStudent;
    }

    @GetMapping("query")
    public Student getStudentbyusingRequestParam(@RequestParam int id ,
                                                 @RequestParam String firstName , @RequestParam String lastName){
        Student student = new Student(id,firstName,lastName);
        return student;
    }

    //Print all the Header Values that are in http request headers
    @GetMapping("getHeaders")
    public ResponseEntity<Map<String, Object>> getResponseEntityHeader(HttpServletRequest httpServletRequest) {
        RestTemplate restTemplate = new RestTemplate();
        Enumeration<String> values = httpServletRequest.getHeaderNames();
        Map<String, Object> returnValue = new HashMap<>();
        while (values.hasMoreElements()) {
            String headerName = values.nextElement();
            returnValue.put(headerName, httpServletRequest.getHeader(headerName));
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    //Learning about HTTP Headers .here we are adding our custom headers to the HTTP Response.
    @GetMapping("studentHttp")
    public ResponseEntity<Student> httpHeaders(/*@RequestHeader("route-type") String header*/){
        Student student = new Student(1,"Sai","Teja");
        ResponseEntity<Student> responseEntity = ResponseEntity.ok().header("route-type","SAITEJ")
                .header("custom-header", "JAIJAWANCOLONY").body(student);
        String headerval= responseEntity.getHeaders().getFirst("route-type");
        System.out.println("Header Value is "+headerval);
        return responseEntity;

    }

    @GetMapping("getHeadersfromRestTemplate")
    //Here we are fethching the response header values from the above api(studentHttp) using rest template builder
    public String getHeadersfromRestTemplate() {
        ResponseEntity responseEntity = restTemplateBuilder.build()
                .getForEntity("http://localhost:8080/studentHttp",Student.class);

        HttpHeaders headers = responseEntity.getHeaders();
        String headerValue = headers.getFirst("route-type");
        String headerValue1 = headers.getFirst("custom-header");
        String output = "The header values from studentHttp api Response is ";

        String finalOutput = output+" : "+headerValue+" : "+headerValue1;
        return finalOutput;
    }

    @GetMapping("getHeadersfromWebClient")
    //Here we are fethching the response header values from the above api(studentHttp) using Webclient
    public Mono<String> getHeadersfromWebClient(){
        Mono<String> webClient = WebClient.create().get().uri("http://localhost:8080/studentHttp").
                retrieve().toEntity(String.class).filter(
                        response -> response.getStatusCode().is2xxSuccessful() && !response.getHeaders().isEmpty())
                .flatMap(finalResp -> Mono.justOrEmpty(finalResp.getHeaders().getFirst("route-type")));
        System.out.println("Header Value from Webclient response is "+webClient);
        return webClient;
    }

}


