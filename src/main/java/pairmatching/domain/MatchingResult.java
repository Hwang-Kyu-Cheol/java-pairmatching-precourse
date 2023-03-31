package pairmatching.domain;

import java.util.List;

public class MatchingResult {
    private Course course;
    private Mission mission;
    private List<Pair> pairList;

    public MatchingResult(Course course, Mission mission, List<Pair> pairList) {
        this.course = course;
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
