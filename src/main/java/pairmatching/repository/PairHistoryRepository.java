package pairmatching.repository;

import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.PairHistory;

import java.util.*;

public class PairHistoryRepository {

    private static final Map<Long, PairHistory> store = new HashMap<>();
    private static long sequence = 0L;

    public PairHistory save(PairHistory pairHistory) {
        store.put(++sequence, pairHistory);
        return pairHistory;
    }

    public Optional<PairHistory> findByLevelAndSelfAndOther(Level level, Crew self, Crew other) {
        return store.values()
                .stream()
                .filter(pairHistory -> pairHistory.getLevel().equals(level)
                        && pairHistory.getSelf().equals(self)
                        && pairHistory.getOther().equals(other))
                .findAny();
    }
}
