package pairmatching.repository;

import pairmatching.constant.Course;
import pairmatching.domain.Crew;
import pairmatching.constant.NameList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CrewRepository {

    private static final Map<Long, Crew> store = new HashMap<>();
    private static long sequence = 0L;

    public void init() {
        for(NameList nameList : NameList.values()) {
            try {
                List<String> names = new ArrayList<>();
                names = Files.readAllLines(Paths.get(nameList.getFilePath()));
                for(String name : names) {
                    Crew crew = new Crew(nameList.getCourse(), name);
                    save(crew);
                }
            } catch(IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public Crew save(Crew crew) {
        store.put(++sequence, crew);
        return crew;
    }

    public List<Crew> findAll() {
        return new ArrayList<>(store.values());
    }

    public List<Crew> findByCourse(Course course) {
        return store.values()
                .stream()
                .filter(crew -> crew.getCourse() == course)
                .collect(Collectors.toList());
    }
}
