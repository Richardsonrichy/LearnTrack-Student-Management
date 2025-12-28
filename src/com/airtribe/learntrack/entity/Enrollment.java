package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.EnrollmentStatus;
import java.time.LocalDate;

public class Enrollment {

    private int id;
    private int studentId;
    private int courseId;
    private LocalDate enrollmentDate;
    private EnrollmentStatus status;

    // Default constructor
    public Enrollment() {
    }

    // Parameterized constructor
    public Enrollment(int id, int studentId, int courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = LocalDate.now(); // sets today's date
        this.status = EnrollmentStatus.ACTIVE; // default
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    // Status change methods
    public void markCompleted() {
        this.status = EnrollmentStatus.COMPLETED;
    }

    public void cancel() {
        this.status = EnrollmentStatus.CANCELLED;
    }


    @Override
    public String toString() {
        return  "\n---------------------------" +
                "\nEnrollment ID : " + id +
                "\nStudent ID    : " + studentId +
                "\nCourse ID     : " + courseId +
                "\nDate          : " + enrollmentDate +
                "\nStatus        : " + status +
                "\n---------------------------";
    }


}
