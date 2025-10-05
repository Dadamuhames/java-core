package lessons.lesson05.quiz.model;

public abstract class Question {
    private final String text;
    private final int points;

    public Question(String text, int points) {
        this.text = text;
        this.points = points;
    }

    public String getText() {
        return text;
    }

    public int getPoints() {
        return points;
    }

    public abstract boolean checkAnswer(final String answer);
}

