package lessons.lesson05.coursesystem.model;

public abstract class Person extends Entity {
    private String name;
    private String email;

    public Person(Integer id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public abstract String getRole();

    @Override
    public String toString() {
        return String.format("Id: %d\nName: %s", getId(), name);
    }
}
