package pairmatching.util;

import pairmatching.constant.*;
import pairmatching.domain.CourseLevelMission;

import java.util.Arrays;
import java.util.List;

public class InputResolver {

    private static final String DELIMITER = ", ";

    /**
     * 기능 선택 입력을 validate하고, Function enum으로 변환하여 반환합니다.
     * @param input
     * @return Function enum을 반환합니다.
     * @throws IllegalArgumentException 유효하지 않은 입력일 경우 exception을 던집니다.
     */
    public static Function resolveSelectingFunctionInput(String input) {
        InputValidator.validateSelectingFunctionInput(input);
        return Function.findByValue(input).get();
    }

    /**
     * (코스,레벨,미션) 선택 입력을 validate하고, CourseLevelMission 객체로 변환하여 반환합니다.
     * @param input
     * @return CourseLevelMission 객체를 반환합니다.
     * @throws IllegalArgumentException 유효하지 않은 입력일 경우 exception을 던집니다.
     */
    public static CourseLevelMission resolveSelectingCourseLevelMissionInput(String input) {
        InputValidator.validateSelectingCourseLevelMissionInput(input);
        List<String> tokenList = splitByDelimiter(input);
        return resolveTokenList(tokenList);
    }

    /**
     * 재매치 선택 입력을 validate하고, TwoWayChoice enum으로 변환하여 반환합니다.
     * @param input
     * @return TwoWayChoice
     * @throws IllegalArgumentException 유효하지 않은 입력일 경우 exception을 던집니다.
     */
    public static TwoWayChoice resolveSelectingRematchInput(String input) {
        InputValidator.validateSelectingRematchInput(input);
        return TwoWayChoice.findByValue(input).get();
    }

    /** 비즈니스 로직 **/
    private static CourseLevelMission resolveTokenList(List<String> tokenList) throws IllegalArgumentException {
        Course course = Course.findByName(tokenList.get(0)).get();
        Level level = Level.findByName(tokenList.get(1)).get();
        Mission mission = Mission.findByLevelAndName(level, tokenList.get(2))
                                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_EXIST_MISSION.getValue()));
        return new CourseLevelMission(course, level, mission);
    }

    private static List<String> splitByDelimiter(String input) {
        return Arrays.asList(input.split(DELIMITER));
    }
}
