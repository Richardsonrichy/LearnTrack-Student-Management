package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class EnrollmentService {

    private EnrollmentRepository repo = new EnrollmentRepository();
    private StudentService studentService;
    private CourseService courseService;

    public EnrollmentService(StudentService s, CourseService c) {
        this.studentService = s;
        this.courseService = c;
    }

    // ========== ENROLL STUDENT ==========
    public Enrollment enrollStudent(int studentId, int courseId) {

        // 1️⃣ Student Validation
        var student = studentService.getStudentById(studentId);
        if (!student.isActive())
            throw new InvalidInputException("Student is deactivated. Cannot enroll!");

        // 2️⃣ Course Validation
        var course = courseService.getCourseById(courseId);

        if (course.getStatus() == CourseStatus.INACTIVE)
            throw new InvalidInputException("Course is INACTIVE. Cannot enroll!");

        // 3️⃣ Check Duplicate ACTIVE Enrollment
        List<Enrollment> existing = repo.getEnrollmentsByStudentId(studentId);

        for (Enrollment e : existing) {
            if (e.getCourseId() == courseId && e.getStatus() == EnrollmentStatus.ACTIVE)
                throw new InvalidInputException("Student already enrolled in this course!");
        }

        // 4️⃣ Create Enrollment
        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId);
        repo.addEnrollment(enrollment);

        return enrollment;
    }

    // ========== GET ENROLLMENTS ==========
    public List<Enrollment> getEnrollmentsByStudent(int id) {
        return repo.getEnrollmentsByStudentId(id);
    }

    // ========== MARK COMPLETED ==========
    public boolean markCompleted(int enrollmentId) {

        for (Enrollment e : repo.getAllEnrollments()) {

            if (e.getId() == enrollmentId) {

                if (e.getStatus() == EnrollmentStatus.CANCELLED)
                    throw new InvalidInputException("Cannot complete a cancelled enrollment!");

                if (e.getStatus() == EnrollmentStatus.COMPLETED)
                    throw new InvalidInputException("Enrollment is already completed!");

                e.markCompleted();
                return true;
            }
        }
        return false;
    }

    // ========== CANCEL ENROLLMENT ==========
    public boolean cancelEnrollment(int enrollmentId) {

        for (Enrollment e : repo.getAllEnrollments()) {

            if (e.getId() == enrollmentId) {

                if (e.getStatus() == EnrollmentStatus.COMPLETED)
                    throw new InvalidInputException("Cannot cancel a completed enrollment!");

                if (e.getStatus() == EnrollmentStatus.CANCELLED)
                    throw new InvalidInputException("Enrollment is already cancelled!");

                e.cancel();
                return true;
            }
        }
        return false;
    }
}
