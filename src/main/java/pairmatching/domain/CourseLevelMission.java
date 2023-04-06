package pairmatching.domain;

import pairmatching.constant.Course;
import pairmatching.constant.Level;

public class CourseLevelMission {
    private Course course;
    private Level level;
    private Mission mission;

    public CourseLevelMission(Course course, Level leve, Mission mission) {
        this.course = course;
        this.level = leve;
        this.mission = mission;
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
}
