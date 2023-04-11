package pairmatching.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ValidInput {
    public static final List<String> FUNCTION = new ArrayList<>(Arrays.asList("1", "2", "3", "Q"));

    public static final List<String> COURSE = Arrays.stream(Course.values())
                                                    .map(course -> course.getName())
                                                    .collect(Collectors.toList());

    public static final List<String> LEVEL = Arrays.stream(Level.values())
                                               .map(level -> level.getName())
                                                .collect(Collectors.toList());

    public static final List<String> MISSION = Arrays.stream(Mission.values())
                                                .map(mission -> mission.getName())
                                                .collect(Collectors.toList());

    public static final List<String> TWO_WAY_CHOICE = new ArrayList<>(Arrays.asList("네", "아니오"));
}
