package assignment.introductory.scheduleManagement.domain.schedule;

import assignment.introductory.scheduleManagement.domain.schedule.dto.ScheduleDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
