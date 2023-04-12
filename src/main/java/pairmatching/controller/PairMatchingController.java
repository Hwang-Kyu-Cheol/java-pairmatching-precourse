package pairmatching.controller;

import pairmatching.constant.TwoWayChoice;
import pairmatching.domain.*;
import pairmatching.service.PairMatchingService;
import pairmatching.util.Display;
import pairmatching.util.InputHandler;

public class PairMatchingController {

    private final PairMatchingService pairMatchingService;

    public PairMatchingController(PairMatchingService pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    public void matchPair() {
        Display.displayCourseLevelMission();
        CourseLevelMission input = getCourseLevelMission();
        try {
            MatchingResult matchingResult = pairMatchingService.matchPair(input.getCourse(), input.getLevel(), input.getMission());
            Display.displayMatchingResult(matchingResult);
        } catch (IllegalStateException e) {
            Display.displayError(e);
        }
    }

    public void findPair() {
        Display.displayCourseLevelMission();
        Display.displaySelectingCourseLevelMission();
        CourseLevelMission input = InputHandler.selectCourseLevelMission();
        try {
            MatchingResult matchingResult = pairMatchingService.findPair(input.getCourse(), input.getLevel(), input.getMission());
            Display.displayMatchingResult(matchingResult);
        } catch (IllegalStateException e) {
            Display.displayError(e);
        }
    }

    public void resetPair() {
        pairMatchingService.resetPair();
        Display.displayResetting();
    }

    /** 비즈니스 로직 **/
    private CourseLevelMission getCourseLevelMission() {
        while (true) {
            Display.displaySelectingCourseLevelMission();
            CourseLevelMission input = InputHandler.selectCourseLevelMission();
            try {
                pairMatchingService.findPair(input.getCourse(), input.getLevel(), input.getMission());
            } catch (IllegalStateException e) {
                return input;
            }
            if (wantToRematch()) {
                return input;
            }
        }
    }

    private boolean wantToRematch() {
        Display.displaySelectingRematch();
        TwoWayChoice input = InputHandler.selectRematch();
        if (input.equals(TwoWayChoice.YES)) {
            return true;
        }
        return false;
    }
}
