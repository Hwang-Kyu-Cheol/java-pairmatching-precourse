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
    private static final String delimiter = ", ";

    public InputValidator(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    /**
     * 문자열이 "1","2","3","Q"인지 판단합니다.
     * @param input
     * @return 문자열이 유효할 경우 true를, 아닐 경우 false를 반환합니다.
     */
    public boolean isValidSelectingFunctionInput(String input) {
        if (validSelectingFunctionInputList.contains(input)) {
            return true;
        }
        return false;
    }

    /**
     * 문자열이 "(코스), (레벨), (미션)"인지 판단합니다.
     * @param input
     * @return 문자열이 유효할 경우 true를, 아닐 경우 false를 반환합니다.
     */
    public boolean isValidSelectingCourseLevelMissionInput(String input) {
        List<String> tokenList = splitByDelimiter(input);
        if (isValidTokenList(tokenList)) {
            return true;
        }
        return false;
    }

    /**
     * 문자열이 "네","아니오"인지 판단하고, 아닐 경우 예외를 던집니다.
     * @param input
     * @return 문자열이 유효할 경우 true를, 아닐 경우 false를 반환합니다.
     */
    public boolean isValidSelectingRematchInput(String input) throws IllegalArgumentException {
        if (validSelectingRematchInputList.contains(input)) {
            return true;
        }
        return false;
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
        return Arrays.asList(input.split(delimiter));
    }
}
