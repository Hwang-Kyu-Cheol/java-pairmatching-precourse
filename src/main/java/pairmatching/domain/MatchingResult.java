package pairmatching.domain;

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

    public Mission getMission() {
        return mission;
    }

    public List<Pair> getPairList() {
        return pairList;
    }
}
