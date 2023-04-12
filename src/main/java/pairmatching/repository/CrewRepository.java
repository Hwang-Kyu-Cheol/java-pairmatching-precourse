package pairmatching.repository;

import pairmatching.constant.Course;
import pairmatching.constant.NameList;
import pairmatching.util.FileReader;

import java.util.*;

public class CrewRepository {

    private static final Map<Course, List<String>> store = new HashMap<>();

    public void init() {
        Arrays.stream(NameList.values())
                .forEach((nameList) -> register(nameList));
    }

    public void save(Course course, List<String> names) {
        store.put(course, names);
    }

    public List<String> findByCourse(Course course) {
        return store.get(course);
    }

    /** 비즈니스 로직 **/
    private void register(NameList nameList) {
        Course course = nameList.getCourse();
        List<String> names = FileReader.readFile(nameList.getFilePath());
        save(course, names);
    }
}
