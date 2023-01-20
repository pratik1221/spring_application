package com.example.demo.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

//this class has to be instantiated ->to tell  it is a spring bean->component
//@service and @component are both same but service is used for semantics
//@Component

@Service
public class StudentService {
    public List<Student> getStudents(){
        return List.of(
                new Student(
                        1L,
                        "Mariam",
                        "Mariam.jamal@gmail.com",
                        LocalDate.of(2000, Month.JANUARY,5),
                        21
                )
        );
    }

}