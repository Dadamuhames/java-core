package lessons.lesson05.quiz.model;

public class InputQuestion extends Question {
    private final String correctAnswer;

    public InputQuestion(String text, int points, String correctAnswer) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(final String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
