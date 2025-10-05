package lessons.lesson03.fighting.service.ui;

import lessons.lesson03.fighting.model.Fighter;
import lessons.lesson03.fighting.utils.Utils;

public class AnimationService {
    private final FightScreenService fightScreenService;

    public AnimationService(FightScreenService fightScreenService) {
        this.fightScreenService = fightScreenService;
    }

    public void animate(final Fighter player, final Fighter enemy) throws Exception {
        String screen = fightScreenService.getFightScreen(player, enemy);

        Utils.clearTerminal();

        System.out.println(screen);

        Thread.sleep(1000);
    }
}
