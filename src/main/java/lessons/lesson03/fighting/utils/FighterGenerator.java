package lessons.lesson03.fighting.utils;

import lessons.lesson03.fighting.model.FighterInfo;

import java.util.HashMap;
import java.util.Map;

public class FighterGenerator {
    public static Map<String, FighterInfo> generatePlayers() {
        FighterInfo subZero = new FighterInfo("1", "Sub-Zero", 20, 100);
        FighterInfo scorpion = new FighterInfo("2", "Scorpion", 20, 100);
        FighterInfo lukang = new FighterInfo("3", "Liu Kang", 15, 90);
        FighterInfo cage = new FighterInfo("4", "Johnny Cage", 15, 90);

        Map<String, FighterInfo> fighters = new HashMap<>();

        fighters.put("1", subZero);
        fighters.put("2", scorpion);
        fighters.put("3", lukang);
        fighters.put("4", cage);

        return fighters;
    }
}
