package pairmatching.constant;

import java.util.Arrays;

public class ValidInput {
    public static final String[] FUNCTION = {"1", "2", "3", "Q"};

    public static final String[] COURSE = Arrays.stream(Course.values())
                                                .map(course -> course.getName())
                                                .toArray(String[]::new);

    public static final String[] LEVEL = Arrays.stream(Level.values())
                                               .map(level -> level.getName())
                                               .toArray(String[]::new);

    public static final String[] MISSION = Arrays.stream(Mission.values())
                                                .map(mission -> mission.getName())
                                                .toArray(String[]::new);

    public static final String[] TWO_WAY_CHOICE = {"예", "아니오"};

}
