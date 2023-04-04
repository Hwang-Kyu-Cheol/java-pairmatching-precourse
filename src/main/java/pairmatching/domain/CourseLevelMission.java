package pairmatching.domain;

public class CourseLevelMission {
    private Course course;
    private Level leve;
    private Mission mission;

    public CourseLevelMission(Course course, Level leve, Mission mission) {
        this.course = course;
        this.leve = leve;
        this.mission = mission;
    }

    public Course getCourse() {
        return course;
    }

    public Level getLeve() {
        return leve;
    }

    public Mission getMission() {
        return mission;
    }
}
