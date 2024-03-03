package com.swe6673.studentmanagementsys.service;

import com.swe6673.studentmanagementsys.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudentById(Long id);

    Student updateStudent(Student student);

    void deleteStudentById(Long id);

    List<Student> searchStudents(String query);
}
