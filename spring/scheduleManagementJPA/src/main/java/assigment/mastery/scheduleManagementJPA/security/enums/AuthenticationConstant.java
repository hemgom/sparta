package assigment.mastery.scheduleManagementJPA.security.enums;

import lombok.Getter;

@Getter
public enum AuthenticationConstant {
    HEADER("Authorization"),
    AUTH("auth"),
    JWT_TYPE("Bearer");
    ;

    private final String key;

    AuthenticationConstant(String key) {
        this.key = key;
    }
}
