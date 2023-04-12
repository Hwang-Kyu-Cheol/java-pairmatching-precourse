package pairmatching.constant;

public enum ErrorMessage {
    INVALID_COURSE("올바른 코스를 입력해주세요."),
    INVALID_LEVEL("올바른 레벨을 입력해주세요."),
    INVALID_MISSION("올바른 미션을 입력해주세요."),
    INVALID_FORM("올바른 형식에 맞게 입력해주세요."),
    NOT_EXIST_MISSION("해당하는 미션이 존재하지 않습니다."),
    NOT_EXIST_MATCHING_RESULT("매칭 이력이 없습니다."),
    FAIL_MATCHING("매칭에 실패하였습니다.");

    private String value;

    ErrorMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
