package assigment.mastery.scheduleManagementJPA.security.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenInfo {
    private Long memberId;

    private String auth;
}
