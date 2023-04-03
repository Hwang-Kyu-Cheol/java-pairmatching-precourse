package pairmatching.util;

import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.repository.MissionRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputValidator {

    private final MissionRepository missionRepository;
    private static final List<String> validSelectingFunctionInputList = new ArrayList<>(Arrays.asList("1", "2", "3", "Q"));
    private static final List<String> validSelectingRematchInputList = new ArrayList<>(Arrays.asList("네", "아니오"));

    public InputValidator(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    /**
     * 문자열이 "1","2","3","Q"인지 판단하고, 아닐 경우 예외를 던집니다.
     * @param input
     * @throws IllegalArgumentException
     */
    public void validateSelectingFunctionInput(String input) throws IllegalArgumentException {
        if (!validSelectingFunctionInputList.contains(input)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 문자열이 "(코스), (레벨), (미션)"인지 판단하고, 아닐 경우 예외를 던집니다.
     * @param input
     * @throws IllegalArgumentException
     */
    public void validateSelectingCourseLevelMissionInput(String input) throws IllegalArgumentException {
        List<String> tokenList = splitByDelimiter(input);
        if (!isValidTokenList(tokenList)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 문자열이 "네","아니오"인지 판단하고, 아닐 경우 예외를 던집니다.
     * @param input
     * @throws IllegalArgumentException
     */
    public void validateSelectingRematchInput(String input) throws IllegalArgumentException {
        if (!validSelectingRematchInputList.contains(input)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValidTokenList(List<String> tokenList) {
        if (tokenList.size() != 3) {
            return false;
        }
        Course course = Course.findByName(tokenList.get(0)).orElse(null);
        Level level = Level.findByName(tokenList.get(1)).orElse(null);
        Mission mission = missionRepository.findByLevelAndName(level, tokenList.get(2)).orElse(null);
        if (course == null || level == null || mission == null) {
            return false;
        }
        return true;
    }

    private List<String> splitByDelimiter(String input) {
        return Arrays.asList(input.split(", "));
    }
}
