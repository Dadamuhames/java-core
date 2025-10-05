package lessons.lesson03.fighting.service.ui;

import lessons.lesson03.fighting.model.Fighter;
import lessons.lesson03.fighting.utils.Utils;

public class FightScreenService {
    private final int SCREEN_WIDTH = 100;

    private final HealthBarService healthBarService;

    public FightScreenService(HealthBarService healthBarService) {
        this.healthBarService = healthBarService;
    }


    public void printScreen(final Fighter player, final Fighter enemy) {
        String screen = getFightScreen(player, enemy);

        Utils.clearTerminal();

        System.out.println(screen);
    }


    public String getFightScreen(final Fighter fighterOne, final Fighter fighterTwo) {
        String nameLine = getNameLine(fighterOne, fighterTwo);
        String healthBarLine = getHealthBarLine(fighterOne, fighterTwo);
        String healthLine = getHealthLine(fighterOne, fighterTwo);
        String messageLine = getMessageLine(fighterOne, fighterTwo);

        return String.format("%s\n\n%s\n%s\n%s\n", nameLine, healthBarLine, healthLine, messageLine);
    }


    public String getHealthBarLine(final Fighter fighterOne, final Fighter fighterTwo) {
        String fighterOneHealthBar = healthBarService.getHealthBarLeft(fighterOne);
        String fighterTwoHealthBar = healthBarService.getHealthBarRight(fighterTwo);

        int gapWidth = SCREEN_WIDTH - fighterOneHealthBar.length() - fighterTwoHealthBar.length();

        return String.format("%s%s%s", fighterOneHealthBar, getGap(gapWidth), fighterTwoHealthBar);
    }


    public String getNameLine(final Fighter fighterOne, final Fighter fighterTwo) {
        String fighterOneName = fighterOne.getColoredName();
        String fighterTwoName = fighterTwo.getColoredName();

        int gapWidth = SCREEN_WIDTH - fighterOneName.length() - fighterTwoName.length();

        return String.format("%s%s%s", fighterOneName, getGap(gapWidth), fighterTwoName);
    }


    public String getHealthLine(final Fighter fighterOne, final Fighter fighterTwo) {
        String fighterOneHealth = fighterOne.getHealthStatus();
        String fighterTwoHealth = fighterTwo.getHealthStatus();

        int gapWidth = SCREEN_WIDTH - fighterOneHealth.length() - fighterTwoHealth.length();

        return String.format("%s%s%s", fighterOneHealth, getGap(gapWidth), fighterTwoHealth);
    }

    public String getMessageLine(final Fighter fighterOne, final Fighter fighterTwo) {
        String fighterOneMessage = fighterOne.getMessage();
        String fighterTwoMessage = fighterTwo.getMessage();

        int gapWidth = SCREEN_WIDTH - fighterOneMessage.length() - fighterTwoMessage.length();

        return String.format("%s%s%s", fighterOneMessage, getGap(gapWidth), fighterTwoMessage);
    }


    public String getGap(final int gapWidth) {
        return " ".repeat(gapWidth);
    }
}
