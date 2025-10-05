package lessons.lesson03.fighting.model;

public record FighterInfo(String code, String name, Integer attack, Integer maxHealth) {
    @Override
    public String toString() {
        return String.format("Code: %s\nName: %s\nHealth: %d\nAttack: %s", code, name, maxHealth, attack);
    }
}
