package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//this interface is responsible for data access
public interface StudentRepository extends JpaRepository<Student,Long> {
    //custom function to find user by email
    ///Select * FROM student where email = ?
//    @Query("SELECT s FROM Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
