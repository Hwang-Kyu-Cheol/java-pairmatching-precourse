package pairmatching.controller;

import pairmatching.constant.Course;
import pairmatching.constant.Level;
import pairmatching.domain.*;
import pairmatching.service.PairMatchingService;
import pairmatching.util.Display;

import java.util.Optional;

public class PairMatchingController {

    private final Input input;
    private final PairMatchingService pairMatchingService;

    public PairMatchingController(Input input, PairMatchingService pairMatchingService) {
        this.input = input;
        this.pairMatchingService = pairMatchingService;
    }

    public void matchPair() {
        CourseLevelMission courseLevelMission;
        Course course;
        Level level;
        Mission mission;
        Display.displayCourseLevelMission();
        while (true) {
            courseLevelMission = input.selectCourseLevelMission();
            course = courseLevelMission.getCourse();
            level = courseLevelMission.getLevel();
            mission = courseLevelMission.getMission();
            if (!pairMatchingService.findPair(course, level, mission).isPresent() || input.selectRematch()) {
                break;
            }
        }
        Optional<MatchingResult> matchingResult = pairMatchingService.matchPair(course, level, mission);
        if (!matchingResult.isPresent()) {
            Display.displayMatchPairError();
            return;
        }
        Display.displayMatchingResult(matchingResult.get().getPairList());
    }

    public void findPair() {
        Display.displayCourseLevelMission();
        CourseLevelMission courseLevelMission = input.selectCourseLevelMission();
        Course course = courseLevelMission.getCourse();
        Level level = courseLevelMission.getLevel();
        Mission mission = courseLevelMission.getMission();
        Optional<MatchingResult> matchingResult = pairMatchingService.findPair(course, level, mission);
        if (!matchingResult.isPresent()) {
            Display.displayFindPairError();
            return;
        }
        Display.displayMatchingResult(matchingResult.get().getPairList());
    }

    public void resetPair() {
        pairMatchingService.resetPair();
        Display.displayResetting();
    }
}
