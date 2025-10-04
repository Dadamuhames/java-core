package lessons.lesson03.fighting;

import lessons.lesson03.fighting.model.FighterInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FighterRepository {
    private Map<String, FighterInfo> fighters;

    public FighterRepository(Map<String, FighterInfo> fighters) {
        this.fighters = fighters;
    }

    public List<FighterInfo> findAll() {
        return fighters.values().stream().toList();
    }

    public Optional<FighterInfo> findFighterByCode(final String code) {
        return Optional.ofNullable(fighters.get(code));
    }
}
