package lessons.lesson05.coursesystem.model;

public class Teacher extends Person {
    private final String subject;

    public Teacher(Integer id, String name, String email, String subject) {
        super(id, name, email);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String getRole() {
        return "TEACHER";
    }
}
