package lessons.lesson03.fighting.utils;

public class GameState {
    private boolean playerCanHeal;
    private boolean playerCanFatality;

    public GameState() {
        this.playerCanHeal = false;
        this.playerCanFatality = false;
    }

    public void setPlayerCanHeal(boolean playerCanHeal) {
        this.playerCanHeal = playerCanHeal;
    }

    public void setPlayerCanFatality(boolean playerCanFatality) {
        this.playerCanFatality = playerCanFatality;
    }

    public boolean canPlayerHeal() {
        return this.playerCanHeal;
    }

    public boolean canPlayerFatality() {
        return this.playerCanFatality;
    }
}

