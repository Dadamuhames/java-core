package lessons.lesson05.coursesystem;

import lessons.lesson05.coursesystem.model.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Repository<T extends Entity> {
    private final Map<Integer, T> data;

    public Repository(Map<Integer, T> data) {
        this.data = data;
    }

    public Repository() {
        this.data = new HashMap<>();
    }

    public List<T> findAll() {
        return data.values().stream().toList();
    }

    public Optional<T> findById(Integer i) {
        return Optional.ofNullable(data.get(i));
    }


    public T save(T t) {
        Integer id = t.getId();

        data.put(id, t);

        return t;
    }
}
