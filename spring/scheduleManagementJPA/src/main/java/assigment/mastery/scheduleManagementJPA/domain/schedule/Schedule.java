package assigment.mastery.scheduleManagementJPA.domain.schedule;

import assigment.mastery.scheduleManagementJPA.converter.DateTimeFormatConverter;
import assigment.mastery.scheduleManagementJPA.domain.comment.Comment;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.AddSchedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.ResponseSchedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.dto.UpdateSchedule;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SCHEDULE")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "AUTHOR", nullable = false, length = 20)
    private String author;

    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    @Column(name = "BODY", nullable = false, length = 250)
    private String body;

    @Column(name = "CREATE_AT", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createAt;

    @Column(name = "UPDATE_AT", nullable = false)
    @LastModifiedDate
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public void update(UpdateSchedule request) {
        this.title = request.getTitle();
        this.body = request.getBody();
    }

    public static Schedule create(AddSchedule request) {
        return Schedule.builder()
                .author(request.getAuthor())
                .title(request.getTitle())
                .body(request.getBody())
                .build();
    }

    public static ResponseSchedule makeResponse(Schedule schedule) {
        return ResponseSchedule.builder()
                .id(schedule.getId())
                .author(schedule.getAuthor())
                .title(schedule.getTitle())
                .body(schedule.getBody())
                .createAt(DateTimeFormatConverter.convertDateTimeFormat(schedule.getCreateAt()))
                .updateAt(DateTimeFormatConverter.convertDateTimeFormat(schedule.getUpdateAt()))
                .commentCount(schedule.getComments().size())
                .build();
    }
}
