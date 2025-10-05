package lessons.lesson05.coursesystem.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Student extends Person {
    private Map<Integer, Map<LocalDate, Integer>> grades;

    public Student(Integer id, String name, String email) {
        super(id, name, email);
        this.grades = new HashMap<>();
    }

    public void addGrade(Integer courseId, LocalDate date, int grade) {
        grades.getOrDefault(courseId, new HashMap<>()).put(date, grade);
    }

    public Map<Integer, Map<LocalDate, Integer>> getGrades() {
        return new HashMap<>(grades);
    }

    @Override
    public String getRole() {
        return "STUDENT";
    }
}
