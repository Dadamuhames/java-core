package lessons.lesson03.fighting.model;

import lessons.lesson03.fighting.utils.FighterState;
import lessons.lesson03.fighting.utils.Utils;

public class Fighter {
    private FighterInfo info;
    private FighterState state;
    private Integer health;

    public Fighter(FighterInfo info) {
        this.health = info.maxHealth();
        this.info = info;
        this.state = FighterState.IDLE;
    }

    public FighterInfo getInfo() {
        return info;
    }


    public String getColoredFighterAttribute(final String value) {
        return String.format("%s%s%s", state.getColor(), value, Utils.RESET_COLOR);
    }

    public String getColoredName() {
        return getColoredFighterAttribute(info.name());
    }

    public String getHealthStatus() {
        String healthStatus = String.format("%d/%d", health, info.maxHealth());
        return getColoredFighterAttribute(healthStatus);
    }

    public String getMessage() {
        String message = state.getMessage();
        return getColoredFighterAttribute(message);
    }

    public Integer getHealth() {
        return health;
    }

    public FighterState getState() {
        return state;
    }

    public void changeHealthBy(Integer health) {
        this.health += health;
    }

    public void takeDamage(final Integer attack) {
        this.health -= attack;
    }

    public boolean canHeal() {
        return this.health <= (info.maxHealth() / 2);
    }

    public void heal() {
        this.health+=2;
    }

    public void setState(FighterState state) {
        this.state = state;
    }
}
