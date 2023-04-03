package pairmatching.repository;

import pairmatching.domain.Level;
import pairmatching.domain.Mission;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MissionRepository {

    private static final Map<Long, Mission> store = new HashMap<>();
    private static long sequence = 0L;

    public void init() {
        save(new Mission(Level.LEVEL1, "자동차경주"));
        save(new Mission(Level.LEVEL1, "로또"));
        save(new Mission(Level.LEVEL1, "숫자야구게임"));
        save(new Mission(Level.LEVEL2, "장바구니"));
        save(new Mission(Level.LEVEL2, "결제"));
        save(new Mission(Level.LEVEL2, "지하철노선도"));
        save(new Mission(Level.LEVEL4, "성능개선"));
        save(new Mission(Level.LEVEL4, "배포"));
    }

    public Mission save(Mission mission) {
        store.put(++sequence, mission);
        return mission;
    }



    public Optional<Mission> findByLevelAndName(Level level, String name) {
        return store.values()
                .stream()
                .filter(mission -> (mission.getLevel().equals(level) && mission.getName().equals(name)))
                .findAny();
    }
}
