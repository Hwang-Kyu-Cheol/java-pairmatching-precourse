package pairmatching.domain;

import pairmatching.constant.Course;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;

import java.util.List;

public class MatchingResult {
    private Long index;
    private Course course;
    private Level level;
    private Mission mission;
    private List<Pair> pairs;

    public MatchingResult(Course course, Level level, Mission mission, List<Pair> pairs) {
        this.course = course;
        this.level = level;
        this.mission = mission;
        this.pairs = pairs;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
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

    public List<Pair> getPairs() {
        return pairs;
    }

    public boolean contains(Pair other) {
        for (Pair self : pairs) {
            Pair big = (self.size() > other.size()) ? self : other;
            Pair small = (self.size() <= other.size()) ? self : other;
            if (big.containsAll(small)) {
                return true;
            }
        }
        return false;
    }
}
