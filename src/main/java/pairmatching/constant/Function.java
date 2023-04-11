package pairmatching.constant;

import java.util.Arrays;
import java.util.Optional;

public enum Function {
    MATCH_PAIR("1"),
    FIND_PAIR("2"),
    RESET_PAIR("3"),
    QUIT("Q");

    private String value;

    Function(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<Function> findByValue(String value) {
        return Arrays.stream(values())
                .filter(function -> function.getValue().equals(value))
                .findAny();
    }
}
