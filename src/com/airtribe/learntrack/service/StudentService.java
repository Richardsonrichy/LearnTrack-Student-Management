package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.List;

public class StudentService {

    private StudentRepository studentRepository = new StudentRepository();

    // Add new student
    public Student addStudent(String firstName, String lastName, String email, String batch) {

        int id = IdGenerator.getNextStudentId();

        Student student = new Student(id, firstName, lastName, email, batch, true);

        studentRepository.addStudent(student);

        return student;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    // Search by ID
    public Student getStudentById(int id) {
        Student s = studentRepository.getStudentById(id);
        if (s == null) {
            throw new EntityNotFoundException("Student not found with ID: " + id);
        }
        return s;
    }

    // Deactivate student
    public boolean deactivateStudent(int id) {
        Student s = studentRepository.getStudentById(id);
        if (s != null) {
            s.setActive(false);
            return true;
        }
        return false;
    }
}
