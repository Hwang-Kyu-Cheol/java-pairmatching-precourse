package pairmatching.domain;

import pairmatching.constant.Course;
import pairmatching.constant.Level;

import java.util.List;

public class MatchingResult {
    private Course course;
    private Level level;
    private Mission mission;
    private List<Pair> pairList;

    public MatchingResult(Course course, Level level, Mission mission, List<Pair> pairList) {
        this.course = course;
        this.level = level;
        this.mission = mission;
        this.pairList = pairList;
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public Mission getMission() {
        return mission;
    }

    public List<Pair> getPairList() {
        return pairList;
    }
}
