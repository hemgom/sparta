package assigment.mastery.scheduleManagementJPA.domain.refreshToken.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseReissueToken {
    private String accessToken;
}
