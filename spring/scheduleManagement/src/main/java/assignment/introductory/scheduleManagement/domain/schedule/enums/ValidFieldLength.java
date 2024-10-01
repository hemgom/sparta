package assignment.introductory.scheduleManagement.domain.schedule.enums;

import lombok.Getter;

@Getter
public enum ValidFieldLength {
    BODY("body", "body", 150),
    AUTHOR("author", "author", 20),
    PASSWORD("password", "password", 6),
    ;

    private final String field;
    private final String column;
    private final int length;

    ValidFieldLength(String field, String column, int length) {
        this.field = field;
        this.column = column;
        this.length = length;
    }
}
