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

    @Column(name = "CREATE_AT", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "UPDATE_AT", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "AUTHOR_NAME", nullable = false, length = 20)
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_ID")
    private Schedule schedule;

    public void update(UpdateComment request) {
        this.body = request.getBody();
    }

    public static Comment create(AddComment request, Schedule schedule) {
        return Comment.builder()
                .body(request.getBody())
                .author(request.getAuthor())
                .schedule(schedule)
                .build();
    }

    public static ResponseComment makeResponse(Comment comment) {
        return ResponseComment.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .createAt(DateTimeFormatConverter.convertDateTimeFormat(comment.getCreatedAt()))
                .updateAt(DateTimeFormatConverter.convertDateTimeFormat(comment.getUpdatedAt()))
                .author(comment.getAuthor())
                .scheduleId(comment.getSchedule().getId())
                .build();
    }
}
