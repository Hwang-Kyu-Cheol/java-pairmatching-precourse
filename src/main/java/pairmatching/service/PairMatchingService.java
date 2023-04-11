package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.constant.Course;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;
import pairmatching.domain.*;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MatchingResultRepository;

import java.util.ArrayList;
import java.util.List;

public class PairMatchingService {

    private static final int MATCHING_LIMIT = 3;
    private final CrewRepository crewRepository;
    private final MatchingResultRepository matchingResultRepository;

    public PairMatchingService(CrewRepository crewRepository, MatchingResultRepository matchingResultRepository) {
        this.crewRepository = crewRepository;
        this.matchingResultRepository = matchingResultRepository;
    }

    /**
     * 핵심 기능 1 : 페어 매칭
     * @param course 코스(백엔드, 프론트엔드)
     * @param level 레벨(LEVEL1, LEVEL2 ...)
     * @param mission 미션(자동차경주, 숫자야구게임 ...)
     * @return 해당 코스, 레벨, 미션에 따른 새롭게 매칭 결과를 반환합니다.
     * @throws IllegalStateException 3번까지 매칭되지 않을 경우 예외를 던집니다.
     */
    public MatchingResult matchPair(Course course, Level level, Mission mission) throws IllegalStateException {
        deleteMatchingResult(course, level, mission);
        MatchingResult matchingResult = createMatchingResult(course, level, mission);
        return matchingResultRepository.save(matchingResult);
    }

    /**
     * 핵심 기능 2 : 페어 조회
     * @param course 코스(백엔드, 프론트엔드)
     * @param level 레벨(LEVEL1, LEVEL2 ...)
     * @param mission 미션(자동차경주, 숫자야구게임 ...)
     * @return 해당 코스, 레벨, 미션에 따른 이전에 매칭된 결과를 반환합니다.
     * @throws IllegalStateException 이전에 매칭된 결과가 없을 경우 예외를 던집니다.
     */
    public MatchingResult findPair(Course course, Level level, Mission mission) throws IllegalStateException {
        return matchingResultRepository.findByCourseAndLevelAndMission(course, level, mission).orElseThrow(IllegalStateException::new);
    }

    /**
     * 핵심 기능 3 : 페어 초기화
     */
    public void resetPair() {
        matchingResultRepository.clearStore();
    }

    /** 비즈니스 로직 **/
    private MatchingResult createMatchingResult(Course course, Level level, Mission mission) throws IllegalStateException {
        List<String> crewNames = crewRepository.findByCourse(course);
        for (int i = 0; i < MATCHING_LIMIT; i++) {
            List<Pair> pairs = makePairs(shuffleCrewNames(crewNames));
            if (!isValidPairs(level, pairs)) {
                continue;
            }
            return new MatchingResult(course, level, mission, pairs);
        }
        throw new IllegalStateException();
    }

    private boolean isValidPairs(Level level, List<Pair> pairs) {
        for (Pair pair : pairs) {
            if (!isValidPair(level, pair)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPair(Level level, Pair pair) {
        List<MatchingResult> matchingResults = matchingResultRepository.findByLevel(level);
        for (MatchingResult matchingResult : matchingResults) {
            if (matchingResult.contains(pair)) {
                return false;
            }
        }
        return true;
    }

    private List<String> shuffleCrewNames(List<String> crewNames) {
        return Randoms.shuffle(crewNames);
    }

    private List<Pair> makePairs(List<String> crewNames) {
        List<Pair> pairs = new ArrayList<>();
        Pair pair = new Pair();
        for (String crewName : crewNames) {
            pair.add(crewName);
            if (pair.isFull()) {
                pairs.add(pair);
                pair = new Pair();
            }
        }
        if (!pair.isEmpty()) {
            pairs.get(pairs.size() - 1).add(pair.get(0));
        }
        return pairs;
    }

    private void deleteMatchingResult(Course course, Level level, Mission mission) {
        matchingResultRepository.findByCourseAndLevelAndMission(course, level, mission)
                .ifPresent(matchingResult -> matchingResultRepository.delete(matchingResult.getIndex()));
    }
}
