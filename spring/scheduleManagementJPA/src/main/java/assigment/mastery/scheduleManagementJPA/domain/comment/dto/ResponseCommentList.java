package assigment.mastery.scheduleManagementJPA.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResponseCommentList {
    private List<ResponseComment> comments;
}
