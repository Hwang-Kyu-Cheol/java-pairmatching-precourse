package pairmatching.domain;

import java.util.List;

public class Pair {
    private List<Crew> crewList;

    public Pair(List<Crew> crewList) {
        this.crewList = crewList;
    }

    public List<Crew> getCrewList() {
        return crewList;
    }
}
