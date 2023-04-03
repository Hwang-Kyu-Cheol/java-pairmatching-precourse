package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Pair {
    private static final int MAX = 2;
    private List<Crew> crewList;

    public Pair() {
        this.crewList = new ArrayList<>();
    }

    public void add(Crew crew) {
        crewList.add(crew);
    }

    public Crew get(int index) {
        return crewList.get(index);
    }

    public int size() {
        return crewList.size();
    }

    public boolean isFull() {
        return crewList.size() == MAX;
    }

    public boolean isEmpty() {
        return crewList.isEmpty();
    }

    public List<Crew> getCrewList() {
        return crewList;
    }
}
