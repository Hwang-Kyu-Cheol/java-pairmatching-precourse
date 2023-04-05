package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.*;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MatchingResultRepository;
import pairmatching.repository.PairHistoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PairMatchingService {

    private final CrewRepository crewRepository;
    private final PairHistoryRepository pairHistoryRepository;
    private final MatchingResultRepository matchingResultRepository;
    public PairMatchingService(CrewRepository crewRepository, PairHistoryRepository pairHistoryRepository, MatchingResultRepository matchingResultRepository) {
        this.crewRepository = crewRepository;
        this.pairHistoryRepository = pairHistoryRepository;
        this.matchingResultRepository = matchingResultRepository;
    }

    /**
     * 핵심 기능 1 : 페어 매칭
     * @param course 코스(백엔드, 프론트엔드)
     * @param level 레벨(LEVEL1, LEVEL2 ...)
     * @param mission 미션(자동차경주, 숫자야구게임 ...)
     * @return 해당 코스, 레벨, 미션에 따른 매칭 결과를 반환합니다. 3번까지 매칭되지 않을 경우 Optional.empty()를 반환합니다.
     */
    public Optional<MatchingResult> matchPair(Course course, Level level, Mission mission) {
        List<Crew> crewList = crewRepository.findByCourse(course);
        for (int i = 0; i < 3; i++) {
            List<Crew> shuffledCrewList = shuffleCrewList(crewList);
            List<Pair> pairList = makePairList(shuffledCrewList);
            if (!isValidatePairList(pairList, level)) {
                continue;
            }
            recordPairHistory(pairList, level);
            MatchingResult matchingResult = new MatchingResult(course, level, mission, pairList);
            recordMatchingResult(course, level, mission, matchingResult);
            return Optional.of(matchingResult);
        }
        return Optional.empty();
    }

    /**
     * 핵심 기능 2 : 페어 조회
     * @param course 코스(백엔드, 프론트엔드)
     * @param level 레벨(LEVEL1, LEVEL2 ...)
     * @param mission 미션(자동차경주, 숫자야구게임 ...)
     * @return 해당 코스, 레벨, 미션에 따른 이전에 매칭된 결과를 반환합니다.
     */
    public Optional<MatchingResult> findPair(Course course, Level level, Mission mission) {
        return matchingResultRepository.findByCourseAndLevelAndMission(course, level, mission);
    }

    /**
     * 핵심 기능 3 : 페어 초기화
     */
    public void resetPair() {
        matchingResultRepository.clearStore();
    }

    private void recordMatchingResult(Course course, Level level, Mission mission, MatchingResult matchingResult) {
        if (matchingResultRepository.findByCourseAndLevelAndMission(course, level, mission).isPresent()) {
            matchingResultRepository.updateByCourseAndLevelAndMission(course, level, mission, matchingResult);
            return;
        }
        matchingResultRepository.save(matchingResult);
    }

    private void recordPairHistory(List<Pair> pairList, Level level) {
        pairList.stream()
                .forEach(pair -> recordPair(pair, level));
    }

    private void recordPair(Pair pair, Level level) {
        for (int i = 0; i < pair.size(); i++) {
            Crew self = pair.get(i);
            for (int j = i + 1; j < pair.size(); j++) {
                Crew other = pair.get(j);
                pairHistoryRepository.save(new PairHistory(level, self, other));
                pairHistoryRepository.save(new PairHistory(level, other, self));
            }
        }
    }

    private boolean isValidatePairList(List<Pair> pairList, Level level) {
        for (Pair pair : pairList) {
            if (!isValidatePair(pair, level)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidatePair(Pair pair, Level level) {
        for (int i = 0; i < pair.size(); i++) {
            Crew self = pair.get(i);
            for (int j = i + 1; j < pair.size(); j++) {
                Crew other = pair.get(j);
                if (pairHistoryRepository.findByLevelAndSelfAndOther(level, self, other).isPresent()) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<Crew> shuffleCrewList(List<Crew> crewList) {
        return Randoms.shuffle(crewList);
    }

    private List<Pair> makePairList(List<Crew> crewList) {
        List<Pair> pairList = new ArrayList<>();
        Pair pair = new Pair();
        for (Crew crew : crewList) {
            pair.add(crew);
            if (pair.isFull()) {
                pairList.add(pair);
                pair = new Pair();
            }
        }
        if (!pair.isEmpty()) {
            pairList.get(pairList.size() - 1).add(pair.get(0));
        }
        return pairList;
    }
}
