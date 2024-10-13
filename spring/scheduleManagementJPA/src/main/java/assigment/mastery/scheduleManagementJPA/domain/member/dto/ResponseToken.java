package assigment.mastery.scheduleManagementJPA.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseToken {
    private String accessToken;

    private String refreshToken;
}
