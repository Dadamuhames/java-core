package lessons.lesson05.coursesystem;

import java.util.Scanner;

public class CourseSystemApplication {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            TeachingCenter teachingCenter = Factory.getTeachingCenter(scanner);

            printKeyboard();
            System.out.print("Choose action: ");
            String choice = scanner.nextLine();

            while (true) {
                switch (choice) {
                    case "S" -> teachingCenter.studentsList();

                    case "T" -> teachingCenter.teacherList();

                    case "C" -> teachingCenter.courseList();

                    case "I" -> teachingCenter.courseInfo();

                    case "A" -> teachingCenter.addCourse();

                    case "M" -> teachingCenter.markAttendanceAndGrades();

                    case "AS" -> teachingCenter.addStudentToCourse();

                    case "Q" -> System.exit(0);
                }

                printKeyboard();
                System.out.print("Choose action: ");
                choice = scanner.nextLine();
            }
        }
    }


    public static void printKeyboard() {
        System.out.println("[S] - Students | [T] - Teachers | [C] - Courses");
        System.out.println("[I] - Course Info | [A] - Add Create | [AS] - Add student to course");
        System.out.println("[M] - Manage attendance | [Q] - Quit");
    }


}
