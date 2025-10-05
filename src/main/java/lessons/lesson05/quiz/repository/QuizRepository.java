package lessons.lesson05.quiz.repository;

import lessons.lesson05.quiz.model.Quiz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QuizRepository {
    private final Map<Integer, Quiz> quizes;

    public QuizRepository() {
        this.quizes = new HashMap<>();
    }

    public List<Quiz> findAll() {
        return quizes.values().stream().toList();
    }

    public Optional<Quiz> findById(final Integer id) {
        return Optional.ofNullable(quizes.get(id));
    }

    public void save(final Quiz quiz) {
        int id = quiz.getId();

        quizes.put(id, quiz);
    }
}
