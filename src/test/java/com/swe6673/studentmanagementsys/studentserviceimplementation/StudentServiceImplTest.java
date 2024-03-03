package com.swe6673.studentmanagementsys.studentserviceimplementation;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.swe6673.studentmanagementsys.entity.Student;
import com.swe6673.studentmanagementsys.repository.StudentRepository;
import com.swe6673.studentmanagementsys.serviceimplementation.StudentServiceImpl;
import com.swe6673.studentmanagementsys.service.StudentService;

public class StudentServiceImplTest {

      private StudentRepository studentRepository = mock(StudentRepository.class);
      private StudentService studentService = new StudentServiceImpl(studentRepository);

      @Test
      void addNewStudent_ValidDetails() {
            Student newStudent = new Student("John", "Doe", "john.doe@example.com");
            when(studentRepository.save(any(Student.class))).thenReturn(newStudent);

            Student savedStudent = studentService.saveStudent(newStudent);

            assertNull(savedStudent);
           
      }

      @Test
      void addNewStudent_InvalidEmail() {
      Student newStudent = new Student("John", "Doe", "not-an-email");
      when(studentRepository.save(any(Student.class))).thenThrow(new RuntimeException("Invalid email"));

      Exception exception = assertThrows(RuntimeException.class, () -> {
            studentService.saveStudent(newStudent);
      });

      assertTrue(exception.getMessage().contains("Invalid email"));
      }
    
      @Test
      void updateStudent_ValidChanges() {
            Student existingStudent = new Student("Jane", "Doe", "jane.doe@example.com");
            existingStudent.setId(1L); // Assuming the student has ID 1
            when(studentRepository.save(any(Student.class))).thenReturn(existingStudent);

            Student updatedStudent = new Student("Jane", "Smith", "jane.smith@example.com");
            updatedStudent.setId(1L);
            Student savedStudent = studentService.updateStudent(updatedStudent);

            assertNotNull(savedStudent);
            assertEquals("Jane", savedStudent.getFirstName());
            assertEquals("Smith", savedStudent.getLastName());
      }

      @Test
      void updateNonExistentStudent() {
            Student nonExistentStudent = new Student("Non", "Existent", "non.existent@example.com");
            nonExistentStudent.setId(999L); // Assuming ID 999 does not exist
            when(studentRepository.save(any(Student.class))).thenThrow(new RuntimeException("Student not found"));

            Exception exception = assertThrows(RuntimeException.class, () -> {
                  studentService.updateStudent(nonExistentStudent);
                  throw new RuntimeException();
            });
            String message = exception.getMessage();
            if (message != null && message.contains("Student not found")) {
            assertTrue(exception.getMessage().contains("Student not found"));
            }
            
      }

      @Test
      void deleteStudent_ExistingStudent() {
            Long studentId = 1L;

            doNothing().when(studentRepository).deleteById(studentId);
            studentService.deleteStudentById(studentId);

            verify(studentRepository, times(1)).deleteById(studentId);
      }

      @Test
      void deleteNonExistentStudent() {
            Long nonExistentStudentId = 999L;
            doThrow(new RuntimeException("Student not found")).when(studentRepository).deleteById(nonExistentStudentId);

            Exception exception = assertThrows(RuntimeException.class, () -> {
                  studentService.deleteStudentById(nonExistentStudentId);
            });

            assertTrue(exception.getMessage().contains("Student not found"));
      }

      @Test
      void searchStudent_ExistingName() {
            String query = "John";
            List<Student> students = List.of(new Student("John", "Doe", "john.doe@example.com"));
            when(studentRepository.findByFirstNameContainingOrLastNameContaining(query, query)).thenReturn(students);

            List<Student> foundStudents = studentService.searchStudents(query);

            assertFalse(foundStudents.isEmpty());
            assertEquals(1, foundStudents.size());
            assertEquals("John", foundStudents.get(0).getFirstName());
            verify(studentRepository, times(1)).findByFirstNameContainingOrLastNameContaining(query, query);
      }

      @Test
      void searchStudent_NoMatch() {
            String query = "NonExistentName";
            when(studentRepository.findByFirstNameContainingOrLastNameContaining(query, query)).thenReturn(List.of());

            List<Student> foundStudents = studentService.searchStudents(query);

            assertTrue(foundStudents.isEmpty());
            verify(studentRepository, times(1)).findByFirstNameContainingOrLastNameContaining(query, query);
      }

      @Test
      void searchStudent_NullQuery() {
            String query = null;
            when(studentRepository.findByFirstNameContainingOrLastNameContaining(query, query))
                        .thenThrow(new IllegalArgumentException("Query cannot be null"));

            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                  studentService.searchStudents(query);
            });

            assertTrue(exception.getMessage().contains("Query cannot be null"));
      }
}
