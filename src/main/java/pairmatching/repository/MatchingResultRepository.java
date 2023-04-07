package pairmatching.repository;

import pairmatching.constant.Course;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;
import pairmatching.domain.MatchingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MatchingResultRepository {

    private static final Map<Long, MatchingResult> store = new HashMap<>();
    private static long index = 0L;

    public MatchingResult save(MatchingResult matchingResult) {
        matchingResult.setIndex(++index);
        store.put(matchingResult.getIndex(), matchingResult);
        return matchingResult;
    }

    public List<MatchingResult> findByLevel(Level level) {
        return store.values()
                .stream()
                .filter((matchingResult -> matchingResult.getLevel().equals(level)))
                .collect(Collectors.toList());
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
