package lessons.lesson05.quiz.model;

public class Team {
    private final Integer id;
    private final String name;
    private int score;

    public Team(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addPoints(int points) {
        this.score += points;
    }

    @Override
    public String toString() {
        return String.format("Id: %d\nName: %s\nScore: %d", id, name, score);
    }
}
