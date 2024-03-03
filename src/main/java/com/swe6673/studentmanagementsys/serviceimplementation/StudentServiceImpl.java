package com.swe6673.studentmanagementsys.serviceimplementation;

import com.swe6673.studentmanagementsys.entity.Student;
import com.swe6673.studentmanagementsys.repository.StudentRepository;
import com.swe6673.studentmanagementsys.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

      private StudentRepository studentRepository;

      public StudentServiceImpl(StudentRepository studentRepository) {
            super();
            this.studentRepository = studentRepository;
      }

      @Override
      public Student saveStudent(Student student) {
            return null;
      }

      @Override
      public Student updateStudent(Student student) {
            return null;
      }

      @Override
      public void deleteStudentById(Long id) {
      }

      @Override
      public List<Student> getAllStudents() {
            return null;
      }

      @Override
      public Student getStudentById(Long id) {
            return null;
      }
      
      @Override
      public List<Student> searchStudents(String keyword) {
            // TODO: Implement the search logic here
            return null;
      }
}
