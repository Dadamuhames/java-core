package lessons.lesson03.fighting.service.action;

import lessons.lesson03.fighting.model.Fighter;
import lessons.lesson03.fighting.service.ui.FightScreenService;
import lessons.lesson03.fighting.service.ui.AnimationService;
import lessons.lesson03.fighting.utils.FighterState;

import java.util.Random;

public class EnemyActionService {
    private final Fighter player;
    private final Fighter enemy;
    private final FightScreenService fightScreenService;
    private final Random random;
    private final AnimationService animationService;

    public EnemyActionService(Fighter player, Fighter enemy, FightScreenService fightScreenService, Random random, AnimationService animationService) {
        this.player = player;
        this.enemy = enemy;
        this.fightScreenService = fightScreenService;
        this.random = random;
        this.animationService = animationService;
    }

    public void action() throws Exception {
        if (enemy.canHeal()) {
            int allowHealing = random.nextInt(4) + 1;
            if (allowHealing == 4) {
                heal();
                return;
            }
        }

        int attackOrBlock = random.nextInt(4) + 1;

        if (attackOrBlock == 1) {
            block();
        } else {
            attack();
        }

        if (enemy.getInfo().attack() >= player.getHealth()) {
            fatality();
        }
    }

    private void block() {
        enemy.setState(FighterState.BLOCK);

        fightScreenService.printScreen(player, enemy);
    }

    private void attack() throws Exception {
        int attackDamage = enemy.getInfo().attack();

        if (player.getState().equals(FighterState.BLOCK)) {
            player.setState(FighterState.IDLE);
            return;
        }

        enemy.setState(FighterState.ATTACK);
        player.setState(FighterState.TAKE_DAMAGE);

        for (int i = 0; i < attackDamage; i+=5) {
            player.takeDamage(5);
            animationService.animate(player, enemy);
        }
    }

    private void heal() throws Exception {
        int healBy = (enemy.getHealth() / 2);
        enemy.setState(FighterState.HEAL);

        for (int i = 0; i < healBy; i+=2) {
            enemy.heal();
            animationService.animate(player, enemy);
        }
    }

    private void fatality() throws Exception {
        enemy.setState(FighterState.ATTACK);
        player.setState(FighterState.TAKE_FATALITY);

        Integer health = player.getHealth();
        int changer = -1;

        for (int i = 0; i < 5; i++) {
            player.changeHealthBy(health * changer);
            changer *= -1;

            animationService.animate(player, enemy);
        }

        player.setState(FighterState.DIE);
    }
}
