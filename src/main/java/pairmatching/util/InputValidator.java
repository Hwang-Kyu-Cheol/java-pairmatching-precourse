package pairmatching.util;

import pairmatching.constant.*;

import java.util.Arrays;
import java.util.List;

public class InputValidator {

    private static final String DELIMITER = ", ";

    /**
     * 문자열이 유효한 기능 선택인지 판단합니다.
     * @param input
     * @throws IllegalArgumentException 문자열이 유효하지 않을 경우 예외를 던집니다.
     */
    public static void validateSelectingFunctionInput(String input) throws IllegalArgumentException {
        if (!ValidInput.FUNCTION.contains(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORM.getValue());
        }
    }

    /**
     * 문자열이 "(코스), (레벨), (미션)"인지 판단합니다.
     * @param input
     * @throws IllegalArgumentException 문자열이 유효하지 않을 경우 예외를 던집니다.
     */
    public static void validateSelectingCourseLevelMissionInput(String input) throws IllegalArgumentException {
        List<String> tokenList = splitByDelimiter(input);
        validateTokenList(tokenList);
    }

    /**
     * 문자열이 유효한 재매치 선택인지 판단합니다.
     * @param input
     * @throws IllegalArgumentException 문자열이 유효하지 않을 경우 예외를 던집니다.
     */
    public static void validateSelectingRematchInput(String input) throws IllegalArgumentException {
        if (!ValidInput.TWO_WAY_CHOICE.contains(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORM.getValue());
        }
    }

    /** 비즈니스 로직 **/
    private static void validateTokenList(List<String> tokenList) {
        if (tokenList.size() != 3) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORM.getValue());
        }
        Course.findByName(tokenList.get(0))
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_COURSE.getValue()));
        Level.findByName(tokenList.get(1))
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_LEVEL.getValue()));
        Mission.findByName(tokenList.get(2))
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_MISSION.getValue()));
    }

    private static List<String> splitByDelimiter(String input) {
        return Arrays.asList(input.split(DELIMITER));
    }
}
