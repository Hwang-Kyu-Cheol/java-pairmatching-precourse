package pairmatching.constant;

import java.util.Arrays;
import java.util.Optional;

/**
 *   - 레벨1: 자동차경주 | 로또 | 숫자야구게임
 *   - 레벨2: 장바구니 | 결제 | 지하철노선도
 *   - 레벨3:
 *   - 레벨4: 성능개선 | 배포
 */
public enum Mission {
    //레벨1
    CAR_RACING(Level.LEVEL1, "자동차경주"),
    LOTTO(Level.LEVEL1, "로또"),
    NUMBER_BASEBALL_GAME(Level.LEVEL1, "숫자야구게임"),

    //레벨2
    SHOPPING_BASKET(Level.LEVEL2, "장바구니"),
    PAYMENT(Level.LEVEL2, "결제"),
    SUBWAY_MAP(Level.LEVEL2, "지하철노선도"),

    //레벨4
    PERFORMANCE_IMPROVEMENT(Level.LEVEL4, "성능개선"),
    DEPLOY(Level.LEVEL4, "배포");

    private final Level level;
    private final String name;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public static Optional<Mission> findByName(String name) {
        return Arrays.stream(values())
                .filter(mission -> mission.getName().equals(name))
                .findAny();
    }

    public static Optional<Mission> findByLevelAndName(Level level, String name) {
        return Arrays.stream(values())
                .filter(mission -> mission.getLevel().equals(level) && mission.getName().equals(name))
                .findAny();
    }
}
