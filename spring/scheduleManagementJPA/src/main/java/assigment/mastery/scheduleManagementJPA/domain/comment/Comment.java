package assigment.mastery.scheduleManagementJPA.domain.comment;

import assigment.mastery.scheduleManagementJPA.converter.DateTimeFormatConverter;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.AddComment;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.ResponseComment;
import assigment.mastery.scheduleManagementJPA.domain.comment.dto.UpdateComment;
import assigment.mastery.scheduleManagementJPA.domain.schedule.Schedule;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "COMMENT")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "BODY", nullable = false, length = 150)
    private String body;

    @Column(name = "AUTHOR_ID", nullable = false)
    private Long authorId;

    @Column(name = "CREATE_AT", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createAt;

    @Column(name = "UPDATE_AT", nullable = false)
    @LastModifiedDate
    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_ID")
    private Schedule schedule;

    public static Comment create(Schedule schedule, Long authorId, AddComment request) {
        return Comment.builder()
                .body(request.getBody())
                .authorId(authorId)
                .schedule(schedule)
                .build();
    }

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

    public void update(UpdateComment request) {
        this.body = request.getBody();
    }
}
