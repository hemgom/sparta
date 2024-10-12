package assigment.mastery.scheduleManagementJPA.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseMember {
    private Long id;

    private String name;

    private String email;

    private String createAt;

    private String updateAt;
}
