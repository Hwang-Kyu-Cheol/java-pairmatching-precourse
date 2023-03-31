package pairmatching.repository;

import pairmatching.domain.MatchingResult;

import java.util.HashMap;
import java.util.Map;

public class MatchingResultRepository {

    private static final Map<Long, MatchingResult> store = new HashMap<>();
    private static long sequence = 0L;

    public MatchingResult save(MatchingResult matchingResult) {
        store.put(++sequence, matchingResult);
        return matchingResult;
    }
}
