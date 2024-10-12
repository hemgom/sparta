package assigment.mastery.scheduleManagementJPA.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Builder
public class ResponseMemberList {
    private List<ResponseMember> members;

    private Pageable pageable;
}
