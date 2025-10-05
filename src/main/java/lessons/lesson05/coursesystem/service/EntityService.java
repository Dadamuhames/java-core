package lessons.lesson05.coursesystem.service;

import lessons.lesson05.coursesystem.Repository;
import lessons.lesson05.coursesystem.model.Entity;

import java.util.List;

public class EntityService<P extends Entity> {
    private final Repository<P> repository;

    public EntityService(Repository<P> repository) {this.repository = repository;}

    public void printList() {
        List<P> persons = repository.findAll();

        System.out.println("====================");

        for (P p : persons) {
            System.out.println(p.toString());
            System.out.println("====================");
        }
    }


    public P getInstance(final Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Not found"));
    }
}
