package lessons.lesson05.quiz;

import lessons.lesson05.quiz.model.InputQuestion;
import lessons.lesson05.quiz.model.MultipleChoiceQuestion;
import lessons.lesson05.quiz.model.Question;
import lessons.lesson05.quiz.model.Quiz;
import lessons.lesson05.quiz.model.Team;
import lessons.lesson05.quiz.repository.QuizRepository;
import lessons.lesson05.quiz.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizService {
    private final QuizRepository quizRepository;
    private final TeamRepository teamRepository;
    private final Scanner scanner;
    private final Integer MAX_ANSWERS = 4;

    public QuizService(QuizRepository quizRepository, TeamRepository teamRepository, Scanner scanner) {
        this.quizRepository = quizRepository;
        this.teamRepository = teamRepository;
        this.scanner = scanner;
    }

    public void createQuiz() {
        System.out.println("Creating quiz");
        System.out.print("Enter quiz name: ");

        String name = scanner.nextLine();
        List<Team> teams = getTeams();

        List<Question> questions = new ArrayList<>();

        while (true) {
            System.out.print("Enter question: ");
            String question = scanner.nextLine();

            System.out.println("Question type: [1] - Multiple Choice, [2] - Input");
            String questionType = scanner.nextLine();

            switch (questionType) {
                case "1" -> {
                    MultipleChoiceQuestion multipleChoiceQuestion = createMultipleChoiceQuestion(question);
                    questions.add(multipleChoiceQuestion);
                }

                case "2" -> {
                    InputQuestion inputQuestion = createInputQuestion(question);
                    questions.add(inputQuestion);
                }
            }

            System.out.print("Continue? (y/n): ");

            boolean notContinue = scanner.nextLine().equals("n");

            if (notContinue) break;
        }

        List<Quiz> quizList = quizRepository.findAll();
        Integer quizId = quizList.isEmpty() ? 1 : quizList.getLast().getId() + 1;

        Quiz quiz = new Quiz(quizId, name, questions, teams);

        quizRepository.save(quiz);
    }


    public List<Team> getTeams() {
        List<Team> teams = teamRepository.findAll();
        System.out.println("Teams");

        for (Team team : teams) {
            System.out.println(team.toString());
            System.out.println("==================");
        }

        System.out.print("Enter teams id (1, 2, 3): ");

        String idsAsString = scanner.nextLine();
        String[] idsAsList = idsAsString.split(", ");

        List<Team> result = new ArrayList<>();

        for (String id : idsAsList) {
            Integer idAsInt = Integer.parseInt(id);
            teamRepository.findById(idAsInt).ifPresent(result::add);
        }

        return result;
    }

    public MultipleChoiceQuestion createMultipleChoiceQuestion(final String questionText) {
        List<String> answers = new ArrayList<>();
        String correctAnswer = null;

        boolean correctAnswerSelected = false;

        for (int i = 0; i < MAX_ANSWERS; i++) {
            System.out.print("Enter answer: ");
            String answer = scanner.nextLine();

            if (!correctAnswerSelected) {
                System.out.print("Is this correct answer? (y/n): ");
                boolean isAnswerCorrect = scanner.nextLine().equals("y");

                if (isAnswerCorrect) {
                    correctAnswerSelected = true;
                    correctAnswer = answer;
                }
            }

            answers.add(answer);
        }

        if (!correctAnswerSelected) {
            correctAnswer = answers.getFirst();
        }

        return new MultipleChoiceQuestion(questionText, 1, answers, correctAnswer);
    }

    public InputQuestion createInputQuestion(final String questionText) {
        System.out.print("Enter answer: ");
        String answer = scanner.nextLine();

        return new InputQuestion(questionText, 1, answer);
    }

    public void runQuiz() {
        System.out.print("Enter quiz id: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        Quiz quiz = quizRepository.findById(id).orElse(null);

        if (quiz == null) {
            System.out.println("Quiz not found");
            return;
        }

        System.out.println("Starting quiz");

        int score = 0;

        List<Question> questions = quiz.getQuestions();

        for (Question question : questions) {
            System.out.println(question.getText());

            if (question instanceof MultipleChoiceQuestion) {
                List<String> options = ((MultipleChoiceQuestion) question).getOptions();

                for (int i = 0; i < options.size(); i++) {
                    System.out.printf("%d - %s\n", i + 1, options.get(i));
                }

                System.out.print("Your answer: ");
                int answer = scanner.nextInt();
                scanner.nextLine();

                boolean answerCorrect = question.checkAnswer(options.get(answer - 1));

                if (answerCorrect) {
                    score += question.getPoints();
                    System.out.println("ANSWER CORRECT");
                } else {
                    System.out.println("ANSWER INCORRECT");
                }

            } else {
                System.out.print("Your answer: ");
                String answer = scanner.nextLine();

                boolean answerCorrect = question.checkAnswer(answer);

                if (answerCorrect) {
                    score += question.getPoints();
                    System.out.println("ANSWER CORRECT");
                } else {
                    System.out.println("ANSWER INCORRECT");
                }
            }
        }

        System.out.printf("Your score: %d\n", score);
    }
}


