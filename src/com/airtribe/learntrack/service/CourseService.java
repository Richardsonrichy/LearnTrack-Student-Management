package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import java.util.List;
import com.airtribe.learntrack.enums.CourseStatus;

public class CourseService {

    private CourseRepository courseRepository = new CourseRepository();

    // Add course
    public Course addCourse(String name, String description, int durationWeeks) {

        int id = IdGenerator.getNextCourseId();

        Course course = new Course(id, name, description, durationWeeks);


        courseRepository.addCourse(course);

        return course;
    }

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    // Get course by ID
    public Course getCourseById(int id) {
        Course c = courseRepository.getCourseById(id);
        if (c == null) {
            throw new EntityNotFoundException("Course not found with ID: " + id);
        }
        return c;
    }

    // Deactivate course
    public boolean deactivateCourse(int id) {
        Course c = courseRepository.getCourseById(id);
        if (c != null) {
            c.setStatus(CourseStatus.INACTIVE);
            return true;
        }
        return false;
    }


    // Activate course
    public boolean activateCourse(int id) {
        Course c = courseRepository.getCourseById(id);
        if (c != null) {
            c.setStatus(CourseStatus.ACTIVE);
            return true;
        }
        return false;
    }


}
