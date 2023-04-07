package pairmatching.util;

import camp.nextstep.edu.missionutils.Console;

public class Input {

    private final InputResolver inputResolver;

    public Input(InputResolver inputResolver) {
        this.inputResolver = inputResolver;
    }

    public String selectFunction() {
        while (true) {
            Display.displaySelectingFunction();
            String input = Console.readLine();
            System.out.println();
            try {
                return inputResolver.resolveSelectingFunctionInput(input);
            } catch (IllegalArgumentException e) {
                Display.displayInputError();
            }
        }
    }

    public CourseLevelMission selectCourseLevelMission() {
        while (true) {
            Display.displaySelectingCourseLevelMission();
            String input = Console.readLine();
            System.out.println();
            try {
                return inputResolver.resolveSelectingCourseLevelMissionInput(input);
            } catch (IllegalArgumentException e) {
                Display.displayInputError();
            }
        }
    }

    public boolean selectRematch() {
        while (true) {
            Display.displaySelectingRematch();
            String input = Console.readLine();
            System.out.println();
            try {
                return inputResolver.resolveSelectingRematchInput(input);
            } catch (IllegalArgumentException e) {
                Display.displayInputError();
            }
        }
    }
}
