package lessons.lesson05.coursesystem.model;

public abstract class Entity {
    private final Integer id;

    public Entity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }
}
