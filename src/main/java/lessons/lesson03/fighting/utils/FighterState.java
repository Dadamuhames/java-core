package lessons.lesson03.fighting.utils;

public enum FighterState {
    IDLE("39m", "READY!"),

    TAKE_DAMAGE("31m", "DAMAGE!"),

    TAKE_FATALITY("35m","GETTING DESTROYED!"),

    HEAL("32m", "RESTORE HEALTH!"),

    BLOCK("33m", "BLOCK!"),

    ATTACK("36m", "ATTACK!"),

    DIE("100m", "DEATH!");


    public final String color;
    public final String message;

    FighterState(String color, String message) {
        this.color = color;
        this.message = message;
    }

    public String getColor() {
        return "\033[" + color;
    }

    public String getMessage() {
        return message;
    }
}
