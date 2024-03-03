package com.swe6673.studentmanagementsys.controller;

import com.swe6673.studentmanagementsys.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
      @PostMapping
      public Student createStudent(@RequestBody Student student) {
            return null;
      }

      @PutMapping("/{id}")
      public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
            return null;
      }

      @DeleteMapping("/{id}")
      public void deleteStudent(@PathVariable Long id) {
      }

      @GetMapping
      public List<Student> getAllStudents() {
            return null;
      }

      @GetMapping("/{email}")
      public Student getStudentByEmail(@PathVariable String email) {
            return null;
      }
}
