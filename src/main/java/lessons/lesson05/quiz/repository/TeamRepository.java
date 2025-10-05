package lessons.lesson05.quiz.repository;

import lessons.lesson05.quiz.model.Team;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TeamRepository {
    private final Map<Integer, Team> teams;

    public TeamRepository(Map<Integer, Team> teams) {
        this.teams = teams;
    }

    public List<Team> findAll() {
        return this.teams.values().stream().toList();
    }

    public Optional<Team> findById(final Integer id) {
            return Optional.ofNullable(teams.get(id));
    }
}
