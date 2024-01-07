package com.sbliquibasedemo.controller;

import com.sbliquibasedemo.exception.ResourceNotFoundException;
import com.sbliquibasedemo.model.Student;
import com.sbliquibasedemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;


    @GetMapping
    public List<Student> getAllEmployees(){
        //list the all employee details
        return studentRepository.findAll();

    }

    //build create employee REST API
    @PostMapping
    public Student creatStudent(@RequestBody Student student)
    {
        //adding new employee in to database
        return studentRepository.save(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id)
    {
        //getting employee details by using id
        Student student=studentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Student not exist with id:"+id));
        return ResponseEntity.ok(student);
    }


    //build update employee REST API

    //putmapping used to update the records
    @PutMapping("{id}")
    public  ResponseEntity<Student> updateStudent(@PathVariable long id,@RequestBody Student studentDetails){
        Student updateStudent=studentRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee not exist with id:"+id));
        updateStudent.setFirstName(studentDetails.getFirstName());
        updateStudent.setLastName(studentDetails.getLastName());


        studentRepository.save(updateStudent);

        return  ResponseEntity.ok(updateStudent);
    }
}
