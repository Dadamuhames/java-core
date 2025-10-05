package lessons.lesson05.quiz;

import lessons.lesson05.quiz.model.Team;
import lessons.lesson05.quiz.repository.QuizRepository;
import lessons.lesson05.quiz.repository.TeamRepository;

import java.util.Map;
import java.util.Scanner;

public class QuizApplication {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            QuizRepository quizRepository = new QuizRepository();
            TeamRepository teamRepository = getTeamRepository();


            QuizService quizService = new QuizService(quizRepository, teamRepository, scanner);

            printKeyboard();
            String choice = scanner.nextLine();

            while (true) {
                switch (choice) {
                    case "S" -> quizService.runQuiz();

                    case "A" -> quizService.createQuiz();

                    case "Q" -> System.exit(0);
                }

                printKeyboard();
                choice = scanner.nextLine();
            }

        }
    }


    public static void printKeyboard() {
        System.out.println("[S] - Start | [A] - Add | [Q] - Quit");
    }

    public static TeamRepository getTeamRepository() {
        Team team = new Team(1, "Nirvana");
        Team team1 = new Team(2, "Beatles");

        Map<Integer, Team> teams = Map.of(1, team, 2, team1);

        return new TeamRepository(teams);
    }
}
