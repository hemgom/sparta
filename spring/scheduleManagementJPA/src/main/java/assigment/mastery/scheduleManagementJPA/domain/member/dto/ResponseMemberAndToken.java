package assigment.mastery.scheduleManagementJPA.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseMemberAndToken {
    private ResponseMember member;

    private ResponseToken token;
}
