package lessons.lesson05.coursesystem.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course extends Entity {
    private final String courseName;
    private final Teacher teacher;
    private final List<Student> students;
    private final Map<Integer, Map<LocalDate, Boolean>> attendance;

    public Course(Integer id, String courseName, Teacher teacher, List<Student> students) {
        super(id);
        this.courseName = courseName;
        this.teacher = teacher;
        this.students = students;
        this.attendance = new HashMap<>();
    }

    public Map<Integer, Map<LocalDate, Boolean>> getAttendance() {
        return new HashMap<>(attendance);
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public String getCourseName() {
        return courseName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void addStudent(final Student student) throws Exception {
        if (students.contains(student)) {
            throw new Exception("Student is already in the course");
        }

        students.add(student);
        attendance.put(student.getId(), new HashMap<>());
    }

    public void markAttendance(final Integer studentId, final LocalDate date, final boolean attended) {
        attendance.getOrDefault(studentId, new HashMap<>()).put(date, attended);
    }

    @Override
    public String toString() {
        return String.format("Id: %d\nName: %s\nTeacher: %s", getId(), courseName, teacher.getName());
    }
}

