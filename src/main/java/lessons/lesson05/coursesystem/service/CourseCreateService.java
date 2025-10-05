package lessons.lesson05.coursesystem.service;

import lessons.lesson05.coursesystem.Repository;
import lessons.lesson05.coursesystem.model.Course;
import lessons.lesson05.coursesystem.model.Student;
import lessons.lesson05.coursesystem.model.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseCreateService {
    private final Repository<Course> courseRepository;
    private final EntityService<Teacher> teacherService;
    private final EntityService<Student> studentService;
    private final Scanner scanner;

    public CourseCreateService(Repository<Course> courseRepository, EntityService<Teacher> teacherService, EntityService<Student> studentService, Scanner scanner) {
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.scanner = scanner;
    }

    public void create() throws Exception {
        System.out.println("Adding new course");

        System.out.print("Enter course name: ");
        String name = scanner.nextLine();

        teacherService.printList();
        System.out.print("Enter teacher id: ");
        Integer teacherId = scanner.nextInt();
        scanner.nextLine();
        Teacher teacher = teacherService.getInstance(teacherId);

        studentService.printList();
        List<Student> students = getStudents();

        List<Course> courses = courseRepository.findAll();
        Integer courseId = courses.isEmpty() ? 1 : courses.getLast().getId() + 1;

        Course course = new Course(courseId, name, teacher, students);

        courseRepository.save(course);
    }


    public List<Student> getStudents() throws Exception {
        System.out.println("Enter students id (1, 2, 3): ");
        String studentIdString = scanner.nextLine();

        String[] studentIdList = studentIdString.split(", ");

        List<Student> students = new ArrayList<>();

        for (String s : studentIdList) {
            Integer studentId = Integer.parseInt(s);

            Student student = studentService.getInstance(studentId);

            students.add(student);
        }

        return students;
    }
}
