package lessons.lesson03.fighting.service.action;

import lessons.lesson03.fighting.model.Fighter;
import lessons.lesson03.fighting.service.ui.FightScreenService;
import lessons.lesson03.fighting.service.ui.AnimationService;
import lessons.lesson03.fighting.service.ui.KeyboardService;
import lessons.lesson03.fighting.utils.FighterState;
import lessons.lesson03.fighting.utils.GameState;

import java.util.Random;
import java.util.Scanner;

public class PlayerActionService {
    private final Fighter player;
    private final Fighter enemy;
    private final Scanner scanner;
    private final Random random;
    private final GameState gameState;
    private final FightScreenService fightScreenService;
    private final KeyboardService keyboardService;
    private final AnimationService animationService;

    public PlayerActionService(Fighter player, Fighter enemy, Scanner scanner, Random random, GameState gameState, FightScreenService fightScreenService, KeyboardService keyboardService, AnimationService animationService) {
        this.player = player;
        this.enemy = enemy;
        this.scanner = scanner;
        this.random = random;
        this.gameState = gameState;
        this.fightScreenService = fightScreenService;
        this.keyboardService = keyboardService;
        this.animationService = animationService;
    }


    public void action() throws Exception {
        System.out.println(keyboardService.getKeyboard());

        System.out.print("Choose action: ");

        String key = scanner.nextLine();

        switch (key) {
            case "A" -> attack();

            case "B" -> block();

            case "H" -> heal();

            case "F" -> fatality();
        }

        if (player.canHeal()) {
            int giveHealingAbility = random.nextInt(4) + 1;

            if (giveHealingAbility == 4) {
                gameState.setPlayerCanHeal(true);
            }
        }

        if (player.getInfo().attack() >= enemy.getHealth()) {
            gameState.setPlayerCanFatality(true);
        }
    }

    private void attack() throws Exception {
        int attackDamage = player.getInfo().attack();

        if (enemy.getState().equals(FighterState.BLOCK)) {
            enemy.setState(FighterState.IDLE);
            return;
        }

        player.setState(FighterState.ATTACK);
        enemy.setState(FighterState.TAKE_DAMAGE);

        for (int i = 0; i < attackDamage; i += 5) {
            enemy.takeDamage(5);

            animationService.animate(player, enemy);
        }

        player.setState(FighterState.IDLE);
    }

    private void block() {
        player.setState(FighterState.BLOCK);

        fightScreenService.printScreen(player, enemy);
    }

    private void heal() throws Exception {
        if (!gameState.canPlayerHeal()) {
            System.out.println("Not available");
            System.out.println(keyboardService.getKeyboard());
        }

        if (!player.canHeal()) return;

        int healBy = (player.getHealth() / 2);
        player.setState(FighterState.HEAL);

        for (int i = 0; i < healBy; i += 2) {
            player.heal();
            animationService.animate(player, enemy);
        }

        player.setState(FighterState.IDLE);
        gameState.setPlayerCanHeal(false);
    }

    private void fatality() throws Exception {
        player.setState(FighterState.ATTACK);
        enemy.setState(FighterState.TAKE_FATALITY);

        Integer health = enemy.getHealth();
        int changer = -1;

        for (int i = 0; i < 5; i++) {
            enemy.changeHealthBy(health * changer);
            changer *= -1;

            animationService.animate(player, enemy);
        }

        enemy.setState(FighterState.DIE);
    }
}
