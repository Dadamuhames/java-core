package lessons.lesson03.fighting.service.ui;


import lessons.lesson03.fighting.model.Fighter;
import lessons.lesson03.fighting.utils.Utils;

public class HealthBarService {
    private final int HEALTH_BAR_MAX = 15;

    private int getHealthProportion(final Integer health, final Integer maxHealth) {
        return (health * HEALTH_BAR_MAX) / maxHealth;
    }

    public String getHealthBarLeft(final Fighter fighter) {
        String color = fighter.getState().getColor();
        String healthBar = getHealthBarLeft(fighter.getHealth(), fighter.getInfo().maxHealth());

        return String.format("%s%s%s", color, healthBar, Utils.RESET_COLOR);
    }

    public String getHealthBarRight(final Fighter fighter) {
        String color = fighter.getState().getColor();
        String healthBar = getHealthBarRight(fighter.getHealth(), fighter.getInfo().maxHealth());

        return String.format("%s%s%s", color, healthBar, Utils.RESET_COLOR);
    }

    public String getHealthBarLeft(final Integer health, final Integer maxHealth) {
        int proportionHealth = getHealthProportion(health, maxHealth);

        String gap = " ".repeat(HEALTH_BAR_MAX - proportionHealth);

        return String.format("|%s%s|", "=".repeat(proportionHealth), gap);
    }

    public String getHealthBarRight(final Integer health, final Integer maxHealth) {
        int proportionHealth = getHealthProportion(health, maxHealth);

        String gap = " ".repeat(HEALTH_BAR_MAX - proportionHealth);

        return String.format("|%s%s|", gap, "=".repeat(proportionHealth));
    }
}
