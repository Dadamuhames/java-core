package lessons.lesson03.fighting.service;

import lessons.lesson03.fighting.model.Fighter;
import lessons.lesson03.fighting.model.FighterInfo;
import lessons.lesson03.fighting.FighterRepository;
import lessons.lesson03.fighting.service.action.EnemyActionService;
import lessons.lesson03.fighting.service.action.PlayerActionService;
import lessons.lesson03.fighting.service.ui.AnimationService;
import lessons.lesson03.fighting.service.ui.FightScreenService;
import lessons.lesson03.fighting.service.ui.KeyboardService;
import lessons.lesson03.fighting.utils.FighterState;
import lessons.lesson03.fighting.utils.GameState;
import lessons.lesson03.fighting.utils.Utils;

import java.util.Random;
import java.util.Scanner;

public class FightService {
    private final FighterRepository fighterRepository;
    private final FightScreenService fightScreenService;
    private final Scanner scanner;
    private final GameState gameState;
    private final Random random;
    private final KeyboardService keyboardService;
    private final AnimationService animationService;

    public FightService(FighterRepository fighterRepository, FightScreenService fightScreenService, Scanner scanner, GameState gameState, Random random, KeyboardService keyboardService, AnimationService animationService) {
        this.fighterRepository = fighterRepository;
        this.fightScreenService = fightScreenService;
        this.scanner = scanner;
        this.gameState = gameState;
        this.random = random;
        this.keyboardService = keyboardService;
        this.animationService = animationService;
    }

    public void fight(final Fighter player, final Fighter enemy) throws Exception {
        fightScreenService.printScreen(player, enemy);

        PlayerActionService playerService = new PlayerActionService(player, enemy, scanner, random, gameState, fightScreenService, keyboardService, animationService);
        EnemyActionService enemyActionService = new EnemyActionService(player, enemy, fightScreenService, random, animationService);

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            int turn;

            if (player.getState().equals(FighterState.BLOCK)) {
                turn = 2;
            } else if (enemy.getState().equals(FighterState.BLOCK)) {
                turn = 1;
            } else {
                turn = random.nextInt(2) + 1;
            }

            switch (turn) {
                case 1 -> playerService.action();

                case 2 -> enemyActionService.action();
            }

            if (enemy.getState().equals(FighterState.DIE)) {
                Utils.clearTerminal();
                System.out.println("YOU WON!");
                return;
            }

            if (player.getState().equals(FighterState.DIE)) {
                Utils.clearTerminal();
                System.out.println("YOU LOST!");
                return;
            }

            if (!player.getState().equals(FighterState.BLOCK)) {
                player.setState(FighterState.IDLE);
            }

            if (!enemy.getState().equals(FighterState.BLOCK)) {
                enemy.setState(FighterState.IDLE);
            }

            fightScreenService.printScreen(player, enemy);
        }
    }

    public FighterInfo getFighter(final String code) throws Exception {
        return fighterRepository.findFighterByCode(code).orElseThrow(() -> new Exception("Fighter code invalid"));
    }

    public void getFighterInfo(final String code) throws Exception {
        FighterInfo fighter = getFighter(code);

        System.out.println(fighter.toString());
    }
}
