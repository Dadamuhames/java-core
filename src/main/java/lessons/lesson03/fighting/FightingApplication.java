package lessons.lesson03.fighting;

import lessons.lesson03.fighting.model.FighterInfo;
import lessons.lesson03.fighting.service.ui.AnimationService;
import lessons.lesson03.fighting.service.ui.FightScreenService;
import lessons.lesson03.fighting.service.FightService;
import lessons.lesson03.fighting.service.ui.HealthBarService;
import lessons.lesson03.fighting.service.ui.KeyboardService;
import lessons.lesson03.fighting.utils.FighterGenerator;
import lessons.lesson03.fighting.utils.GameState;
import lessons.lesson03.fighting.utils.Utils;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class FightingApplication {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            StreetFighter streetFighter = getStreetFighter(scanner);

            System.out.println("========= STREET FIGHTER =========");

            streetFighter.getFighters();


            printKeyboard();
            String choice = scanner.nextLine();

            while (true) {
                switch (choice) {
                    case "I" -> streetFighter.getFighterInfo();

                    case "F" -> streetFighter.fight();

                    case "Q" -> {
                        Utils.clearTerminal();
                        System.exit(0);
                    }
                }


                printKeyboard();
                choice = scanner.nextLine();
            }
        }
    }

    public static void printKeyboard() {
        System.out.println("\n[I] - Get fighter info | [F] - Fight | [Q] - Quit");
    }


    public static StreetFighter getStreetFighter(final Scanner scanner) {
        Map<String, FighterInfo> fighters = FighterGenerator.generatePlayers();

        GameState gameState = new GameState();
        Random random = new Random();

        FighterRepository fighterRepository = new FighterRepository(fighters);

        HealthBarService healthBarService = new HealthBarService();
        FightScreenService fightScreenService = new FightScreenService(healthBarService);

        KeyboardService keyboardService = new KeyboardService(gameState);

        AnimationService animationService = new AnimationService(fightScreenService);

        FightService fightService = new FightService(fighterRepository, fightScreenService, scanner, gameState, random, keyboardService, animationService);

        return new StreetFighter(fightService, fighterRepository, scanner);
    }
}
