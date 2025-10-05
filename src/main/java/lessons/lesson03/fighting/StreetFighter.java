package lessons.lesson03.fighting;

import lessons.lesson03.fighting.model.Fighter;
import lessons.lesson03.fighting.model.FighterInfo;
import lessons.lesson03.fighting.service.FightService;

import java.util.List;
import java.util.Scanner;

public class StreetFighter {
    private final FightService fightService;
    private final FighterRepository fighterRepository;
    private final Scanner scanner;

    public StreetFighter(FightService fightService, FighterRepository fighterRepository, Scanner scanner) {
        this.fightService = fightService;
        this.fighterRepository = fighterRepository;
        this.scanner = scanner;
    }

    public void fight() {
        try {
            System.out.println("CHOOSE YOUR FIGHTER!");

            String fighterOneCode = scanner.nextLine();
            FighterInfo fighterOneInfo = fightService.getFighter(fighterOneCode);
            Fighter fighterOne = new Fighter(fighterOneInfo);

            System.out.println("CHOOSE YOUR ENEMY!");

            String fighterTwoCode = scanner.nextLine();
            FighterInfo fighterTwoInfo = fightService.getFighter(fighterTwoCode);
            Fighter fighterTwo = new Fighter(fighterTwoInfo);

            System.out.println("FIGHT!");

            fightService.fight(fighterOne, fighterTwo);
        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }
    }


    public void getFighters() {
        System.out.println("======= FIGHTERS =======");

        List<FighterInfo> fighters = fighterRepository.findAll();

        System.out.println("=".repeat(20));

        for (FighterInfo fighterInfo : fighters) {
            System.out.println(fighterInfo.toString());
            System.out.println("=".repeat(20));
        }
    }

    public void getFighterInfo() {
        System.out.print("Enter fighter code: ");

        String code = scanner.nextLine();

        try {
            fightService.getFighterInfo(code);
        } catch (Exception e) {
            System.out.printf("Error - %s\n", e.getMessage());
        }

    }
}
