package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//this class has to be instantiated ->to tell  it is a spring bean->component
//@service and @component are both same but service is used for semantics
//@Component

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService (StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student>studentByEmail = studentRepository
                .findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
       boolean exists = studentRepository.existsById(studentId);
       if(!exists){
           throw new IllegalStateException(
                   "student with id" + studentId + "does not exists"
           );
       }
       studentRepository.deleteById(studentId);
    }

    @Transactional
    //this annotation makes sure that entity goes into managed state
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->new IllegalStateException(
                        "student with id" + studentId + "does not exist"
                ));
        if(name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if(email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.
                    findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

    }
}
