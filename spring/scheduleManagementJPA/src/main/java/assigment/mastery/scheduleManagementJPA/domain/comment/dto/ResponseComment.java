package assigment.mastery.scheduleManagementJPA.domain.comment.dto;

import assigment.mastery.scheduleManagementJPA.converter.DateTimeFormatConverter;
import assigment.mastery.scheduleManagementJPA.domain.comment.Comment;
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

    public static ResponseComment makeResponse(Comment comment, String authorName) {
        return ResponseComment.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .author(authorName)
                .createAt(DateTimeFormatConverter.convertDateTimeFormat(comment.getCreateAt()))
                .updateAt(DateTimeFormatConverter.convertDateTimeFormat(comment.getUpdateAt()))
                .scheduleId(comment.getSchedule().getId())
                .build();
    }
}
