package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Pair {
    private static final int MAX_SIZE = 2;
    private List<String> crewNames;

    public Pair() {
        this.crewNames = new ArrayList<>();
    }

    public void add(String crewName) {
        crewNames.add(crewName);
    }

    public String get(int index) {
        return crewNames.get(index);
    }

    public int size() {
        return crewNames.size();
    }

    public boolean isFull() {
        return crewNames.size() == MAX_SIZE;
    }

    public boolean isEmpty() {
        return crewNames.isEmpty();
    }

    public List<String> getCrewList() {
        return crewNames;
    }

    public boolean containsAll(Pair pair) {
        ArrayList<String> arr = new ArrayList<>();
        arr.containsAll()
    }
}
