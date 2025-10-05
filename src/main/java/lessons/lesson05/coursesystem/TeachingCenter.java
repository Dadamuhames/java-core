package lessons.lesson05.coursesystem;

import lessons.lesson05.coursesystem.model.Course;
import lessons.lesson05.coursesystem.model.Student;
import lessons.lesson05.coursesystem.model.Teacher;
import lessons.lesson05.coursesystem.service.CourseCreateService;
import lessons.lesson05.coursesystem.service.EntityService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TeachingCenter {
    private final Scanner scanner;
    private final EntityService<Course> courseService;
    private final EntityService<Teacher> teacherService;
    private final EntityService<Student> studentService;
    private final CourseCreateService courseCreateService;

    public TeachingCenter(Scanner scanner, EntityService<Course> courseService, EntityService<Teacher> teacherService, EntityService<Student> studentService, CourseCreateService courseCreateService) {
        this.scanner = scanner;
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.courseCreateService = courseCreateService;
    }

    public void courseList() {
        System.out.println("Course list");
        courseService.printList();
    }

    public void courseInfo() {
        System.out.print("Enter course id: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();

        try {
            Course course = courseService.getInstance(courseId);
            System.out.println(course);

        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }

    public void studentsList() {
        studentService.printList();
    }

    public void teacherList() {
        teacherService.printList();
    }

    public void addCourse() {
        try {
            courseCreateService.create();
            System.out.println("Course created");
        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }

    public void addStudentToCourse() {
        try {
            System.out.print("Enter course id: ");
            Integer courseId = scanner.nextInt();
            scanner.nextLine();


            System.out.print("Student id: ");
            Integer studentId = scanner.nextInt();;
            scanner.nextLine();

            Course course = courseService.getInstance(courseId);
            Student student = studentService.getInstance(studentId);

            course.addStudent(student);

        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }

    public void markAttendanceAndGrades() {
        try {
            System.out.print("Enter course id: ");
            Integer courseId = scanner.nextInt();
            scanner.nextLine();

            Course course = courseService.getInstance(courseId);

            List<Student> students = course.getStudents();

            LocalDate now = LocalDate.now();

            System.out.printf("Mark attendance: %s\n", now);

            for (Student student : students) {
                System.out.println(student.toString());

                System.out.print("Student attended (y/n): ");
                String response = scanner.nextLine();
                course.markAttendance(student.getId(), now, response.equals("y"));

                System.out.print("Enter students grade: ");
                int grade = scanner.nextInt();
                scanner.nextLine();

                student.addGrade(courseId, now, grade);

                System.out.println("================================");
            }
        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }
}
