package assigment.mastery.scheduleManagementJPA.security.enums;

import lombok.Getter;

@Getter
public enum AuthorizationKey {
    HEADER("Authorization"),
    AUTH("auth"),
    BEARER("Bearer");
    ;

    private final String key;

    AuthorizationKey(String key) {
        this.key = key;
    }
}
