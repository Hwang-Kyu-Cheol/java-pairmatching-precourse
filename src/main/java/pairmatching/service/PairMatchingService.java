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
     * @return 해당 코스, 레벨, 미션에 따른 매칭 결과를 반환합니다.
     */
    public Optional<MatchingResult> matchPair(Course course, Level level, Mission mission) {
        List<Crew> crewList = crewRepository.findByCourse(course);
        List<Crew> shuffledCrewList = shuffleCrewList(crewList);
        List<Pair> pairList = makePairList(shuffledCrewList);
        if (!isValidatePairList(pairList, level)) {
            return Optional.empty();
        }
        recordPairHistory(pairList, level);
        MatchingResult matchingResult = new MatchingResult(course, mission, pairList);
        recordMatchingResult(matchingResult);
        return Optional.of(matchingResult);
    }

    private void recordMatchingResult(MatchingResult matchingResult) {
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
                PairHistory pairHistory = new PairHistory(level, self, other);
                pairHistoryRepository.save(pairHistory);
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
