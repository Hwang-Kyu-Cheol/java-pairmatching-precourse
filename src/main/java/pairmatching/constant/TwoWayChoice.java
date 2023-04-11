package pairmatching.constant;

import java.util.Arrays;
import java.util.Optional;

public enum TwoWayChoice {
    YES("네"),
    NO("아니오");

    private String value;

    TwoWayChoice(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<TwoWayChoice> findByValue(String value) {
        return Arrays.stream(values())
                .filter(twoWayChoice -> twoWayChoice.getValue().equals(value))
                .findAny();
    }
}
