package lessons.lesson05.coursesystem;

import lessons.lesson05.coursesystem.model.Course;
import lessons.lesson05.coursesystem.model.Student;
import lessons.lesson05.coursesystem.model.Teacher;
import lessons.lesson05.coursesystem.service.CourseCreateService;
import lessons.lesson05.coursesystem.service.EntityService;

import java.util.Map;
import java.util.Scanner;

public class Factory {
    public static Repository<Student> getStudentRepository() {
        Student student = new Student(1, "Ted Mosby", "shmosby@gmail.com");
        Student student2 = new Student(2, "Jonny Cage", "cagejonny@gmail.com");
        Map<Integer, Student> students = Map.of(1, student, 2, student2);

        return new Repository<>(students);
    }

    public static Repository<Teacher> getTeacherRepository() {
        Teacher teacher = new Teacher(1, "Master Miyagi", "miyagemaster@kobra.kai", "Karate");
        Teacher teacher1 = new Teacher(2, "Indiana Jones", "jonesindiana@harward.edu", "Architecture");

        Map<Integer, Teacher> teachers = Map.of(1, teacher, 2, teacher1);

        return new Repository<>(teachers);
    }

    public static TeachingCenter getTeachingCenter(final Scanner scanner) {
        Repository<Course> courseRepository = new Repository<>();
        Repository<Student> studentRepository = getStudentRepository();
        Repository<Teacher> teacherRepository = getTeacherRepository();

        EntityService<Course> courseService = new EntityService<>(courseRepository);
        EntityService<Student> studentService = new EntityService<>(studentRepository);
        EntityService<Teacher> teacherService = new EntityService<>(teacherRepository);

        CourseCreateService courseCreateService = new CourseCreateService(courseRepository, teacherService, studentService, scanner);

        return new TeachingCenter(scanner, courseService, teacherService, studentService, courseCreateService);
    }
}
