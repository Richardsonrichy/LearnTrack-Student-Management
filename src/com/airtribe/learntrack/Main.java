package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;
import java.util.List;
import java.util.Scanner;
import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.enums.CourseStatus;

public class Main {

    public static void main(String[] args) {

        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);


        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n===== LearnTrack Menu =====");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Deactivate Student");
                System.out.println("4. Add Course");
                System.out.println("5. View Courses");
                System.out.println("6. Enroll Student to Course");
                System.out.println("7. View Student Enrollments");
                System.out.println("8. Complete Enrollment");
                System.out.println("9. Cancel Enrollment");
                System.out.println("10. Deactivate Course");
                System.out.println("11. Activate Course");
                System.out.println("12. Search Student By ID");
                System.out.println("13. Exit");
                System.out.print("Enter choice: ");


                int choice = sc.nextInt();

                switch (choice) {

                    case MenuOptions.ADD_STUDENT:
                        System.out.print("First Name: ");
                        String fn = sc.next();
                        System.out.print("Last Name: ");
                        String ln = sc.next();
                        System.out.print("Email: ");
                        String email = sc.next();
                        System.out.print("Batch: ");
                        String batch = sc.next();

                        InputValidator.validateName(fn);
                        InputValidator.validateName(ln);
                        InputValidator.validateEmail(email);

                        studentService.addStudent(fn, ln, email, batch);
                        System.out.println(AppConstants.STUDENT_ADDED);
                        break;

                    case MenuOptions.VIEW_STUDENTS:
                        System.out.println(studentService.getAllStudents());
                        break;

                    case MenuOptions.DEACTIVATE_STUDENT:
                        System.out.print("Enter Student ID: ");
                        int sid = sc.nextInt();
                        InputValidator.validatePositiveNumber(sid, "Student ID");

                        if (studentService.deactivateStudent(sid)) {
                            System.out.println(AppConstants.STUDENT_DEACTIVATED);
                        } else {
                            System.out.println(AppConstants.STUDENT_NOT_FOUND);
                        }
                        break;

                    case MenuOptions.ADD_COURSE:
                        sc.nextLine();
                        System.out.print("Course Name: ");
                        String cname = sc.nextLine();
                        System.out.print("Description: ");
                        String desc = sc.nextLine();
                        System.out.print("Duration Weeks: ");
                        int weeks = sc.nextInt();

                        InputValidator.validateName(cname);
                        InputValidator.validatePositiveNumber(weeks, "Duration");

                        courseService.addCourse(cname, desc, weeks);
                        System.out.println(AppConstants.COURSE_ADDED);
                        break;

                    case MenuOptions.VIEW_COURSES:
                        System.out.println(courseService.getAllCourses());
                        break;

                    case MenuOptions.ENROLL_STUDENT:
                        System.out.print("Student ID: ");
                        int stid = sc.nextInt();
                        InputValidator.validatePositiveNumber(stid, "Student ID");

                        System.out.print("Course ID: ");
                        int cid = sc.nextInt();
                        InputValidator.validatePositiveNumber(cid, "Course ID");

                        enrollmentService.enrollStudent(stid, cid);
                        System.out.println(AppConstants.ENROLLED_SUCCESS);
                        break;

                    case MenuOptions.VIEW_ENROLLMENTS:
                        System.out.print("Enter Student ID: ");
                        int s = sc.nextInt();
                        InputValidator.validatePositiveNumber(s, "Student ID");

                        List<Enrollment> list = enrollmentService.getEnrollmentsByStudent(s);
                        System.out.println(list);
                        break;

                    case MenuOptions.COMPLETE_ENROLLMENT:
                        System.out.print("Enter Enrollment ID: ");
                        int eid1 = sc.nextInt();
                        InputValidator.validatePositiveNumber(eid1, "Enrollment ID");

                        if (enrollmentService.markCompleted(eid1)) {
                            System.out.println("Enrollment marked as COMPLETED!");
                        } else {
                            System.out.println("Enrollment not found!");
                        }
                        break;

                    case MenuOptions.CANCEL_ENROLLMENT:
                        System.out.print("Enter Enrollment ID: ");
                        int eid2 = sc.nextInt();
                        InputValidator.validatePositiveNumber(eid2, "Enrollment ID");

                        if (enrollmentService.cancelEnrollment(eid2)) {
                            System.out.println("Enrollment CANCELLED!");
                        } else {
                            System.out.println("Enrollment not found!");
                        }
                        break;

                    case MenuOptions.DEACTIVATE_COURSE:
                        System.out.print("Enter Course ID: ");
                        int dcid = sc.nextInt();

                        InputValidator.validatePositiveNumber(dcid, "Course ID");

                        if (courseService.deactivateCourse(dcid)) {
                            System.out.println("Course Deactivated Successfully!");
                        } else {
                            System.out.println("Course Not Found!");
                        }
                        break;

                    case MenuOptions.ACTIVATE_COURSE:
                        System.out.print("Enter Course ID: ");
                        int acid = sc.nextInt();

                        InputValidator.validatePositiveNumber(acid, "Course ID");

                        if (courseService.activateCourse(acid)) {
                            System.out.println("Course Activated Successfully!");
                        } else {
                            System.out.println("Course Not Found!");
                        }
                        break;

                    case MenuOptions.SEARCH_STUDENT:
                        System.out.print("Enter Student ID: ");
                        int searchId = sc.nextInt();

                        InputValidator.validatePositiveNumber(searchId, "Student ID");

                        var student = studentService.getStudentById(searchId);

                        if (student == null) {
                            System.out.println("❌ Student Not Found!");
                        } else {
                            System.out.println(student);
                        }
                        break;


                    case MenuOptions.EXIT:
                        System.out.println(AppConstants.EXIT_MESSAGE);
                        System.exit(0);

                    default:
                        System.out.println(AppConstants.INVALID_OPTION);
                }
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}