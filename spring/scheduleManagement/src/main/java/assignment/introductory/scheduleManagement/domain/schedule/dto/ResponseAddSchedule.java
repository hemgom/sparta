package assignment.introductory.scheduleManagement.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAddSchedule {
    private int id;
    private String body;
    private String author;
    private LocalDateTime createAt;
}
