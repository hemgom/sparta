package assignment.introductory.scheduleManagementJDBC.domain.schedule;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
public class Schedule {
    private int id;

    private String body;

    private String password;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private Author author;
}
