package pairmatching.repository;

import pairmatching.constant.Course;
import pairmatching.constant.Level;
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

    public Optional<MatchingResult> findByCourseAndLevelAndMission(Course course, Level level, Mission mission) {
        return store.values()
                .stream()
                .filter(matchingResult -> matchingResult.getCourse().equals(course)
                                        && matchingResult.getLevel().equals(level)
                                        && matchingResult.getMission().equals(mission))
                .findAny();
    }

    public void updateByCourseAndLevelAndMission(Course course, Level level, Mission mission, MatchingResult matchingResult) {
        store.keySet()
                .stream()
                .filter(key -> store.get(key).getCourse().equals(course)
                        && store.get(key).getLevel().equals(level)
                        && store.get(key).getMission().equals(mission))
                .findAny()
                .ifPresent(key -> store.put(key, matchingResult));
    }

    public void clearStore() {
        store.clear();
    }
}
