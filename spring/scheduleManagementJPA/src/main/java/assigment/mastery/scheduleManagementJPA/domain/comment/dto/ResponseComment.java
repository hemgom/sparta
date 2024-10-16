package assigment.mastery.scheduleManagementJPA.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseComment {
    private Long id;

    private String body;

    private String author;

    private String createAt;

    private String updateAt;

    private Long scheduleId;
}
