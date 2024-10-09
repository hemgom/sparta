package assigment.mastery.scheduleManagementJPA.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseComment {
    private long id;

    private String body;

    private String createAt;

    private String updateAt;

    private String author;

    private long scheduleId;
}
