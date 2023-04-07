package pairmatching.util;

import pairmatching.constant.Course;
import pairmatching.constant.Level;

import java.util.Arrays;
import java.util.List;

public class InputResolver {

    private final InputValidator inputValidator;
    private final MissionRepository missionRepository;
    private static final String delimiter = ", ";

    public InputResolver(InputValidator inputValidator, MissionRepository missionRepository) {
        this.inputValidator = inputValidator;
        this.missionRepository = missionRepository;
    }

    /**
     * 기능을 선택할 때 입력을 validate하고, 입력을 반환합니다.
     * @param input
     * @return 유효한 입력이면, 입력받은 문자열을 그대로 반환합니다.
     * @throws IllegalArgumentException 유효하지 않을 경우 exception을 던집니다.
     */
    public String resolveSelectingFunctionInput(String input) throws IllegalArgumentException {
        if (inputValidator.isValidSelectingFunctionInput(input)) {
            return input;
        }
        throw new IllegalArgumentException();
    }

    /**
     * 코스,레벨,미션을 선택할 때 입력을 validate하고, CourseLevelMission 객체로 변환하여 반환합니다.
     * @param input
     * @return 유효한 입력이면, CourseLevelMission 객체를 반환합니다.
     * @throws IllegalArgumentException 유효하지 않을 경우 exception을 던집니다.
     */
    public CourseLevelMission resolveSelectingCourseLevelMissionInput(String input) throws IllegalArgumentException {
        if (inputValidator.isValidSelectingCourseLevelMissionInput(input)) {
            List<String> tokenList = splitByDelimiter(input);
            return resolveTokenList(tokenList);
        }
        throw new IllegalArgumentException();
    }

    /**
     * 다시 매칭을 선택할 때 입력을 validate하고, boolean 타입으로 변환하여 반환합니다.
     * @param input
     * @return "네"일 경우 true를 "아니오"일 경우 false를 반환합니다.
     * @throws IllegalArgumentException 유효하지 않을 경우 exception을 던집니다.
     */
    public boolean resolveSelectingRematchInput(String input) throws IllegalArgumentException {
        if (inputValidator.isValidSelectingRematchInput(input)) {
            if (input.equals("네")) {
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException();
    }

    private CourseLevelMission resolveTokenList(List<String> tokenList) {
        Course course = Course.findByName(tokenList.get(0)).get();
        Level level = Level.findByName(tokenList.get(1)).get();
        Mission mission = missionRepository.findByLevelAndName(level, tokenList.get(2)).get();
        return new CourseLevelMission(course, level, mission);
    }

    private List<String> splitByDelimiter(String input) {
        return Arrays.asList(input.split(delimiter));
    }
}
