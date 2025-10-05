package lessons.lesson05.quiz.model;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private final List<String> options;
    private final String correctAnswer;

    public MultipleChoiceQuestion(String text, int points, List<String> options, String correctAnswer) {
        super(text, points);
        this.options = new ArrayList<>(options);
        this.correctAnswer = correctAnswer;
    }

    public List<String> getOptions() {
        return new ArrayList<>(options);
    }

    @Override
    public boolean checkAnswer(final String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}
