package com.swe6673.studentmanagementsys.repository;

import com.swe6673.studentmanagementsys.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
      List<Student> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
