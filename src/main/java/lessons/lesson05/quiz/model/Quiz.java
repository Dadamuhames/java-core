package lessons.lesson05.quiz.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private final Integer id;
    private final String name;
    private final List<Question> questions;
    private final List<Team> teams;

    public Quiz(Integer id, String name, List<Question> questions, List<Team> teams) {
        this.id = id;
        this.name = name;
        this.questions = questions;
        this.teams = teams;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Team> getTeams() {
        return new ArrayList<>(this.teams);
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }
}
