package assignment.introductory.scheduleManagement.domain.schedule.enums;

import lombok.Getter;

@Getter
public enum ValidDataTimeFormat {
    UPDATE_AT("updateAt", "update_at",
            "[2-9][0-9][2-9][4-9]\\-([0][1-9]|[1][0-2])\\-([0][1-9]|[1-2][0-9]|[3][0-1])"),
    ;

    private final String field;
    private final String column;
    private final String regex;

    ValidDataTimeFormat(String field, String column, String regex) {
        this.field = field;
        this.column = column;
        this.regex = regex;
    }
}
