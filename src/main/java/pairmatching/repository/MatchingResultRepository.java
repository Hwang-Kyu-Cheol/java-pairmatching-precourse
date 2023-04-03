package pairmatching.repository;

import pairmatching.domain.Course;
import pairmatching.domain.MatchingResult;
import pairmatching.domain.Mission;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MatchingResultRepository {

    private static final Map<Long, MatchingResult> store = new HashMap<>();
    private static long sequence = 0L;

    public MatchingResult save(MatchingResult matchingResult) {
        store.put(++sequence, matchingResult);
        return matchingResult;
    }

    public Optional<MatchingResult> findByCourseAndMission(Course course, Mission mission) {
        return store.values()
                .stream()
                .filter(matchingResult -> matchingResult.getCourse().equals(course)
                                        && matchingResult.getMission().equals(mission))
                .findAny();
    }

    public void clearStore() {
        store.clear();
    }
}
