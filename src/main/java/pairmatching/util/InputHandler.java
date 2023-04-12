package pairmatching.util;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.constant.Function;
import pairmatching.constant.TwoWayChoice;
import pairmatching.domain.CourseLevelMission;

public class InputHandler {

    public static Function selectFunction() {
        while (true) {
            String input = Console.readLine();
            System.out.println();
            try {
                return InputResolver.resolveSelectingFunctionInput(input);
            } catch (IllegalArgumentException e) {
                Display.displayError(e);
            }
        }
    }

    public static CourseLevelMission selectCourseLevelMission() {
        while (true) {
            String input = Console.readLine();
            System.out.println();
            try {
                return InputResolver.resolveSelectingCourseLevelMissionInput(input);
            } catch (IllegalArgumentException e) {
                Display.displayError(e);
            }
        }
    }

    public static TwoWayChoice selectRematch() {
        while (true) {
            String input = Console.readLine();
            System.out.println();
            try {
                return InputResolver.resolveSelectingRematchInput(input);
            } catch (IllegalArgumentException e) {
                Display.displayError(e);
            }
        }
    }
}
