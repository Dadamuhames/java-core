package lessons.lesson03.fighting.service.ui;


import lessons.lesson03.fighting.utils.GameState;

public class KeyboardService {
    private final GameState gameState;

    public KeyboardService(GameState gameState) {
        this.gameState = gameState;
    }

    public String getKeyboard() {
        if (gameState.canPlayerHeal()) {
            return getHealingKeyboard();
        } else if (gameState.canPlayerFatality()) {
            return getFatalityKeyboard();
        }

        return getBasicKeyboard();
    }

    private String getBasicKeyboard() {
        return "[A] - Attack | [B] - Block";
    }

    private String getHealingKeyboard() {

        return getBasicKeyboard() + " | [H] - Heal";
    }

    private String getFatalityKeyboard() {
        return getBasicKeyboard() + " | [F] - Fatality";
    }
} 
