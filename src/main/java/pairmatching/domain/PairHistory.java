package pairmatching.domain;

import pairmatching.constant.Level;

public class PairHistory {
    private Level level;
    private Crew self;
    private Crew other;

    public PairHistory(Level level, Crew self, Crew other) {
        this.level = level;
        this.self = self;
        this.other = other;
    }

    public Level getLevel() {
        return level;
    }

    public Crew getSelf() {
        return self;
    }

    public Crew getOther() {
        return other;
    }
}
