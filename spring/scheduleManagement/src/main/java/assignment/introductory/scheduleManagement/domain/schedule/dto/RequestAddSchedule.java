package assignment.introductory.scheduleManagement.domain.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestAddSchedule {
    private String body;
    private String author;
    private String password;
}
